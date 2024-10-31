package ghostface.dev.mapping;

import ghostface.dev.type.ConcreteType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public abstract class Column<T> {

    private final @NotNull String label;

    protected Column(@NotNull String label) {
        if (label.trim().isEmpty()) throw new IllegalArgumentException("String cannot be null");
        this.label = label;
    }

    public @NotNull String label() {
        return label;
    }

    public abstract @NotNull ConcreteType<T> concreteType();

    @Override
    public final boolean equals(@Nullable Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        @NotNull Column<?> that = (Column<?>) o;
        return label.equalsIgnoreCase(that.label);
    }

    @Override
    public final int hashCode() {
        return Objects.hashCode(label.toLowerCase());
    }
}
