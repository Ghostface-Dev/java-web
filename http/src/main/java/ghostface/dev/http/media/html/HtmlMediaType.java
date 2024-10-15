package ghostface.dev.http.media.html;

import ghostface.dev.http.body.HttpBody;
import ghostface.dev.http.media.MediaType;
import org.jsoup.nodes.Element;
import org.jetbrains.annotations.NotNull;

public final class HtmlMediaType implements MediaType<@NotNull Element> {

    private final @NotNull Type type;
    private final @NotNull Parameter[] parameters;
    private final @NotNull Element data;
    private final @NotNull HttpBody body;

    public HtmlMediaType(@NotNull Element data, @NotNull HttpBody body, @NotNull Parameter... parameters) {
        this.type = new Type("text", "html");
        this.data = data;
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
    public @NotNull Element getData() {
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
