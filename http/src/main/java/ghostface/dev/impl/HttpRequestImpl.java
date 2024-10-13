package ghostface.dev.impl;

import ghostface.dev.body.HttpBody;
import ghostface.dev.headers.HttpHeaders;
import ghostface.dev.message.HttpMethod;
import ghostface.dev.request.HttpRequest;
import ghostface.dev.version.HttpVersion;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.net.URI;

public final class HttpRequestImpl implements HttpRequest {

    private final @NotNull HttpMethod method;
    private final @NotNull URI uri;
    private final @NotNull HttpVersion version;
    private final @NotNull HttpHeaders headers;
    private final @Nullable HttpBody body;

    public HttpRequestImpl(
            @NotNull HttpMethod method,
            @NotNull URI uri,
            @NotNull HttpVersion version,
            @NotNull HttpHeaders headers,
            @Nullable HttpBody body
    ) {
        this.method = method;
        this.uri = uri;
        this.version = version;
        this.headers = headers;
        this.body = body;
    }

    @Override
    public @NotNull HttpMethod getMethod() {
        return method;
    }

    @Override
    public @NotNull URI getUri() {
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
    public @Nullable HttpBody getBody() {
        return body;
    }

    @Override
    public boolean hasBody() {
        return body != null;
    }
}
