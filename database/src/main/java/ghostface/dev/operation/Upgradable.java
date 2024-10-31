package ghostface.dev.operation;

import ghostface.dev.exception.TableException;
import ghostface.dev.mapping.Data;
import ghostface.dev.mapping.Key;
import org.jetbrains.annotations.NotNull;

public interface Upgradable<T extends Key<?>> {

    void setAll(@NotNull T key, @NotNull Data<T> data) throws TableException;

}
