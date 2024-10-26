package ghostface.dev.mapping;

import com.google.gson.JsonElement;
import ghostface.dev.exception.IllegalValueException;
import ghostface.dev.mapping.column.Columns;
import ghostface.dev.mapping.key.Key;
import ghostface.dev.mapping.column.Column;
import org.jetbrains.annotations.NotNull;

public interface Data {

    @NotNull Key<?> getKey();

    @NotNull Object getValue(@NotNull Column<?> column);

    @NotNull Object getValue(@NotNull String columnLabel);

    @NotNull Column<?> getColumn(@NotNull Object object) throws IllegalArgumentException;

    @NotNull Columns getColumns();

    @NotNull JsonElement serialize();

    @NotNull Object @NotNull [] getValues();

    void putValue(@NotNull Object value, @NotNull Column<?> column) throws IllegalValueException, IllegalStateException;
}
