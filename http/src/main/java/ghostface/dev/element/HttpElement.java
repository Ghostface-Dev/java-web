package ghostface.dev.element;

import ghostface.dev.HttpVersion;
import ghostface.dev.body.HttpBody;
import ghostface.dev.headers.HttpHeaders;
import org.jetbrains.annotations.NotNull;

public interface HttpElement {

    @NotNull
    HttpVersion getVersion();

    @NotNull
    HttpHeaders getHeaders();

    @NotNull
    HttpBody getBody();
}
