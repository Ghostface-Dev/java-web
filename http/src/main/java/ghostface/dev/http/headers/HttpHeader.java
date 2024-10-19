package ghostface.dev.http.headers;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.UnknownNullability;

public interface HttpHeader<T> {

    @NotNull HttpHeaderName getKey();

    @UnknownNullability T getValue();

    @NotNull Target getTarget();

    @NotNull HttpHeader<T> read(@NotNull String string);

    @NotNull String write(@NotNull HttpHeader<T> header);

    @Override
    boolean equals(@Nullable Object o);

    @Override
    int hashCode();
}
