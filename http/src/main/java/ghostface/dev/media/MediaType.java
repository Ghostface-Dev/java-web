package ghostface.dev.media;

import ghostface.dev.body.HttpBody;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Objects;
import java.util.StringJoiner;

public abstract class MediaType<T> {

    static @NotNull String getString(@NotNull MediaType<?> mediaType) {
        @NotNull StringJoiner joiner = new StringJoiner("; ");
        joiner.add(mediaType.getType().toString());

        for (@NotNull Parameter parameter : mediaType.getParameters()) {
            joiner.add(parameter.toString());
        }

        return joiner.toString();
    }

    // Objects

    private final @NotNull Type type;
    private final @NotNull Parameter[] parameters;
    private final @NotNull T data;
    private final @NotNull HttpBody body;

    protected MediaType(@NotNull Type type, @NotNull T data, @NotNull HttpBody body, @NotNull Parameter[] parameters) {
        this.type = type;
        this.parameters = parameters;
        this.data = data;
        this.body = body;
    }

    public @NotNull Type getType() {
        return type;
    }

    public @NotNull Parameter[] getParameters() {
        return parameters;
    }

    public abstract @NotNull MediaTypeParser<T> getParser();

    public @NotNull T getData() {
        return data;
    }

    public @NotNull HttpBody getBody() {
        return body;
    }

    @Override
    public @NotNull String toString() {
        return getString(this);
    }

    @Override
    public final boolean equals(@Nullable Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        @NotNull MediaType<?> mediaType = (MediaType<?>) object;
        return Objects.equals(type, mediaType.type) && Objects.deepEquals(parameters, mediaType.parameters);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(type, Arrays.hashCode(parameters));
    }

    // Classes

    public static final class Type {

        public static @NotNull Type APPLICATION_JSON = new Type("application", "json");

        private final @NotNull String type;
        private final @NotNull String subtype;

        public Type(@NotNull String type, @NotNull String subtype) {
            this.type = type.toLowerCase();
            this.subtype = subtype.toLowerCase();
        }

        public @NotNull String getType() {
            return type;
        }

        public @NotNull String getSubtype() {
            return subtype;
        }

        @Override
        public String toString() {
            return getType() + "/" + getSubtype();
        }

        @Override
        public boolean equals(@Nullable Object object) {
            if (this == object) return true;
            if (object == null || getClass() != object.getClass()) return false;
            @NotNull Type type1 = (Type) object;
            return this.toString().equalsIgnoreCase(type1.toString());
        }

        @Override
        public int hashCode() {
            return Objects.hash(type.toLowerCase(), subtype.toLowerCase());
        }
    }

    // Classes

    public static final class Parameter {

        public static @NotNull Parameter UTF_8 = new Parameter("charset", "utf-8");

        private final @NotNull String key;
        private final @NotNull String value;

        public Parameter(@NotNull String key, @NotNull String value) {
            this.key = key;
            this.value = value;

            if (key.contains(";") || key.contains(",") || value.contains(";") || value.contains(",")) {
                throw new IllegalArgumentException("content type parameter key or value contains illegal caracteres");
            }
        }

        public @NotNull String getKey() {
            return key;
        }

        public @NotNull String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return getKey() + "=" + getValue();
        }

        @Override
        public boolean equals(@Nullable Object object) {
            if (this == object) return true;
            if (object == null || getClass() != object.getClass()) return false;
            @NotNull Parameter parameter = (Parameter) object;
            return this.toString().equalsIgnoreCase(parameter.toString());
        }

        @Override
        public int hashCode() {
            return Objects.hash(key.toLowerCase(), value.toLowerCase());
        }
    }
}
