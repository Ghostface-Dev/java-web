package ghostface.dev.response;

import ghostface.dev.body.HttpBody;
import ghostface.dev.headers.HttpHeaders;
import ghostface.dev.message.Status;
import ghostface.dev.version.HttpVersion;
import org.jetbrains.annotations.NotNull;


public interface HttpResponse {

    @NotNull HttpVersion getVersion();

    @NotNull Status getStatus();

    @NotNull HttpHeaders getHeaders();

    @NotNull HttpBody getBody();
}
