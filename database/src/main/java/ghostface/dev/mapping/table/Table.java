package ghostface.dev.mapping.table;

import ghostface.dev.exception.IllegalValueException;
import ghostface.dev.exception.TableException;
import ghostface.dev.mapping.Data;
import ghostface.dev.mapping.column.Columns;
import ghostface.dev.mapping.key.Key;
import ghostface.dev.operation.Crud;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Set;

public interface Table<T extends Key<?>> extends Crud<T> {

    @NotNull Columns getColumns();

    @NotNull Set<@NotNull T> getKeys();

    @NotNull Optional<Data> getData(@NotNull T key);

    boolean contains(@NotNull T key);

    boolean contains(@NotNull Data data);

    // Crud Implementation

    @Override
    boolean deleteAll(@NotNull T key);

    @Override
    boolean deleteAll(@NotNull Data value);

    @Override
    boolean createEmpty(@NotNull T key);

    @Override
    boolean create(@NotNull T key, @NotNull Data data) throws TableException;

    @Override
    @NotNull LinkedList<Data> getAll();

    @Override
    @NotNull LinkedList<Data> getAll(@NotNull Object value);

    @Override
    @NotNull Optional<Data> get(@NotNull T key);

    @Override
    void setValue(@NotNull T key, @NotNull Object oldValue, @NotNull Object value) throws IllegalValueException, TableException;

    @Override
    void setAll(@NotNull T key, @NotNull Data values) throws TableException;
}
