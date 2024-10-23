package ghostface.dev.http.headers;


import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.UnknownNullability;


public interface HttpHeader<T> {

    @NotNull HttpHeaderName<T> getKey();

    @UnknownNullability T getValue();

    void setValue(T value);

    @NotNull Target getTarget();

    @Override
    boolean equals(@Nullable Object o);

    @Override
    int hashCode();

    default @NotNull String getAsString() {
        return getKey().serialize(this);
    }
}
