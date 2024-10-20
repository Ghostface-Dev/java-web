package ghostface.dev.http.impl;

import com.google.gson.JsonObject;
import ghostface.dev.http.HttpStatus;
import ghostface.dev.http.HttpVersion;
import ghostface.dev.http.body.HttpBody;
import ghostface.dev.http.element.HttpResponse;
import ghostface.dev.http.exception.HttpException;
import ghostface.dev.http.exception.MediaParseException;
import ghostface.dev.http.exception.header.HttpHeaderException;
import ghostface.dev.http.headers.HttpHeaderName;
import ghostface.dev.http.headers.HttpHeaders;
import ghostface.dev.http.headers.Target;
import ghostface.dev.http.media.MediaType;
import jdk.internal.util.ByteArray;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class HttpResponseImpl implements HttpResponse {

    private final @NotNull HttpVersion version;
    private final @NotNull HttpStatus status;
    private final @NotNull HttpHeaders headers;
    private final @NotNull HttpBody body;

    public HttpResponseImpl(@NotNull HttpStatus status, @NotNull HttpHeaders headers) throws HttpHeaderException {
        this.version = HttpVersion.HTTP_1_1;
        this.status = status;
        this.headers = headers;
        this.body = HttpBody.empty();

        if (headers.getTarget() != Target.RESPONSE) {
            throw new HttpHeaderException("Header Targets do not match");
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
    public byte @NotNull [] getBytes() {
        return (getVersion() + "\r\n" + getStatus() + "\r\n" + getHeaders() + "\r\n\r\n").getBytes(StandardCharsets.UTF_8);
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
