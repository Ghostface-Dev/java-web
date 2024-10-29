package ghostface.dev.impl.data;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import ghostface.dev.mapping.data.AbstractData;
import ghostface.dev.mapping.key.Key;
import ghostface.dev.mapping.table.Table;
import org.jetbrains.annotations.NotNull;

public final class DataLong extends AbstractData<Key<Long>> {

    public DataLong(@NotNull Key<Long> key, @NotNull Table<Key<Long>> table) {
        super(key, table);
    }

    @Override
    public @NotNull JsonElement serialize() {
        @NotNull JsonObject object = new JsonObject();
        values.forEach((column, obj) -> object.addProperty(column.label(), String.valueOf(obj)));
        return object;
    }
}
