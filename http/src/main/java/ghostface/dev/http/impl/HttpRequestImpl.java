package ghostface.dev.http.impl;

import ghostface.dev.http.HttpMethod;
import ghostface.dev.http.HttpVersion;
import ghostface.dev.http.body.HttpBody;
import ghostface.dev.http.element.HttpRequest;
import ghostface.dev.http.exception.header.HeaderException;
import ghostface.dev.http.headers.HttpHeaders;
import ghostface.dev.http.headers.Target;
import ghostface.dev.http.media.MediaType;
import org.jetbrains.annotations.NotNull;

import java.net.URI;

public class HttpRequestImpl implements HttpRequest {

    private final @NotNull HttpVersion version;
    private final @NotNull HttpMethod method;
    private final @NotNull URI uri;
    private final @NotNull HttpHeaders headers;
    private final @NotNull HttpBody body;

    public HttpRequestImpl(@NotNull HttpMethod method, @NotNull URI uri, @NotNull HttpHeaders headers) throws HeaderException {
        this.version = HttpVersion.HTTP_1_1;
        this.method = method;
        this.uri = uri;
        this.headers = headers;
        this.body = headers.getMediaType().map(MediaType::getBody).orElse(HttpBody.empty());

        if (headers.getHeaders(Target.RESPONSE).stream().anyMatch(httpHeader -> !httpHeader.getTarget().isRequest())) {
            throw new HeaderException("Headers contains Http response Header");
        }
    }


    @Override
    public @NotNull HttpMethod getMethod() {
        return method;
    }

    @Override
    public @NotNull URI getURI() {
        return uri;
    }

    @Override
    public @NotNull HttpVersion getVersion() {
        return version;
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
