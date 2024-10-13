package ghostface.dev.media;

import ghostface.dev.body.HttpBody;

import org.jetbrains.annotations.Blocking;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;

public abstract class MediaType<T> {

    // TODO: add providers

    public static @NotNull String getString(@NotNull MediaType<?> media) {
        @NotNull StringBuilder builder = new StringBuilder();
        builder.append(media.getType());

        for (@NotNull Parameter parameter : media.getParameters()) {
            builder.append("; ").append(parameter);
        }

        return builder.toString();
    }

    // Objects

    private final @NotNull Type type;
    private final @NotNull Parameter[] parameters;
    private final @NotNull HttpBody body;

    public MediaType(@NotNull T data ,@NotNull Type type, @NotNull Parameter @NotNull ... parameters) throws IOException {
        this.type = type;
        this.parameters = parameters;
        this.body = HttpBody.create(serialize(data));
    }

    @Blocking
    protected abstract @NotNull InputStream serialize(T data) throws IOException;

    public @NotNull Type getType() {
        return type;
    }

    public @NotNull Parameter[] getParameters() {
        return parameters;
    }

    public @NotNull HttpBody getBody() {
        return body;
    }

    // Implementations

    @Override
    public abstract @NotNull String toString();

    @Override
    public boolean equals(@Nullable Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        @NotNull MediaType<?> mediaType = (MediaType<?>) object;
        return Objects.equals(type, mediaType.type) && Objects.deepEquals(parameters, mediaType.parameters);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, Arrays.hashCode(parameters));
    }

    // Classes

    public static final class Parameter {

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

        // Implementations

        @Override
        public String toString() {
            return getKey() + "=" + getValue();
        }

        @Override
        public boolean equals(@Nullable Object object) {
            if (this == object) return true;
            if (object == null || getClass() != object.getClass()) return false;
            @NotNull Parameter that = (Parameter) object;
            return Objects.equals(key, that.key) && Objects.equals(value, that.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key, value);
        }
    }

    public static final class Type {

        // Static providers

        public static final @NotNull Type TEXT_HTML = new Type("text", "html");
        public static final @NotNull Type TEXT_CSS = new Type("text", "css");
        public static final @NotNull Type TEXT_XML = new Type("text", "xml");

        // todo created colletions for each type
        private static final @NotNull Set<@NotNull Type> collection = new LinkedHashSet<>();

        public static boolean add(@NotNull Type type) {
            return collection.add(type);
        }

        public static boolean remove(@NotNull Type type) {
            return collection.remove(type);
        }

        public static boolean contains(@NotNull Type type) {
            return collection.contains(type);
        }

        public static @NotNull Collection<@NotNull Type> collection() {
            return Collections.unmodifiableSet(collection);
        }

        // Static Initializers

        static {
            for (@NotNull Field field : Type.class.getDeclaredFields()) {
                if (Modifier.isPublic(field.getModifiers()) && Modifier.isStatic(field.getModifiers())) {
                    try {
                        @NotNull Type type = (Type) field.get(Type.class);
                        collection.add(type);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException("Cannot loading http name '" + field.getName() + "'", e) ;
                    }
                }
            }
        }

        // Objects

        private final @NotNull String type;
        private final @NotNull String subtype;

        public Type(@NotNull String type, @NotNull String subtype) {
            this.type = type.toLowerCase();
            this.subtype = subtype.toLowerCase();
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
}
