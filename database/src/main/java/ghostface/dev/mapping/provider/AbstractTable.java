package ghostface.dev.mapping.provider;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import ghostface.dev.exception.TableException;
import ghostface.dev.mapping.Table;
import ghostface.dev.mapping.Columns;
import ghostface.dev.mapping.Data;
import ghostface.dev.mapping.Key;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.UnknownNullability;
import org.jetbrains.annotations.Unmodifiable;

import java.util.*;

public abstract class AbstractTable<T extends Key<?>> implements Table<T> {

    protected final @NotNull Object lock = new Object();
    protected final @NotNull Map<@NotNull T, @UnknownNullability Data<T>> index = new LinkedHashMap<>();

    private final @NotNull Columns unmodifiableColumns;

    public AbstractTable(@NotNull Columns columns) {
        if (columns.isEmpty()) {
            throw new IllegalArgumentException("Columns cannot be null");
        }
        this.unmodifiableColumns = columns.getUnmodifiable();
    }

    @Override
    public @Unmodifiable @NotNull Columns getUnmodifiableColumns() {
        return unmodifiableColumns;
    }

    @Override
    public @Unmodifiable @NotNull Set<@NotNull T> getKeys() {
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
    public @Unmodifiable @NotNull LinkedList<Data<T>> getAll() {
        return new LinkedList<>(index.values());
    }

    @Override
    public boolean deleteAll(@NotNull T key) {
        if (!contains(key)) {
            return false;
        } else synchronized (lock){
            index.remove(key);
            return true;
        }
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
