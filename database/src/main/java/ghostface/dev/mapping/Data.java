package ghostface.dev.mapping;

import com.google.gson.JsonElement;
import ghostface.dev.impl.data.DataInteger;
import ghostface.dev.impl.data.DataLong;
import ghostface.dev.impl.data.DataUUID;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.UnknownNullability;
import org.jetbrains.annotations.Unmodifiable;

import java.util.Iterator;
import java.util.Objects;
import java.util.UUID;

public abstract class Data<T extends Key<?>> {

    public static @NotNull Data<Key<UUID>> getDefault(@NotNull Key<UUID> key, @NotNull Table<Key<UUID>> table) {
        return new DataUUID(key, table);
    }

    public static @NotNull Data<Key<Integer>> getDataInteger(@NotNull Key<Integer> key, @NotNull Table<Key<Integer>> table) {
        return new DataInteger(key, table);
    }

    public static @NotNull Data<Key<Long>> getDataLong(@NotNull Key<Long> key, @NotNull Table<Key<Long>> table) {
        return new DataLong(key, table);
    }

    // Objects

    private final @NotNull T key;
    private final @NotNull Table<T> table;

    protected Data(@NotNull T key, @NotNull Table<T> table) {
        this.key = key;
        this.table = table;
    }

    public final @NotNull T getKey() {
        return key;
    }

    public @Unmodifiable @NotNull Columns getUnmodifiableColumns() {
        return table.getUnmodifiableColumns();
    }

    public @NotNull Table<T> getTable() {
        return table;
    }

    public boolean contains(@NotNull Column<?> column) {
        return getUnmodifiableColumns().contains(column);
    }

    public abstract boolean contains(@Nullable Object value);

    public abstract <E> @UnknownNullability E getValue(@NotNull Column<E> column) throws IllegalArgumentException;

    public abstract <E> @NotNull Iterator<Column<E>> getColumn(@Nullable E value);

    public abstract @NotNull JsonElement serialize();

    public abstract @NotNull Object @UnknownNullability [] getValues();

    public abstract <E> void put(@NotNull Column<E> column, @UnknownNullability E value) throws IllegalArgumentException;

    @Override
    public final boolean equals(@Nullable Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        @NotNull Data<?> data = (Data<?>) o;
        return Objects.equals(key, data.key);
    }

    @Override
    public final int hashCode() {
        return Objects.hashCode(key);
    }
}
