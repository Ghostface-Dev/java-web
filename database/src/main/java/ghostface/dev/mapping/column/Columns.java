package ghostface.dev.mapping.column;

import ghostface.dev.exceptions.IllegalValueException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.stream.Stream;

public final class Columns<T> implements CollumnCollections {

    private final @NotNull List<@NotNull Column<?>> columns = new LinkedList<>();
    private final @NotNull ColumnKey<T> key;
    private volatile int capacity;

    public Columns(@NotNull ColumnKey<T> key, int initialCapacity) {
        this.key = key;
        this.capacity = initialCapacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public @NotNull ColumnKey<T> getKey() {
        return key;
    }

    public synchronized void setCapacity(int newCapacity) {
        if (newCapacity < columns.size()) {
            throw new IllegalValueException("The number of columns exceeds the new capacity");
        } else {
            this.capacity = newCapacity;
        }
    }

    // Implementations

    @Override
    public boolean add(@NotNull Column<?> column) {
        if (stream().anyMatch(c -> c.label().equalsIgnoreCase(column.label()))) {
            return false;
        } else if (column.isUnique() && stream().anyMatch(c -> c.getValue().equals(column.getValue()))) {
            return false;
        } else {
            return columns.add(column);
        }
    }

    @Override
    public boolean put(@NotNull Column<?> column) {
        return remove(column) && add(column);
    }

    @Override
    public boolean remove(@NotNull Object column) {
        return columns.remove(column);
    }

    @Override
    public @NotNull <V> Optional<@NotNull Column<V>> get(@NotNull Column<V> column) {
        return Optional.empty();
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public @NotNull Iterator<Column<?>> iterator() {
        return columns.iterator();
    }

    @Override
    public boolean contains(@Nullable Column<?> column) {
        return stream().anyMatch(c -> c.equals(column));
    }

    public @NotNull Object @NotNull [] toArray() {
        return new Object[0];
    }

    public @NotNull Stream<Column<?>> stream() {
        return columns.stream();
    }
}
