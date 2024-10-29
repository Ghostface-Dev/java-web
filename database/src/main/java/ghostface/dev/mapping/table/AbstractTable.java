package ghostface.dev.mapping.table;

import ghostface.dev.exception.TableException;
import ghostface.dev.mapping.column.Columns;
import ghostface.dev.mapping.data.Data;
import ghostface.dev.mapping.key.Key;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.UnknownNullability;

import java.util.*;

public abstract class AbstractTable<T extends Key<?>> implements Table<T> {

    protected final @NotNull Object lock = new Object();
    protected final @NotNull Map<@NotNull T, @UnknownNullability Data<T>> index = new LinkedHashMap<>();

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
        return Collections.unmodifiableSet(index.keySet());
    }

    @Override
    public boolean create(@NotNull Data<T> data) {
        if (contains(data.getKey())) {
            return false;
        } else synchronized (lock) {
            index.put(data.getKey(), data);
            return true;
        }
    }

    @Override
    public @NotNull LinkedList<Data<T>> getAll() {
        return new LinkedList<>(index.values());
    }

    @Override
    public boolean setAll(@NotNull Data<T> data) throws TableException {
        if (!contains(data.getKey())) {
            return false;
        } else synchronized (lock) {
            index.put(data.getKey(), data);
            return true;
        }
    }
}
