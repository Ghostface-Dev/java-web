package ghostface.dev.util;

import ghostface.dev.body.HttpBody;
import ghostface.dev.exception.HttpFormatException;
import ghostface.dev.headers.HttpHeaders;
import ghostface.dev.impl.HttpUtilsImpl;
import ghostface.dev.media.MediaType;
import ghostface.dev.message.HttpMethod;
import ghostface.dev.version.HttpVersion;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.net.URI;

public interface HttpUtils {

    static @NotNull HttpUtils getInstance() {
        return HttpUtilsImpl.instance;
    }

    static void setInstance(@NotNull HttpUtils instance) {
        HttpUtilsImpl.instance = instance;
    }

    @NotNull HttpVersion getVersion(@NotNull String httpElement) throws HttpFormatException;

    @NotNull HttpMethod getMethod(@NotNull String httpElement) throws HttpFormatException;

    @NotNull URI getURI(@NotNull String httpElement) throws HttpFormatException;

    @NotNull HttpHeaders of(@NotNull String httpElement) throws HttpFormatException;

    @Nullable MediaType<?> getMedia(@NotNull String httpElement) throws HttpFormatException;

    @Nullable HttpBody getBody(@NotNull String httpElement) throws HttpFormatException;
}
