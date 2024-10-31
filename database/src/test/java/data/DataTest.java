package data;

import com.google.gson.JsonObject;
import ghostface.dev.impl.column.ColumnsImpl;
import ghostface.dev.impl.column.StringColumn;
import ghostface.dev.impl.data.DataUUID;
import ghostface.dev.impl.table.UUIDTable;
import ghostface.dev.mapping.Column;
import ghostface.dev.mapping.Columns;
import ghostface.dev.mapping.Data;
import ghostface.dev.mapping.Key;
import ghostface.dev.impl.key.UUIDKey;
import ghostface.dev.mapping.Table;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.UUID;

public final class DataTest {

    private static final @NotNull Column<String> column = new StringColumn("name");
    private static final @NotNull Column<String> column1 = new StringColumn("description");
    private static final @NotNull Columns columns = new ColumnsImpl();

    static {
        columns.add(column);
        columns.add(column1);
    }

    private final @NotNull Table<Key<UUID>> table = new UUIDTable(columns);

    @Test
    public void test() {
        @NotNull Data<Key<UUID>> data = new DataUUID(UUIDKey.create(), table);
        data.put(column, "vinicius junior");
        data.put(column1, "hello world");

        @NotNull JsonObject object = data.serialize().getAsJsonObject();

        Assertions.assertTrue(object.has("name"));
        Assertions.assertTrue(object.has("description"));
        Assertions.assertEquals(object.get("name").getAsString(), "vinicius junior");
        Assertions.assertEquals(object.get("description").getAsString(), "hello world");

        Assertions.assertTrue(data.contains(column));
        Assertions.assertTrue(data.contains(column1));
        Assertions.assertTrue(data.contains("vinicius junior"));
        Assertions.assertTrue(data.contains("hello world"));

        data.put(column, "Shaolin");
        Assertions.assertTrue(data.contains("Shaolin"));
        Assertions.assertFalse(data.contains("vinicius junior"));

        Assertions.assertEquals(data.getValue(column), "Shaolin");
        Assertions.assertEquals(data.getValue(column1), "hello world");

        data.put(column1, "Shaolin");
        @NotNull Iterator<Column<String>> iterator = data.getColumn("Shaolin");

        Assertions.assertTrue(iterator.hasNext());
        iterator.next();
        Assertions.assertTrue(iterator.hasNext());
        iterator.next();
        Assertions.assertFalse(iterator.hasNext());

        Assertions.assertTrue(table.create(data));
        Assertions.assertTrue(table.contains(data));
        Assertions.assertFalse(table.create(data));
    }
}
