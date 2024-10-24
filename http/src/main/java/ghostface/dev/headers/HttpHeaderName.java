package ghostface.dev.headers;

import codes.laivy.address.host.Host;
import codes.laivy.address.host.HttpHost;
import codes.laivy.address.http.HttpAddress;
import codes.laivy.address.port.Port;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import ghostface.dev.body.HttpBody;
import ghostface.dev.exception.header.HttpHeaderException;
import ghostface.dev.impl.SimpleHttpHeader;
import ghostface.dev.media.MediaType;
import ghostface.dev.media.MediaType.Parameter;
import ghostface.dev.media.json.JsonMediaType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public abstract class HttpHeaderName<T> {

    // Static providers

    public static final @NotNull HttpHeaderName<@NotNull MediaType<@NotNull JsonElement>> JSON_CONTENT = new Provider.ContentJson();
    public static final @NotNull HttpHeaderName<@NotNull Integer> CONTENT_LENGTH = new Provider.ContentLength();
    public static final @NotNull HttpHeaderName<@NotNull Boolean> CONNECTION = new Provider.Connection();

    public static final @NotNull HttpHeaderName<@NotNull String> X_POWERED_BY = new Provider.PoweredBy();

    public static final @NotNull HttpHeaderName<@NotNull HttpHost<?>> HOST = new Provider.Host();

    public static boolean validate(@NotNull String s) {
        if (s.split("-").length > 6) {
            return false;
        } else if (!Character.isLetter(s.charAt(0))) {
            return false;
        } else {
            return s.matches("^[A-Za-z-]{2,35}$");
        }
    }

    // Objects

    private final @NotNull String name;
    private final @NotNull Target target;

    public HttpHeaderName(@NotNull String name, @NotNull Target target) {
        if (!validate(name)) {
            throw new IllegalArgumentException("The name '" + name + "' is not a valid http name");
        }
        this.name = name;
        this.target = target;
    }

    public final @NotNull String name() {
        return name;
    }

    public final @NotNull Target target() {
        return target;
    }

    public abstract @NotNull HttpHeader<T> parse(@NotNull String string) throws HttpHeaderException;

    public abstract @NotNull String serialize(@NotNull HttpHeader<T> header);

    @Override
    public @NotNull String toString() {
        return name();
    }

    @Override
    public final boolean equals(@Nullable Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        @NotNull HttpHeaderName<?> that = (HttpHeaderName<?>) object;
        return name.equalsIgnoreCase(that.name);
    }

    @Override
    public final int hashCode() {
        return Objects.hashCode(name.toLowerCase());
    }

    // Providers

    private static final class Provider {

        private static final class ContentJson extends HttpHeaderName<@NotNull MediaType<@NotNull JsonElement>> {
            private ContentJson() {
                super("Content-Type", Target.BOTH);
            }

            @Override
            public @NotNull HttpHeader<@Nullable MediaType<JsonElement>> parse(@NotNull String string) throws HttpHeaderException {
                @NotNull String[] parts = string.split("\\s*:\\s");

                try {
                    if (parts.length > 2) {
                        throw new HttpHeaderException("Too many parts in header");
                    } else if (parts.length == 2 && !parts[0].equalsIgnoreCase(this.name())) {;
                        throw new HttpHeaderException("Header name does not match");
                    } else if (!string.contains(MediaType.Type.APPLICATION_JSON.toString())) {
                        throw new HttpHeaderException("Media type is not JSON");
                    } else {
                        @Nullable Parameter parameter = parts[1].contains(Parameter.UTF_8.toString()) ? Parameter.UTF_8 : null;
                        if (parameter != null) {
                            return new SimpleHttpHeader<>(this, new JsonMediaType(JsonNull.INSTANCE, HttpBody.empty(), parameter), Target.BOTH);
                        } else {
                            return new SimpleHttpHeader<>(this, new JsonMediaType(JsonNull.INSTANCE, HttpBody.empty()), Target.BOTH);
                        }
                    }
                } catch (@NotNull HttpHeaderException e) {
                    throw new HttpHeaderException("The string '" + string + "' is not a valid Content-type for JSON: " + e.getMessage());
                }
            }

            @Override
            public @NotNull String serialize(@NotNull HttpHeader<@NotNull MediaType<@NotNull JsonElement>> header) {
                return header.getKey() + ": " + header.getValue();
            }
        }

        private static final class ContentLength extends HttpHeaderName<@NotNull Integer> {

            public ContentLength() {
                super("Content-Length", Target.BOTH);
            }

            @Override
            public @NotNull HttpHeader<@NotNull Integer> parse(@NotNull String string) throws HttpHeaderException {
                @NotNull String[] parts = string.split("\\s*:\\s");

                try {
                    if (parts.length != 2) {
                        throw new IllegalArgumentException("Invalid header");
                    } else if (!parts[0].equalsIgnoreCase(this.name())) {
                        throw new IllegalArgumentException("Invalid name header");
                    } else if (Integer.parseInt(parts[1]) < 0) {
                        throw new IllegalArgumentException("Numbers cannot be negative");
                    } else {
                        return new SimpleHttpHeader<>(this, Integer.parseInt(parts[1]), Target.BOTH);
                    }
                } catch (@NotNull IllegalArgumentException e) {
                    throw new HttpHeaderException("The string '" + string + "' is not a valid Content-Length: " + e.getMessage());
                }
            }

            @Override
            public @NotNull String serialize(@NotNull HttpHeader<@NotNull Integer> contentLength) {
                if (contentLength.getValue() < 0) {
                    throw new RuntimeException("Content-Length has illegal values");
                } else {
                    return name() + ": " + contentLength.getValue();
                }
            }
        }

        private static final class Connection extends HttpHeaderName<@NotNull Boolean> {

            public Connection() {
                super("Connection", Target.BOTH);
            }

            @Override
            public @NotNull HttpHeader<@NotNull Boolean> parse(@NotNull String string) throws HttpHeaderException {
                @NotNull String[] parts = string.split("\\s*:\\s");
                @NotNull String value = parts[1];

                try {
                    if (parts.length != 2) {
                        throw new IllegalArgumentException("Connection is not valid");
                    } else if (!parts[0].equalsIgnoreCase(this.name())) {
                        throw new IllegalArgumentException("Invalid Name");
                    } else if (!value.matches("^\\s*keep-alive|close\\s*$")) {
                        throw new IllegalArgumentException("Invalid value");
                    } else {
                        return new SimpleHttpHeader<>(this, value.equalsIgnoreCase("keep-alive"), Target.BOTH);
                    }
                } catch (@NotNull Throwable throwable) {
                    throw new HttpHeaderException("The string '" + string + "' is not a valid Connection: " + throwable.getMessage());
                }
            }

            @Override
            public @NotNull String serialize(@NotNull HttpHeader<@NotNull Boolean> header) {
                return this.name() + ": " + (header.getValue() ? "keep-alive" : "close");
            }
        }

        private static final class PoweredBy extends HttpHeaderName<@NotNull String> {

            public PoweredBy() {
                super("X-Powered-By", Target.RESPONSE);
            }

            @Override
            public @NotNull HttpHeader<@NotNull String> parse(@NotNull String string) throws HttpHeaderException {
                @NotNull String @NotNull [] parts = string.split("\\s*:\\s");

                try {
                    if (parts.length != 2) {
                        throw new IllegalArgumentException("Invalid header format");
                    } else if (!parts[0].equalsIgnoreCase(this.name())) {
                        throw new IllegalArgumentException("Invalid name");
                    } else if (!parts[1].matches("^[A-Za-z0-9]{2,15}")) {
                        throw new IllegalArgumentException("Invalid characters");
                    } else {
                        return new SimpleHttpHeader<>(this, "Java", Target.RESPONSE);
                    }
                } catch (@NotNull Throwable throwable) {
                    throw new HttpHeaderException("The string '" + string + "' is not valid: " + throwable.getMessage());
                }
            }

            @Override
            public @NotNull String serialize(@NotNull HttpHeader<@NotNull String> header) {
                return header.getKey().name() + ": " + header.getValue();
            }
        }

        private static final class Host extends HttpHeaderName<@NotNull HttpHost<?>> {
            public Host() {
                super("Host", Target.REQUEST);
            }

            @Override
            public @NotNull HttpHeader<@NotNull HttpHost<?>> parse(@NotNull String string) throws HttpHeaderException {
                @NotNull String @NotNull [] parts = string.split("\\s*:\\s");

                try {
                    if (parts.length != 2) {
                        throw new IllegalArgumentException("Invalid header format");
                    } else if (!parts[0].equalsIgnoreCase(this.name())) {
                        throw new IllegalArgumentException("Invalid name");
                    } else {
                        @NotNull String @NotNull [] address = parts[1].split(":");
                        @NotNull HttpHost<?> host = new HttpHost<>(HttpAddress.parse(parts[1]), address.length > 1 ? Port.parse(address[1]) : null);
                        return new SimpleHttpHeader<>(this, host, Target.REQUEST);
                    }
                } catch (@NotNull Throwable throwable) {
                    throw new HttpHeaderException("The string '" + string + "' is not a valid HTTP Host: " + throwable.getMessage());
                }
            }

            @Override
            public @NotNull String serialize(@NotNull HttpHeader<@NotNull HttpHost<?>> header) {
                return header.getKey() + ": " + header.getValue();
            }
        }

        // Constructor
        private Provider() {throw new UnsupportedOperationException();}
    }
}
