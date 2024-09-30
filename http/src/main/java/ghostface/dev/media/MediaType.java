package ghostface.dev.media;

import ghostface.dev.body.HttpBody;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


import java.io.IOException;
import java.io.InputStream;
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
}
