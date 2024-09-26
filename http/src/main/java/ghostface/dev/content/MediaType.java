package ghostface.dev.content;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


import java.util.Objects;

public interface MediaType<T> {

    @NotNull Type type();

    @NotNull Parameter[] parameters();

    @Override
    @NotNull String toString();

    // Classes

    final class Type {

        public static final @NotNull Type TEXT_HTML = new Type("text", "html");

        private final @NotNull String type;
        private final @NotNull String subtype;

        public Type(@NotNull String type, @NotNull String subtype) {
            this.type = type;
            this.subtype = subtype;
        }

        public @NotNull String getType() {
            return type;
        }

        public @NotNull String getSubType() {
            return subtype;
        }

        // Implementations

        @Override
        public String toString() {
            return getType() + "/" + getSubType();
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

    final class Parameter {

        private final @NotNull String parameter;
        private final @NotNull String value;

        public Parameter(@NotNull String parameter, @NotNull String value) {
            this.parameter = parameter;
            this.value = value;
        }

        public @NotNull String getParameter() {
            return parameter;
        }

        public @NotNull String getValue() {
            return value;
        }

        // Implementations

        @Override
        public String toString() {
            return parameter + "=" + value;
        }

        @Override
        public boolean equals(@Nullable Object object) {
            if (this == object) return true;
            if (object == null || getClass() != object.getClass()) return false;
            Parameter that = (Parameter) object;
            return Objects.equals(parameter, that.parameter) && Objects.equals(value, that.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(parameter, value);
        }
    }
}
