package ghostface.dev.operation;

import ghostface.dev.exception.data.DataException;
import ghostface.dev.mapping.Column;
import ghostface.dev.mapping.Data;
import ghostface.dev.mapping.Key;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.UnknownNullability;

import java.util.LinkedList;
import java.util.Optional;

public interface Selectable<T extends Key<?>> {

    @NotNull LinkedList<Data<T>> getAll();

    @NotNull LinkedList<Data<T>> getAll(@UnknownNullability Object value, @NotNull Column<?> column) throws DataException;

    @NotNull Optional<Data<T>> get(@NotNull T key);

}
