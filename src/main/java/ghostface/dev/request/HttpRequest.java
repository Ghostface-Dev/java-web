package ghostface.dev.request;

import ghostface.dev.protocol.HttpVersion;
import ghostface.dev.Method;
import org.jetbrains.annotations.NotNull;

import java.net.URI;

public interface HttpRequest {

    @NotNull Method getMethod();

    @NotNull URI getPath();

    @NotNull HttpVersion getVersion();

    byte[] getBytes();
}
