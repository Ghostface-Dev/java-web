package ghostface.dev.operation;

import ghostface.dev.exception.TableException;
import ghostface.dev.mapping.data.Data;
import ghostface.dev.mapping.key.Key;
import org.jetbrains.annotations.NotNull;

public interface Upgradable<T extends Key<?>> {

    boolean setAll(@NotNull Data<T> data) throws TableException;

}
