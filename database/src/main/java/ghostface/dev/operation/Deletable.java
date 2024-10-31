package ghostface.dev.operation;

import ghostface.dev.mapping.Data;
import ghostface.dev.mapping.Key;
import org.jetbrains.annotations.NotNull;

public interface Deletable<T extends Key<?>> {

    boolean deleteAll(@NotNull T key);

    boolean deleteAll(@NotNull Data<T> data);

}
