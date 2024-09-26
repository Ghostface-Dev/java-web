package ghostface.dev.content;

import ghostface.dev.body.HttpBody;
import org.jetbrains.annotations.NotNull;


public interface Content<T> {

    @NotNull HttpBody getBody();

    @NotNull MediaType<T> getMediaType();

    @NotNull T getData();

}
