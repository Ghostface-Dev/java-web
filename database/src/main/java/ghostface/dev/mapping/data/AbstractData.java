package ghostface.dev.mapping.data;

import ghostface.dev.mapping.column.Column;
import ghostface.dev.mapping.column.Columns;
import ghostface.dev.mapping.key.Key;
import ghostface.dev.mapping.table.Table;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.UnknownNullability;

import java.util.*;

public abstract class AbstractData<T extends Key<?>> implements Data<T> {

    protected final @NotNull Object lock = new Object();
    protected final @NotNull Map<@NotNull Column<?>, @UnknownNullability Object> values = new LinkedHashMap<>();

    private final @NotNull T key;
    private final @NotNull Table<T> table;
    private final @NotNull Columns columns;

    public AbstractData(@NotNull T key, @NotNull Table<T> table) {
        this.key = key;
        this.table = table;
        this.columns = table.getColumns();
        columns.forEach(column -> values.put(column, null));
    }

    @Override
    public @NotNull T getKey() {
        return key;
    }

    @Override
    public @NotNull Table<T> getTable() {
        return table;
    }

    @Override
    public @NotNull Columns getColumns() {
        return columns;
    }

    @Override
    public <E> void put(@NotNull Column<E> column, @UnknownNullability E value) throws IllegalArgumentException {
        if (!contains(column)) {
            throw new IllegalArgumentException("The column does not belong to this data");
        } else synchronized (lock) {
            values.put(column, value);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public <E> @NotNull Iterator<Column<E>> getColumn(@UnknownNullability E value) {
         return values.entrySet().stream()
                 .filter(entry -> entry.getValue().equals(value))
                 .map(entry -> (Column<E>) entry.getKey()).iterator();
    }

    @Override
    @SuppressWarnings("unchecked")
    public <E> @UnknownNullability E getValue(@NotNull Column<E> column) throws IllegalArgumentException {
        if (!contains(column)) {
            throw new IllegalArgumentException("The column does not belong to this data");
        } else {
            return (E) values.get(column);
        }
    }

    @Override
    public boolean contains(@Nullable Object value) {
        return values.containsValue(value);
    }

    @Override
    public boolean contains(@NotNull Column<?> column) {
        return Data.super.contains(column);
    }

    @Override
    public @NotNull Object @UnknownNullability [] getValues() {
        return values.values().toArray();
    }

    @Override
    public final boolean equals(@Nullable Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        @NotNull AbstractData<?> that = (AbstractData<?>) o;
        return Objects.equals(key, that.key);
    }

    @Override
    public final int hashCode() {
        return Objects.hashCode(key);
    }
}
