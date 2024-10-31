package ghostface.dev.mapping.provider;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import ghostface.dev.mapping.Data;
import ghostface.dev.mapping.Key;
import ghostface.dev.mapping.Table;
import ghostface.dev.mapping.Column;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.UnknownNullability;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

public abstract class AbstractData<T extends Key<?>> extends Data<T> {

    private final @NotNull Map<@NotNull Column<?>, @Nullable Object> datas;

    protected AbstractData(@NotNull T key, @NotNull Table<T> table, @NotNull Map<@NotNull Column<?>, @Nullable Object> datas) {
        super(key, table);
        this.datas = datas;
    }

    @Override
    public boolean contains(@Nullable Object value) {
        return datas.values().stream().anyMatch(o -> Objects.equals(o, value));
    }

    @Override
    @SuppressWarnings("unchecked")
    public <E> @UnknownNullability E getValue(@NotNull Column<E> column) throws IllegalArgumentException {
        if (!contains(column)) {
            throw new IllegalArgumentException("Columns does not belong to this data");
        } else if (datas.get(column) == null) {
            return null;
        } else {
            return (E) datas.get(column);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public @NotNull <E> Iterator<Column<E>> getColumn(@Nullable E value) {
        return datas.entrySet().stream()
                .filter(entry -> Objects.equals(entry.getValue(), value))
                .map(entry -> (Column<E>) entry.getKey())
                .iterator();
    }

    @Override
    public @NotNull JsonElement serialize() {
        @NotNull JsonObject object = new JsonObject();
        datas.forEach((column, obj) -> object.addProperty(column.label(), String.valueOf(obj)));
        return object;
    }

    @Override
    public @NotNull Object @UnknownNullability [] getValues() {
        return datas.values().toArray();
    }

    @Override
    public <E> void put(@NotNull Column<E> column, @UnknownNullability E value) throws IllegalArgumentException {
        if (!contains(column)) {
            throw new IllegalArgumentException("Column does not belong to this Data");
        } else synchronized (this) {
            datas.put(column, value);
        }
    }
}
