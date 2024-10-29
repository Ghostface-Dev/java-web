package ghostface.dev.mapping.column;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public abstract class AbstractColumn<T> implements Column<T> {

    private final @NotNull String label;

    public AbstractColumn(@NotNull String label) {
        this.label = label;
    }

    @Override
    public @NotNull String label() {
        return label;
    }

    @Override
    public final boolean equals(@Nullable Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        @NotNull AbstractColumn<?> that = (AbstractColumn<?>) o;
        return label.equalsIgnoreCase(that.label);
    }

    @Override
    public final int hashCode() {
        return Objects.hashCode(label.toLowerCase());
    }
}
