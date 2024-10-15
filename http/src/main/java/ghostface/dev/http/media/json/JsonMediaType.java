package ghostface.dev.http.media.json;

import com.google.gson.JsonElement;
import ghostface.dev.http.body.HttpBody;
import ghostface.dev.http.media.MediaType;
import org.jetbrains.annotations.NotNull;


public final class JsonMediaType implements MediaType<@NotNull JsonElement> {

    private final @NotNull Type type;
    private final @NotNull Parameter[] parameters;
    private final @NotNull JsonElement data;
    private final @NotNull HttpBody body;

    public JsonMediaType(@NotNull JsonElement element, @NotNull HttpBody body, @NotNull Parameter ... parameters) {
        this.type = new Type("application", "json");
        this.data = element;
        this.body = body;
        this.parameters = parameters;

        if (body.length() <= 0) throw new IllegalArgumentException("The Http Body cannot be null");
    }

    @Override
    public @NotNull Type getType() {
        return type;
    }

    @Override
    public @NotNull Parameter[] getParameters() {
        return parameters;
    }

    @Override
    public @NotNull JsonElement getData() {
        return data;
    }

    @Override
    public @NotNull HttpBody getBody() {
        return body;
    }

    @Override
    public @NotNull String toString() {
        return MediaType.getString(this);
    }
}
