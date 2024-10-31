package ghostface.dev.mapping;

import ghostface.dev.type.ConcreteType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public abstract class Key<T>  {

    private final @NotNull T value;

    protected Key(@NotNull T value) {
        this.value = value;
    }

    public final @NotNull T getValue() {
        return value;
    }

    public abstract @NotNull ConcreteType<T> concreteType();

    @Override
    public final @NotNull String toString() {
        return value.toString();
    }

    @Override
    public final boolean equals(@Nullable Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        @NotNull Key<?> key = (Key<?>) o;
        return Objects.equals(value, key.value);
    }

    @Override
    public final int hashCode() {
        return Objects.hashCode(value);
    }
}
