package ghostface.dev.impl;

import ghostface.dev.HttpMethod;
import ghostface.dev.HttpVersion;
import ghostface.dev.body.HttpBody;
import ghostface.dev.element.HttpRequest;
import ghostface.dev.exception.target.IllegalTargetException;
import ghostface.dev.headers.HttpHeaders;
import ghostface.dev.headers.Target;
import org.jetbrains.annotations.NotNull;

import java.net.URI;

public class HttpRequestImpl implements HttpRequest {

    private final @NotNull HttpVersion version;
    private final @NotNull HttpMethod method;
    private final @NotNull URI uri;
    private final @NotNull HttpHeaders headers;
    private final @NotNull HttpBody body;

    public HttpRequestImpl (
            @NotNull HttpMethod method,
            @NotNull URI uri,
            @NotNull HttpHeaders headers,
            @NotNull HttpBody body
    ) {
        this.version = HttpVersion.HTTP_1_1;
        this.method = method;
        this.uri = uri;
        this.headers = headers;
        this.body = body;

        if (headers.getTarget() == Target.RESPONSE) {
            throw new IllegalTargetException("Header Targets do not match");
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
