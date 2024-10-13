package ghostface.dev.request;

import ghostface.dev.body.HttpBody;
import ghostface.dev.headers.HttpHeaders;
import ghostface.dev.impl.HttpRequestImpl;
import ghostface.dev.impl.HttpResponseImpl;
import ghostface.dev.media.MediaType;
import ghostface.dev.message.HttpMethod;
import ghostface.dev.message.HttpStatus;
import ghostface.dev.response.HttpResponse;
import ghostface.dev.version.HttpVersion;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.net.URI;


public interface HttpRequest {

    static HttpRequest create(
            @NotNull HttpMethod method,
            @NotNull URI uri,
            @NotNull HttpVersion version,
            @NotNull HttpHeaders headers
    ) {
        return new HttpRequestImpl(method, uri, version, headers, null);
    }

    static HttpRequest create(
            @NotNull HttpMethod method,
            @NotNull URI uri,
            @NotNull HttpVersion version,
            @NotNull HttpHeaders headers,
            @NotNull HttpBody body
    ) {
        return new HttpRequestImpl(method, uri, version, headers, body);
    }

    @NotNull HttpMethod getMethod();

    @NotNull URI getUri();

    @NotNull HttpVersion getVersion();

    @NotNull HttpHeaders getHeaders();

    @Nullable HttpBody getBody();

    boolean hasBody();
}
