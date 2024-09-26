package ghostface.dev.headers;

import ghostface.dev.message.Name;
import ghostface.dev.message.Target;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public interface HttpHeader<T> {

    @NotNull Name getName();

    @NotNull Target getTarget();

    @NotNull T getValue();

    @Override
    @NotNull String toString();
}
