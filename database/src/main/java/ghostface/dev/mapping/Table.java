package ghostface.dev.mapping;

import com.google.gson.JsonElement;
import ghostface.dev.exception.TableException;
import ghostface.dev.operation.Crud;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.UnknownNullability;
import org.jetbrains.annotations.Unmodifiable;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public interface Table<T extends Key<?>> extends Crud<T> {

    @Unmodifiable @NotNull Columns getUnmodifiableColumns();

    @Unmodifiable @NotNull Set<@NotNull T> getKeys();

    default boolean contains(@NotNull T key) {
        return getKeys().contains(key);
    }

    default boolean contains(@NotNull Data<T> data) {
        return this.contains(data.getKey());
    }

    @NotNull JsonElement serialize();

    @NotNull JsonElement serializeByKey();

    // Crud Implementation

    @Override
    boolean deleteAll(@NotNull T key);

    @Override
    default boolean deleteAll(@NotNull Data<T> data) {
        return this.deleteAll(data.getKey());
    }

    @Override
    boolean create(@NotNull Data<T> data);

    @Override
    @Unmodifiable @NotNull LinkedList<Data<T>> getAll();

    @Override
    default @NotNull LinkedList<Data<T>> getAll(@UnknownNullability Object value, @NotNull Column<?> column) throws IllegalArgumentException {
        return getAll().stream()
                .filter(data -> data.getValue(column) == null ? value == null : data.getColumn(column).equals(value))
                .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    default @NotNull Optional<Data<T>> get(@NotNull T key) {
        return getAll().stream().filter(data -> data.getKey().equals(key)).findFirst();
    }

    @Override
    void setAll(@NotNull T key, @NotNull Data<T> data) throws TableException;
}
