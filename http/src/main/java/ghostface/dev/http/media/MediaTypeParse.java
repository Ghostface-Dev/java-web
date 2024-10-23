package ghostface.dev.http.media;

import ghostface.dev.http.exception.media.MediaParserException;
import org.jetbrains.annotations.NotNull;
import ghostface.dev.http.media.MediaType.Parameter;

import java.io.InputStream;

public interface MediaTypeParse<T> {

    @NotNull T deserialize(@NotNull InputStream stream) throws MediaParserException;

    @NotNull InputStream serialize(@NotNull T obj, @NotNull Parameter... parameters) throws MediaParserException;

}
