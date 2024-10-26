package ghostface.dev.operation;

import ghostface.dev.exception.IllegalValueException;
import ghostface.dev.mapping.key.Key;
import org.jetbrains.annotations.NotNull;

public interface Insertable<T extends Key<?>> {

    void createEmpty(@NotNull T key);

    void create(@NotNull T key, @NotNull Object @NotNull [] values) throws IllegalValueException;

}
