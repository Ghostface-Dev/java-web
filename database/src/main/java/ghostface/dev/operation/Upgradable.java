package ghostface.dev.operation;

import ghostface.dev.exception.IllegalValueException;
import ghostface.dev.exception.TableException;
import ghostface.dev.mapping.Data;
import ghostface.dev.mapping.key.Key;
import org.jetbrains.annotations.NotNull;

public interface Upgradable<T extends Key<?>> {

    void setValue(@NotNull T key, @NotNull Object oldValue, @NotNull Object value) throws IllegalValueException, TableException;

    void setAll(@NotNull T key, @NotNull Data data) throws TableException;

}
