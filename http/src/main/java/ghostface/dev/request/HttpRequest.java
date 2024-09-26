package ghostface.dev.request;

import ghostface.dev.body.HttpBody;
import ghostface.dev.headers.HttpHeaders;
import ghostface.dev.message.Method;
import ghostface.dev.version.HttpVersion;
import org.jetbrains.annotations.NotNull;

import java.net.URI;


public interface HttpRequest {

    @NotNull Method getMethod();

    @NotNull URI getUri();

    @NotNull HttpVersion getVersion();

    @NotNull HttpHeaders getHeaders();

    @NotNull HttpBody getBody();

}
