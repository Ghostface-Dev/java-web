package ghostface.dev.response;

import ghostface.dev.HttpStatus;
import ghostface.dev.HttpVersion;
import org.jetbrains.annotations.NotNull;

public interface HttpResponse {

    @NotNull HttpStatus getStatus();

    @NotNull HttpVersion getVersion();

}
