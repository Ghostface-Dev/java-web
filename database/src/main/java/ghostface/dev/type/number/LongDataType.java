package ghostface.dev.type.number;

import ghostface.dev.exception.IllegalValueException;
import ghostface.dev.type.ConcreteType;
import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public final class LongDataType implements ConcreteType<Long> {

    public LongDataType() {
    }

    @Override
    public @NotNull Long read(@NotNull InputStream stream) throws IllegalValueException, IOException {
        try (@NotNull ByteArrayOutputStream output = new ByteArrayOutputStream()) {
            byte @NotNull [] bytes = new byte[2048];

            int read;
            while ((read = stream.read(bytes)) != -1) {
                output.write(bytes, 0, read);
                output.flush();
            }

            return this.read(output.toByteArray());
        }
    }

    @Override
    public @NotNull Long read(byte @NotNull [] bytes) throws IllegalValueException {
        try {
            return Long.parseLong(new String(bytes));
        } catch (NumberFormatException e) {
            throw new IllegalValueException(e.getMessage());
        }
    }
}
