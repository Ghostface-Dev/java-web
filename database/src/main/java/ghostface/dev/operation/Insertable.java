package ghostface.dev.operation;

import ghostface.dev.exception.TableException;
import ghostface.dev.mapping.Data;
import ghostface.dev.mapping.key.Key;
import org.jetbrains.annotations.NotNull;

public interface Insertable<T extends Key<?>> {

    boolean createEmpty(@NotNull T key);

    boolean create(@NotNull T key, @NotNull Data data) throws TableException;

}
