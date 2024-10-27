package ghostface.dev.type;

import ghostface.dev.exception.IllegalValueException;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;

public interface ConcreteType<T> extends DataType {

    @Override
    @NotNull T read(@NotNull InputStream stream) throws IllegalValueException, IOException;

    @Override
    @NotNull T read(byte @NotNull [] bytes) throws IllegalValueException, IOException;

    @Override
    @NotNull T read(@NotNull Object object) throws IllegalValueException;
}
