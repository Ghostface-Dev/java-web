package ghostface.dev.media.json;

import com.google.gson.*;
import ghostface.dev.body.HttpBody;
import ghostface.dev.exception.media.MediaParserException;
import ghostface.dev.media.MediaType;
import ghostface.dev.media.MediaTypeParser;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public final class JsonMediaType extends MediaType<@NotNull JsonElement> {

    public static final @NotNull MediaTypeParser<@NotNull JsonElement> parser = new Parser();

    // Objects

    public JsonMediaType(@NotNull JsonElement element, @NotNull HttpBody body, @NotNull Parameter ... parameters) {
        super(Type.APPLICATION_JSON, element, body, parameters);
    }

    @Override
    public @NotNull MediaTypeParser<@NotNull JsonElement> getParser() {
        return parser;
    }

    // Parser

    private static final class Parser implements MediaTypeParser<@NotNull JsonElement> {

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
