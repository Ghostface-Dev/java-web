package ghostface.dev.content;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

public abstract class MediaType<T> {

    private static final @NotNull Set<@NotNull MediaType<?>> collection = new LinkedHashSet<>();

    static {
        for (@NotNull Method method : MediaType.class.getDeclaredMethods()) {
            if (Modifier.isPublic(method.getModifiers()) && Modifier.isStatic(method.getModifiers()) && method.getParameterCount() == 0) {
                try {
                    @NotNull MediaType<?> mediaType = (MediaType<?>) method.invoke(null);
                    collection.add(mediaType);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException("Cannot load media type method '" + method.getName() + "'", e);
                }
            }
        }
    }

    public static boolean add(@NotNull MediaType<?> media) {
        if (collection.stream().anyMatch(mediaType -> mediaType.getType().equals(media.getType())))
            return false;
        return collection.add(media);
    }

    public static boolean remove(@NotNull MediaType<?> media) {
        return collection.remove(media);
    }

    public static boolean contains(@NotNull MediaType<?> media) {
        return collection.contains(media);
    }

    public static @NotNull Collection<@NotNull MediaType<?>> collection() {
        return Collections.unmodifiableSet(collection);
    }

    // Objects

    private final @NotNull Type type;
    private final @NotNull Parameter[] parameters;

    public MediaType(@NotNull Type type, @NotNull Parameter @NotNull ... parameters) {
        this.type = type;
        this.parameters = parameters;
    }

    public @NotNull Type getType() {
        return type;
    }

    public @NotNull Parameter[] getParameters() {
        return parameters;
    }

    @Override
    public abstract @NotNull String toString();
    public abstract @NotNull T deserialize(@NotNull InputStream stream, @NotNull Parameter @NotNull ... parameters);

    // Implementations

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
            return getType() + "/" + getSubType() + "; ";
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
            return getKey() + "=" + getValue() + ",";
        }

        @Override
        public boolean equals(@Nullable Object object) {
            if (this == object) return true;
            if (object == null || getClass() != object.getClass()) return false;
            Parameter that = (Parameter) object;
            return Objects.equals(key, that.key) && Objects.equals(value, that.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key, value);
        }
    }
}
