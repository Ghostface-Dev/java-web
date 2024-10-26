package ghostface.dev.operation;

import ghostface.dev.mapping.Data;
import ghostface.dev.mapping.key.Key;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;
import java.util.Optional;

public interface Selectable<T extends Key<?>> {

    @NotNull LinkedList<Data> getAll();

    @NotNull LinkedList<Data> getAll(@NotNull Object value);

    @NotNull Optional<Data> get(@NotNull T key);

}
