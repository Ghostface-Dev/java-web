package ghostface.dev.media.json;

import codes.laivy.serializable.json.JsonSerializable;
import com.google.gson.JsonObject;
import ghostface.dev.media.MediaType;
import ghostface.dev.media.Parameter;
import ghostface.dev.media.Type;
import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class JsonMediaType extends MediaType<@NotNull JsonObject> {

    public JsonMediaType(@NotNull JsonObject data) throws IOException {
        super(data, new Type("application", "json"), new Parameter("charset", "utf-8"));
    }

    @Override
    protected @NotNull InputStream serialize(@NotNull JsonObject data) {
        return new ByteArrayInputStream(data.toString().getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public @NotNull String toString() {
        return MediaType.getString(this) ;
    }
}
