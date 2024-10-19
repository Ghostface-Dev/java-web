package ghostface.dev.http.media.json;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import ghostface.dev.http.body.HttpBody;
import ghostface.dev.http.exception.MediaParseException;
import ghostface.dev.http.media.MediaType;
import ghostface.dev.http.media.MediaTypeParse;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;


public final class JsonMediaType implements MediaType<@NotNull JsonElement> {

    public static @NotNull MediaTypeParse<JsonElement> jsonMediaParser = new Parser();

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
        return new Parser();
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

    private static final class Parser implements MediaTypeParse<@NotNull JsonElement> {

        @Override
        public @NotNull JsonElement deserialize(@NotNull InputStream stream) throws MediaParseException {
            try {
                return JsonParser.parseReader(new InputStreamReader(stream, StandardCharsets.UTF_8));
            } catch (JsonSyntaxException e) {
                throw new MediaParseException("Cannot parse stream as a valid json", e);
            }
        }

        @Override
        public @NotNull InputStream serialize(@NotNull JsonElement obj, @NotNull Parameter... parameters) throws MediaParseException {
            @Nullable Charset charset = Arrays.stream(parameters).anyMatch(parameter -> Parameter.UTF_8.equals(parameter)) ? StandardCharsets.UTF_8 : null;

            return new ByteArrayInputStream(charset != null ? obj.toString().getBytes(charset) : obj.toString().getBytes());
        }
    }
}
