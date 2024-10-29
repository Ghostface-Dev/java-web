package ghostface.dev.mapping.column;

import ghostface.dev.type.ConcreteType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Column<T> {

    @NotNull String label();

    @NotNull ConcreteType<T> dataType();

    @Override
    boolean equals(@Nullable Object o);

    @Override
    int hashCode();
}
