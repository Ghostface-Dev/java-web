package ghostface.dev.type.standard;

import ghostface.dev.exception.data.IllegalValueException;
import ghostface.dev.type.ConcreteType;
import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

public final class DefaultDataType implements ConcreteType<UUID> {

    private static final @NotNull DefaultDataType INSTANCE = new DefaultDataType();

    public static @NotNull DefaultDataType getInstance() {
        return INSTANCE;
    }

    @Override
    public @NotNull UUID read(byte @NotNull [] bytes) throws IllegalValueException {
        try {
            return UUID.fromString(new String(bytes, StandardCharsets.UTF_8));
        } catch (IllegalArgumentException e) {
            throw new IllegalValueException(e.getMessage());
        }
    }

    @Override
    public @NotNull UUID read(@NotNull Object object) throws IllegalValueException {
        try {
            return UUID.fromString((String) object);
        } catch (@NotNull Throwable throwable) {
            throw new IllegalValueException("Object is not a valid UUID");
        }
    }

    @Override
    public @NotNull UUID read(@NotNull InputStream stream) throws IllegalValueException, IOException {
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

    private DefaultDataType() {
    }
}
