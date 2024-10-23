package ghostface.dev.http.element;

import ghostface.dev.http.HttpVersion;
import ghostface.dev.http.body.HttpBody;
import ghostface.dev.http.headers.HttpHeaders;
import org.jetbrains.annotations.NotNull;

public interface HttpElement {

    @NotNull HttpVersion getVersion();

    @NotNull HttpHeaders getHeaders();

    @NotNull HttpBody getBody();
}
