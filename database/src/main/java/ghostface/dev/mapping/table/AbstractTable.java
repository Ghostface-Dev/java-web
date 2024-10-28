package ghostface.dev.mapping.table;

import ghostface.dev.exception.TableException;
import ghostface.dev.exception.data.IllegalValueException;
import ghostface.dev.mapping.column.Column;
import ghostface.dev.mapping.data.Data;
import ghostface.dev.mapping.column.Columns;
import ghostface.dev.mapping.key.Key;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.UnknownNullability;

import java.util.*;
import java.util.stream.Collectors;

public abstract class AbstractTable<T extends Key<?>> implements Table<T> {

    private final @NotNull Map<@NotNull T, Data<T>> index = new LinkedHashMap<>();
    private final @NotNull Object lock = new Object();
    private final @NotNull Columns columns;

    public AbstractTable(@NotNull Columns columns) {
        this.columns = columns;
    }

    @Override
    public @NotNull Columns getColumns() {
        return columns;
    }

    @Override
    public @NotNull Set<@NotNull T> getKeys() {
        return index.keySet();
    }

    @Override
    public boolean contains(@NotNull T key) {
        return Table.super.contains(key);
    }

    @Override
    public boolean contains(@NotNull Data<T> data) {
        return Table.super.contains(data);
    }

    @Override
    public synchronized boolean deleteAll(@NotNull T key) {
        return Table.super.deleteAll(key);
    }

    @Override
    public synchronized boolean deleteAll(@NotNull Data<T> value) {
        return Table.super.deleteAll(value);
    }

    @Override
    public boolean create(@NotNull Data<T> data) {
        if (index.containsKey(data.getKey())) {
            return false;
        } else synchronized(lock) {
            index.put(data.getKey(), data);
            return true;
        }
    }

    @Override
    public @NotNull LinkedList<Data<T>> getAll() {
        return new LinkedList<>(index.values());
    }

    @Override
    public @NotNull LinkedList<Data<T>> getAll(@UnknownNullability Object value, @NotNull Column<?> column) throws IllegalArgumentException {
        return getAll().stream().filter(data -> data.getValue(column).equals(value)).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    public @NotNull Optional<Data<T>> get(@NotNull T key) {
        return Optional.ofNullable(index.get(key));
    }

    @Override
    public void setValue(@NotNull T key, @UnknownNullability Object value, @NotNull Column<?> column) throws TableException, IllegalValueException {
        if (!contains(key)) {
            throw new TableException("Key does not exist");
        } else if (!getColumns().contains(column)) {
            throw new TableException("Column is not belong to table");
        } else {
            index.get(key).put(value, column);
        }
    }

    @Override
    public void setAll(@NotNull T key, @NotNull Data<T> data) throws TableException {
        if (!contains(key)) {
            throw new TableException("The key does not exist");
        } else synchronized (lock) {
            index.replace(key, data);
        }
    }
}
