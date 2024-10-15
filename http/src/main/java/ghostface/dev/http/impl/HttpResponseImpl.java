package ghostface.dev.http.impl;

import ghostface.dev.http.HttpMethod;
import ghostface.dev.http.HttpStatus;
import ghostface.dev.http.HttpVersion;
import ghostface.dev.http.body.HttpBody;
import ghostface.dev.http.element.HttpResponse;
import ghostface.dev.http.exception.header.HeaderException;
import ghostface.dev.http.headers.HttpHeaders;
import ghostface.dev.http.headers.Target;
import ghostface.dev.http.media.MediaType;
import org.jetbrains.annotations.NotNull;

public class HttpResponseImpl implements HttpResponse {

    private final @NotNull HttpVersion version;
    private final @NotNull HttpStatus status;
    private final @NotNull HttpHeaders headers;
    private final @NotNull HttpBody body;

    public HttpResponseImpl(@NotNull HttpStatus status, @NotNull HttpHeaders headers) throws HeaderException {
        this.version = HttpVersion.HTTP_1_1;
        this.status = status;
        this.headers = headers;
        this.body = headers.getMediaType().map(MediaType::getBody).orElse(HttpBody.empty());

        if (headers.getHeaders(Target.REQUEST).stream().anyMatch(httpHeader -> !httpHeader.getTarget().isResponse())) {
            throw new HeaderException("Headers contains Http request Header");
        }
    }

    @Override
    public @NotNull HttpVersion getVersion() {
        return version;
    }

    @Override
    public @NotNull HttpStatus getStatus() {
        return status;
    }

    @Override
    public @NotNull HttpHeaders getHeaders() {
        return headers;
    }

    @Override
    public @NotNull HttpBody getBody() {
        return body;
    }
}
