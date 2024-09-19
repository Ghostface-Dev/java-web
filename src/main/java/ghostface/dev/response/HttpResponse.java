package ghostface.dev.response;

import ghostface.dev.HttpBody;
import ghostface.dev.HttpStatus;
import ghostface.dev.HttpVersion;
import org.jetbrains.annotations.NotNull;

public interface HttpResponse {

    @NotNull HttpVersion getVersion();

    @NotNull HttpStatus getStatus();

    default @NotNull String getStatusMessage() {
        return getStatus().getMessage();
    }

    @NotNull HttpHeader[] getHeaders();

    @NotNull HttpBody getBody();

    byte[] getBytes();
}
