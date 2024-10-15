package ghostface.dev.http.element;

import ghostface.dev.http.HttpStatus;
import org.jetbrains.annotations.NotNull;

public interface HttpResponse extends HttpElement {

    @NotNull HttpStatus getStatus();

}
