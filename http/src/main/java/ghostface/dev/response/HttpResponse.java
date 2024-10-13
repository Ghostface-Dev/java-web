package ghostface.dev.response;

import ghostface.dev.body.HttpBody;
import ghostface.dev.headers.HttpHeaders;
import ghostface.dev.impl.HttpResponseImpl;
import ghostface.dev.media.MediaType;
import ghostface.dev.message.HttpStatus;
import ghostface.dev.version.HttpVersion;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public interface HttpResponse {

    static HttpResponse create(
            @NotNull HttpVersion version,
            @NotNull HttpStatus status,
            @NotNull HttpHeaders headers
    ) {
        return new HttpResponseImpl(version, status, headers, null);
    }

    static HttpResponse create(
            @NotNull HttpVersion version,
            @NotNull HttpStatus status,
            @NotNull HttpHeaders headers,
            @NotNull MediaType<?> mediaType
    ) {
        return new HttpResponseImpl(version, status, headers, mediaType);
    }

    @NotNull HttpVersion getVersion();

    @NotNull HttpStatus getStatus();

    @NotNull HttpHeaders getHeaders();

    @Nullable MediaType<?> getMedia();

    default @Nullable HttpBody getBody() {
        return getMedia() == null ? getMedia().getBody() : null;
    }
}
