package ghostface.dev.headers;

import ghostface.dev.message.Target;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public interface HttpHeader<T> {

    @NotNull HeaderKey<T> getHeader();

}
