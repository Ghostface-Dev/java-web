package ghostface.dev.http.headers;

import com.google.gson.JsonElement;
import ghostface.dev.http.exception.header.HttpHeaderException;
import ghostface.dev.http.impl.SimpleHttpHeader;
import ghostface.dev.http.media.MediaType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ghostface.dev.http.media.MediaType.Parameter;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;

public abstract class HttpHeaderName<T> {

    // Static providers

    public static final @NotNull HttpHeaderName<@NotNull MediaType<@NotNull JsonElement>> APPLICATION_JSON = new Provider.ContentType.ContentJson();
    public static final @NotNull HttpHeaderName<@NotNull Integer> CONTENT_LENGTH = new Provider.ContentLength();
    public static final @NotNull HttpHeaderName<@NotNull Boolean> CONNECTION = new Provider.Connection();

    // Static collections

    private static final @NotNull Set<@NotNull HttpHeaderName<?>> collection = new LinkedHashSet<>();

    public static boolean add(@NotNull HttpHeaderName<?> name) {
        return collection.add(name);
    }

    public static boolean remove(@NotNull HttpHeaderName<?> name) {
        return collection.remove(name);
    }

    public static boolean contains(@NotNull HttpHeaderName<?> name) {
        return collection.contains(name);
    }

    public static @NotNull Collection<@NotNull HttpHeaderName<?>> collection() {
        return Collections.unmodifiableSet(collection);
    }

    // Static initializer

    static {
        for (@NotNull Field field : HttpHeaderName.class.getDeclaredFields()) {
            if (Modifier.isPublic(field.getModifiers()) && Modifier.isStatic(field.getModifiers())) {
                try {
                    @NotNull HttpHeaderName<?> name = (HttpHeaderName<?>) field.get(HttpHeaderName.class);
                    collection.add(name);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("Cannot loading http name '" + field.getName() + "'", e) ;
                }
            }
        }
    }

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
        this.name = name.toLowerCase();
        this.target = target;
    }

    public final @NotNull String name() {
        return name;
    }

    public final @NotNull Target target() {
        return target;
    }

    public abstract @NotNull HttpHeader<T> parse(@NotNull String string) throws HttpHeaderException;

    public abstract @NotNull String serialize(@NotNull HttpHeader<T> header, @NotNull Parameter @NotNull ... parameters) throws HttpHeaderException;

    @Override
    public String toString() {
        return name();
    }

    @Override
    public boolean equals(@Nullable Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        @NotNull HttpHeaderName<?> name1 = (HttpHeaderName<?>) object;
        return Objects.equals(name, name1.name) && target == name1.target;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    // Providers

    private static final class Provider {
        private Provider() {
            throw new UnsupportedOperationException();
        }

        // Classes

        private static abstract class ContentType<T> extends HttpHeaderName<@NotNull MediaType<T>> {
            protected ContentType(@NotNull String name, @NotNull Target target) {
                super(name, target);
            }
            // Content type classes
            private static final class ContentJson extends ContentType<@NotNull JsonElement> {
                private ContentJson() {
                    super("content-type", Target.BOTH);
                }

                @Override
                public @NotNull HttpHeader<@NotNull MediaType<@NotNull JsonElement>> parse(@NotNull String string) throws HttpHeaderException {
                    @NotNull String[] parts = string.split("\\s*:\\s");

                    try {
                        if (parts.length != 2) {
                            throw new Exception();
                        } else if (!parts[0].equalsIgnoreCase(this.name())) {
                            throw new Exception();
                        } else if (!parts[1].contains(MediaType.Type.APPLICATION_JSON.toString())) {
                            throw new Exception();
                        } else {
                            return new SimpleHttpHeader<>(this, null, Target.BOTH);
                        }
                    } catch (@NotNull Throwable throwable) {
                        throw new HttpHeaderException("The string '" + string + "' is not a valid Content-type for JSON");
                    }
                }

                @Override
                public @NotNull String serialize(
                        @NotNull HttpHeader<@NotNull MediaType<@NotNull JsonElement>> header,
                        @NotNull Parameter @NotNull ... parameters
                )
                        throws HttpHeaderException
                {
                    @NotNull StringJoiner joiner = new StringJoiner("; ");
                    joiner.add(name() + ": " + "application/json");
                    for (@NotNull Parameter parameter : parameters) {
                        joiner.add(parameter.toString());
                    }
                    return  joiner + "\r\n";
                }
            }

            // Classes
        }

        // Classes

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
            )
                    throws HttpHeaderException
            {
                if (contentLength.getValue() < 0) {
                    throw new HttpHeaderException("Content-Length has illegal values");
                } else {
                    return name() + ": " + contentLength.getValue();
                }
            }
        }

        // Classes

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
            public @NotNull String serialize(@NotNull HttpHeader<@NotNull Boolean> header, @NotNull Parameter @NotNull ... parameters) throws HttpHeaderException {
                return this.name() + ": " + (header.getValue() ? "keep-alive" : "close");
            }
        }
    }
}
