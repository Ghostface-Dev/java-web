package ghostface.dev.mapping;

import com.google.gson.JsonElement;
import ghostface.dev.exception.IllegalValueException;
import ghostface.dev.mapping.column.Columns;
import ghostface.dev.mapping.key.Key;
import ghostface.dev.mapping.column.Column;
import ghostface.dev.mapping.table.Table;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public interface Data {

    @NotNull Key<?> getKey();

    @NotNull Object getValue(@NotNull Column<?> column);

    @NotNull Object getValue(@NotNull String columnLabel);

    @NotNull Optional<Column<?>> getColumn(@NotNull Object object);

    @NotNull Columns getColumns();

    @NotNull JsonElement serialize();

    @NotNull Object @NotNull [] getValues();

    @NotNull Table<?> getTable();

    void putValue(@NotNull Object value, @NotNull Column<?> column) throws IllegalValueException, IllegalStateException;

    boolean contains(@NotNull Object value);
}
