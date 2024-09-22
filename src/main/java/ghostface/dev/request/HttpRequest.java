package ghostface.dev.request;

import ghostface.dev.header.HttpHeader;
import ghostface.dev.protocol.HttpVersion;
import ghostface.dev.Method;
import org.jetbrains.annotations.NotNull;

import java.net.URI;

public interface HttpRequest {

    @NotNull Method getMethod();

    @NotNull URI getPath();

    @NotNull HttpVersion getVersion();

    @NotNull HttpHeader[] getHeaders();

    byte[] getBytes();
}
