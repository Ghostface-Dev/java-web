package ghostface.dev.headers;

import ghostface.dev.message.HttpName;
import ghostface.dev.message.Target;
import org.jetbrains.annotations.NotNull;

public interface HttpHeader<T> {

    @NotNull HttpName getName();

    @NotNull Target getTarget();

    @NotNull T getValue();

    @Override
    @NotNull String toString();
}
