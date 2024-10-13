package ghostface.dev.impl;

import ghostface.dev.body.HttpBody;
import ghostface.dev.exception.HttpFormatException;
import ghostface.dev.headers.HttpHeaders;
import ghostface.dev.media.MediaType;
import ghostface.dev.message.HttpMethod;
import ghostface.dev.util.HttpUtils;
import ghostface.dev.version.HttpVersion;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


import java.net.URI;

public class HttpUtilsImpl implements HttpUtils {

    public static @NotNull HttpUtils instance = new HttpUtilsImpl();

    @Override
    public @NotNull HttpVersion getVersion(@NotNull String httpElement) throws HttpFormatException {
        return null;
    }

    @Override
    public @NotNull HttpMethod getMethod(@NotNull String httpElement) throws HttpFormatException {
        return null;
    }

    @Override
    public @NotNull URI getURI(@NotNull String httpElement) throws HttpFormatException {
        return null;
    }

    @Override
    public @NotNull HttpHeaders of(@NotNull String httpElement) throws HttpFormatException {
        return null;
    }

    @Override
    public @Nullable MediaType<?> getMedia(@NotNull String httpElement) throws HttpFormatException {
        return null;
    }

    @Override
    public @Nullable HttpBody getBody(@NotNull String httpElement) throws HttpFormatException {
        return null;
    }
}
