package ghostface.dev.mapping.data;

import com.google.gson.JsonElement;
import ghostface.dev.impl.data.DataInteger;
import ghostface.dev.impl.data.DataLong;
import ghostface.dev.impl.data.DataUUID;
import ghostface.dev.mapping.column.Columns;
import ghostface.dev.mapping.key.Key;
import ghostface.dev.mapping.column.Column;
import ghostface.dev.mapping.table.Table;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.UnknownNullability;

import java.util.Iterator;
import java.util.UUID;

public interface Data<T extends Key<?>> {

    static @NotNull Data<Key<UUID>> getDefault(@NotNull Key<UUID> key, @NotNull Table<Key<UUID>> table) {
        return new DataUUID(key, table);
    }

    static @NotNull Data<Key<Integer>> getDataInteger(@NotNull Key<Integer> key, @NotNull Table<Key<Integer>> table) {
        return new DataInteger(key, table);
    }

    static @NotNull Data<Key<Long>> getDataLong(@NotNull Key<Long> key, @NotNull Table<Key<Long>> table) {
        return new DataLong(key, table);
    }

    // Objects

    @NotNull T getKey();

    <E> @UnknownNullability E getValue(@NotNull Column<E> column) throws IllegalArgumentException;

    <E> @NotNull Iterator<Column<E>> getColumn(@UnknownNullability E value);

    @NotNull Columns getColumns();

    @NotNull JsonElement serialize();

    @NotNull Object @UnknownNullability [] getValues();

    @NotNull Table<T> getTable();

    <E> void put(@NotNull Column<E> column, @UnknownNullability E value) throws IllegalArgumentException;

    boolean contains(@Nullable Object value);

    default boolean contains(@NotNull Column<?> column) {
        return getColumns().contains(column);
    }
}
