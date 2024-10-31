package ghostface.dev.operation;

import ghostface.dev.mapping.Data;
import ghostface.dev.mapping.Key;
import org.jetbrains.annotations.NotNull;

public interface Insertable<T extends Key<?>> {

    boolean create(@NotNull Data<T> data);

}
