package ghostface.dev.impl;

import ghostface.dev.headers.HttpHeaders;
import ghostface.dev.media.MediaType;
import ghostface.dev.message.HttpStatus;
import ghostface.dev.response.HttpResponse;
import ghostface.dev.version.HttpVersion;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class HttpResponseImpl implements HttpResponse {

    private final @NotNull HttpVersion version;
    private final @NotNull HttpStatus status;
    private final @NotNull HttpHeaders headers;
    private final @Nullable MediaType<?> mediaType;

    public HttpResponseImpl(
            @NotNull HttpVersion version,
            @NotNull HttpStatus status,
            @NotNull HttpHeaders headers,
            @Nullable MediaType<?> mediaType
    ) {
        this.version = version;
        this.status = status;
        this.headers = headers;
        this.mediaType = mediaType;
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
    public @Nullable MediaType<?> getMedia() {
        return mediaType;
    }
}
