package ghostface.dev.response;

import ghostface.dev.body.HttpBody;
import ghostface.dev.header.HttpHeader;
import ghostface.dev.status.HttpStatus;
import ghostface.dev.protocol.HttpVersion;
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
