package ghostface.dev.type;

import ghostface.dev.exception.IllegalValueException;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;

public interface DataType {

    @NotNull Object read(byte @NotNull [] bytes) throws IllegalValueException, IOException;

    @NotNull Object read(@NotNull InputStream stream) throws IllegalValueException, IOException;

}
