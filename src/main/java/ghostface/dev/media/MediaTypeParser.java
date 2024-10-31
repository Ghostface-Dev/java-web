package ghostface.dev.media;

import ghostface.dev.exception.media.MediaParserException;
import org.jetbrains.annotations.NotNull;

import java.io.InputStream;

public interface MediaTypeParser<T> {

    @NotNull T deserialize(@NotNull InputStream stream) throws MediaParserException;

    @NotNull InputStream serialize(@NotNull T obj, @NotNull MediaType.Parameter... parameters) throws MediaParserException;

}
