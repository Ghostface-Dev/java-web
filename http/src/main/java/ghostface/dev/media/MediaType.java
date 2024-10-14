package ghostface.dev.media;

import ghostface.dev.body.HttpBody;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.StringJoiner;

public interface MediaType<T> {

    static @NotNull String getString(@NotNull MediaType<?> mediaType) {
        @NotNull StringJoiner joiner = new StringJoiner("; ");
        joiner.add(mediaType.getType().toString());

        for (@NotNull Parameter parameter : mediaType.getParameters()) {
            joiner.add(parameter.toString());
        }

        return joiner.toString();
    }

    @NotNull Type getType();

    @NotNull Parameter[] getParameters();

    @NotNull T getData();

    @NotNull HttpBody getBody();

    @Override
    @NotNull String toString();

    // Classes

    final class Type {

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
            return Objects.equals(type, type1.type) && Objects.equals(subtype, type1.subtype);
        }

        @Override
        public int hashCode() {
            return Objects.hash(type, subtype);
        }
    }

    // Classes

    final class Parameter {

        private final @NotNull String key;
        private final @NotNull String value;

        public Parameter(@NotNull String key, @NotNull String value) {
            this.key = key.toLowerCase();
            this.value = value.toLowerCase();

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
            return Objects.equals(key, parameter.key) && Objects.equals(value, parameter.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key, value);
        }
    }
}
