package ghostface.dev.mapping.column;

import ghostface.dev.type.DataType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Column<T extends DataType> {

    @NotNull String label();

    @NotNull T dataType();

    boolean isUnique();

    @Override
    boolean equals(@Nullable Object o);

    @Override
    int hashCode();
}
