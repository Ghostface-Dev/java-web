package ghostface.dev.element;

import ghostface.dev.HttpStatus;
import org.jetbrains.annotations.NotNull;

public interface HttpResponse extends HttpElement {

    @NotNull
    HttpStatus getStatus();

    byte @NotNull [] getBytes();
}
