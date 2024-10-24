package ghostface.dev.mapping.column;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.UnknownNullability;

public interface Column<T> {

    @NotNull String label();

    boolean isUnique();

    @UnknownNullability T getValue();

    void setValue(T value);

    @Override
    boolean equals(@Nullable Object object);

    @Override
    int hashCode();
}
