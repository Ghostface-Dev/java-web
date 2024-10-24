package ghostface.dev.mapping.column;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.Optional;

public interface CollumnCollections extends Iterable<Column<?>> {

    boolean add(@NotNull Column<?> column);

    boolean put(@NotNull Column<?> column);

    boolean remove(@NotNull Object column);

    <T> @NotNull Optional<@NotNull Column<T>> get(@NotNull Column<T> column);

    int size();

    @Override
    @NotNull Iterator<Column<?>> iterator();

    boolean contains(@NotNull Column<?> o);

    default boolean isEmpty() {
        return size() == 0;
    }
}
