package ghostface.dev.http.headers;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import ghostface.dev.http.body.HttpBody;
import ghostface.dev.http.exception.header.HttpHeaderException;
import ghostface.dev.http.impl.SimpleHttpHeader;
import ghostface.dev.http.media.MediaType;
import ghostface.dev.http.media.json.JsonMediaType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ghostface.dev.http.media.MediaType.Parameter;

import java.util.*;

public abstract class HttpHeaderName<T> {

    // Static providers

    public static final @NotNull HttpHeaderName<@NotNull MediaType<@NotNull JsonElement>> JSON_CONTENT = new Provider.ContentJson();
    public static final @NotNull HttpHeaderName<@NotNull Integer> CONTENT_LENGTH = new Provider.ContentLength();
    public static final @NotNull HttpHeaderName<@NotNull Boolean> CONNECTION = new Provider.Connection();

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

    public abstract @NotNull String serialize(@NotNull HttpHeader<T> header, @NotNull Parameter @NotNull ... parameters);

    @Override
    public String toString() {
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
                super("Content-type", Target.BOTH);
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
                        return new SimpleHttpHeader<>(this, new JsonMediaType(JsonNull.INSTANCE, HttpBody.empty()), Target.BOTH);
                    }
                } catch (@NotNull HttpHeaderException e) {
                    throw new HttpHeaderException("The string '" + string + "' is not a valid Content-type for JSON: " + e.getMessage());
                }
            }

            @Override
            public @NotNull String serialize(
                    @NotNull HttpHeader<@NotNull MediaType<@NotNull JsonElement>> header,
                    @NotNull Parameter @NotNull ... parameters
            ) {
                @NotNull StringJoiner joiner = new StringJoiner("; ");
                joiner.add(name() + ": " + "application/json");
                for (@NotNull Parameter parameter : parameters) {
                    joiner.add(parameter.toString());
                }
                return joiner.toString();
            }
        }

        private static final class ContentLength extends HttpHeaderName<@NotNull Integer> {

            public ContentLength() {
                super("Content-length", Target.BOTH);
            }

            @Override
            public @NotNull HttpHeader<@NotNull Integer> parse(@NotNull String string) throws HttpHeaderException {
                @NotNull String[] parts = string.split("\\s*:\\s");

                try {
                    if (parts.length != 2) {
                        throw new Exception();
                    } else if (!parts[0].equalsIgnoreCase(this.name())) {
                        throw new Exception();
                    } else {
                        return new SimpleHttpHeader<>(this, Integer.parseInt(parts[1]), Target.BOTH);
                    }
                } catch (@NotNull Throwable throwable) {
                    throw new HttpHeaderException("The string '" + string + "' is not a valid Content-Length");
                }
            }

            @Override
            public @NotNull String serialize(
                    @NotNull HttpHeader<@NotNull Integer> contentLength,
                    @NotNull Parameter @NotNull ... parameters
            ) {
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
                        throw new Exception();
                    } else if (!parts[0].equalsIgnoreCase(this.name())) {
                        throw new Exception();
                    } else if (!value.matches("^\\s*keep-alive|close\\s*$")) {
                        throw new Exception();
                    } else {
                        return new SimpleHttpHeader<>(this, value.equalsIgnoreCase("keep-alive"), Target.BOTH);
                    }
                } catch (@NotNull Throwable throwable) {
                    throw new HttpHeaderException("The string '" + string + "' is not a valid Connection");
                }
            }

            @Override
            public @NotNull String serialize(@NotNull HttpHeader<@NotNull Boolean> header, @NotNull Parameter @NotNull ... parameters) {
                return this.name() + ": " + (header.getValue() ? "keep-alive" : "close");
            }
        }

        // Constructor
        private Provider() {throw new UnsupportedOperationException();}
    }
}
