package ghostface.dev.mapping;

import ghostface.dev.impl.column.ColumnsImpl;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public abstract class Columns implements Iterable<@NotNull Column<?>> {

    protected Columns() {
    }

    public abstract boolean add(@NotNull Column<?> column);

    public abstract @NotNull Optional<@NotNull Column<?>> getColumn(@NotNull String label);

    public abstract @NotNull List<@NotNull String> getLabels();

    public abstract boolean remove(@NotNull Column<?> column);

    public abstract boolean remove(@NotNull String label);

    public abstract boolean contains(@NotNull Column<?> column);

    public abstract boolean contains(@NotNull String label);

    public abstract @NotNull Column<?> @NotNull [] toArray();

    @Override
    public abstract @NotNull Iterator<@NotNull Column<?>> iterator();

    public int size() {
        return toArray().length;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public final @Unmodifiable @NotNull Columns getUnmodifiable() {
        return new UnmodifiableColumn();
    }

    final class UnmodifiableColumn extends Columns {

        @Override
        public boolean add(@NotNull Column<?> column) {
            throw new UnsupportedOperationException("Columns is Unmodifiable");
        }

        @Override
        public @NotNull Optional<@NotNull Column<?>> getColumn(@NotNull String label) {
            return Columns.this.getColumn(label);
        }

        @Override
        public @NotNull List<@NotNull String> getLabels() {
            return Columns.this.getLabels();
        }

        @Override
        public boolean remove(@NotNull Column<?> column) {
            throw new UnsupportedOperationException("Columns is Unmodifiable");
        }

        @Override
        public boolean remove(@NotNull String label) {
            throw new UnsupportedOperationException("Columns is Unmodifiable");
        }

        @Override
        public boolean contains(@NotNull Column<?> column) {
            return Columns.this.contains(column);
        }

        @Override
        public boolean contains(@NotNull String label) {
            return Columns.this.contains(label);
        }

        @Override
        public @NotNull Column<?> @NotNull [] toArray() {
            return Columns.this.toArray();
        }

        @Override
        public int size() {
            return Columns.this.size();
        }

        @Override
        public @NotNull Iterator<@NotNull Column<?>> iterator() {
            return Columns.this.iterator();
        }
    }
}
