package ghostface.dev.mapping.column;

import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public interface Columns extends Iterable<@NotNull Column<?>>, Cloneable {

    boolean add(@NotNull Column<?> column);

    @NotNull Optional<@NotNull Column<?>> getColumn(@NotNull String label);

    @NotNull List<@NotNull String> getLabels();

    boolean remove(@NotNull Column<?> column);

    boolean remove(@NotNull String label);

    boolean contains(@NotNull Column<?> column);

    boolean contains(@NotNull String label);

    @NotNull Collection<@NotNull Column<?>> getAsCollection();

    int size();

    @NotNull Columns clone();

    @Override
    @NotNull Iterator<@NotNull Column<?>> iterator();
}
