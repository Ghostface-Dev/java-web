package ghostface.dev.http.impl;

import ghostface.dev.http.headers.HttpHeader;
import ghostface.dev.http.headers.HttpHeaderName;
import ghostface.dev.http.headers.Target;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.UnknownNullability;

import java.util.Objects;

public class SimpleHttpHeader<T> implements HttpHeader<T> {

    private final @NotNull HttpHeaderName<T> key;
    private final @UnknownNullability T value;
    private final @NotNull Target target;

    public SimpleHttpHeader(@NotNull HttpHeaderName<T> key, @UnknownNullability T value, @NotNull Target target) {
        if (key.target() != target) {
            throw new IllegalArgumentException("Header and name target do not match");
        }
        this.key = key;
        this.value = value;
        this.target = target;
    }

    @Override
    public @NotNull HttpHeaderName<T> getKey() {
        return key;
    }

    @Override
    public @UnknownNullability T getValue() {
        return value;
    }

    @Override
    public @NotNull Target getTarget() {
        return target;
    }

    @Override
    public boolean equals(@Nullable Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        @NotNull SimpleHttpHeader<?> that = (SimpleHttpHeader<?>) object;
        return Objects.equals(key, that.key);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(key);
    }
}
