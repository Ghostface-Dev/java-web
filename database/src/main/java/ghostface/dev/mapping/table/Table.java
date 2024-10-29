package ghostface.dev.mapping.table;

import ghostface.dev.exception.TableException;
import ghostface.dev.mapping.column.Column;
import ghostface.dev.mapping.data.Data;
import ghostface.dev.mapping.column.Columns;
import ghostface.dev.mapping.key.Key;
import ghostface.dev.operation.Crud;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.UnknownNullability;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public interface Table<T extends Key<?>> extends Crud<T> {

    @NotNull Columns getColumns();

    @NotNull Set<@NotNull T> getKeys();

    default boolean contains(@NotNull T key) {
        return getKeys().contains(key);
    }

    default boolean contains(@NotNull Data<T> data) {
        return this.contains(data.getKey());
    }

    // Crud Implementation

    @Override
    default boolean deleteAll(@NotNull T key) {
        return getKeys().remove(key);
    }

    @Override
    default boolean deleteAll(@NotNull Data<T> data) {
        return this.deleteAll(data.getKey());
    }

    @Override
    boolean create(@NotNull Data<T> data);

    @Override
    @NotNull LinkedList<Data<T>> getAll();

    @Override
    default @NotNull LinkedList<Data<T>> getAll(@UnknownNullability Object value, @NotNull Column<?> column) throws IllegalArgumentException {
        return getAll().stream().filter(data -> data.getValue(column).equals(value)).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    default @NotNull Optional<Data<T>> get(@NotNull T key) {
        return getAll().stream().filter(data -> data.getKey().equals(key)).findFirst();
    }

    @Override
    void setAll(@NotNull T key, @NotNull Data<T> data) throws TableException;
}
