package ghostface.dev.operation;

import ghostface.dev.exception.data.IllegalValueException;
import ghostface.dev.exception.TableException;
import ghostface.dev.mapping.column.Column;
import ghostface.dev.mapping.data.Data;
import ghostface.dev.mapping.key.Key;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.UnknownNullability;

public interface Upgradable<T extends Key<?>> {

    void setValue(@NotNull T key, @UnknownNullability Object value, @NotNull Column<?> column) throws IllegalValueException, TableException;

    void setAll(@NotNull T key, @NotNull Data<T> data) throws TableException;

}
