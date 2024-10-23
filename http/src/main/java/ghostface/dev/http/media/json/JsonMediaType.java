package ghostface.dev.http.media.json;

import com.google.gson.*;
import ghostface.dev.http.body.HttpBody;
import ghostface.dev.http.exception.media.MediaParserException;
import ghostface.dev.http.media.MediaType;
import ghostface.dev.http.media.MediaTypeParse;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Objects;


public final class JsonMediaType implements MediaType<@NotNull JsonElement> {

    public static final @NotNull MediaTypeParse<@NotNull JsonElement> parser = new Parser();

    // Objects

    private final @NotNull Type type;
    private final @NotNull Parameter[] parameters;
    private final @NotNull JsonElement data;
    private final @NotNull HttpBody body;

    public JsonMediaType(@NotNull JsonElement element, @NotNull HttpBody body, @NotNull Parameter ... parameters) {
        this.type = new Type("application", "json");
        this.data = element;
        this.body = body;
        this.parameters = parameters;

        if (body.isClose()) throw new IllegalArgumentException("The Http Body is close");
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
    public @NotNull MediaTypeParse<@NotNull JsonElement> getParse() {
        return parser;
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

    @Override
    public boolean equals(@Nullable Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        @NotNull JsonMediaType mediaType = (JsonMediaType) object;
        return Objects.equals(type, mediaType.type) && Objects.deepEquals(parameters, mediaType.parameters);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, Arrays.hashCode(parameters));
    }

    private static final class Parser implements MediaTypeParse<@NotNull JsonElement> {

        @Override
        public @NotNull JsonElement deserialize(@NotNull InputStream stream) throws MediaParserException {
            try {
                return JsonParser.parseReader(new InputStreamReader(stream, StandardCharsets.UTF_8));
            } catch (JsonSyntaxException e) {
                throw new MediaParserException("Cannot parse stream as a valid json", e);
            }
        }

        @Override
        public @NotNull InputStream serialize(@NotNull JsonElement json, @NotNull Parameter... parameters) {
            @Nullable Charset charset = Arrays.stream(parameters).anyMatch(parameter -> Parameter.UTF_8.equals(parameter)) ? StandardCharsets.UTF_8 : null;
            return new ByteArrayInputStream(charset != null ? json.toString().getBytes(charset) : json.toString().getBytes());
        }
    }
}
