package ghostface.dev.mapping.table;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
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
        if (columns.isEmpty()) {
            throw new IllegalArgumentException("Columns cannot be null");
        }
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
    public @NotNull JsonElement serialize() {
        @NotNull JsonObject object = new JsonObject();
        index.forEach((key, data) -> object.add(key.getValue().toString(), data.serialize()));
        return object;
    }

    @Override
    public @NotNull JsonElement serializeByKey() {
        @NotNull JsonObject object = new JsonObject();
        getKeys().forEach(key -> object.addProperty("key", key.getValue().toString()));
        return object;
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
    public void setAll(@NotNull T key, @NotNull Data<T> data) throws TableException {
        if (!contains(data.getKey())) {
            throw new TableException("Key does not belong to this table");
        } else synchronized (lock) {
            index.put(key, data);
        }
    }
}
