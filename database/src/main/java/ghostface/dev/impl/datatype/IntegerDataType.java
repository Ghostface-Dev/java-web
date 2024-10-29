package ghostface.dev.impl.datatype;

import ghostface.dev.exception.data.IllegalValueException;
import ghostface.dev.type.ConcreteType;
import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public final class IntegerDataType implements ConcreteType<Integer> {

    private static final @NotNull IntegerDataType INSTANCE = new IntegerDataType();

    public static @NotNull IntegerDataType getInstance() {
        return INSTANCE;
    }

    @Override
    public @NotNull Integer read(@NotNull InputStream stream) throws IllegalValueException, IOException {
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
    public @NotNull Integer read(byte @NotNull [] bytes) throws IllegalValueException {
        try {
            return Integer.parseInt(new String(bytes));
        } catch (NumberFormatException e) {
            throw new IllegalValueException(e.getMessage());
        }
    }

    @Override
    public @NotNull Integer read(@NotNull Object object) throws IllegalValueException {
        try {
            return Integer.getInteger((String) object);
        } catch (@NotNull Throwable throwable) {
            try {
                return (int) object;
            } catch (@NotNull Throwable thr) {
                throw new IllegalValueException("Object is not a valid Integer");
            }
        }
    }

    private IntegerDataType() {
    }
}
