package ghostface.dev.impl.datatype;

import ghostface.dev.exception.data.IllegalValueException;
import ghostface.dev.type.ConcreteType;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public final class StringDataType implements ConcreteType<String> {

    private static final @NotNull StringDataType INSTANCE = new StringDataType();

    public static @NotNull StringDataType getInstance() {
        return INSTANCE;
    }

    // Objects

    @Override
    public @NotNull String read(@NotNull InputStream stream) throws IllegalValueException, IOException {
        try (@NotNull InputStreamReader input = new InputStreamReader(stream, StandardCharsets.UTF_8)) {
            @NotNull StringBuilder builder = new StringBuilder();
            char @NotNull [] chars = new char[1024];

            int read;
            while ((read = input.read(chars)) != -1) {
                builder.append(chars, 0, read);
            }

            if (builder.toString().trim().isEmpty()) {
                throw new IllegalValueException("String cannot be null");
            }

            return builder.toString();
        }
    }

    @Override
    public @NotNull String read(@NotNull Object object) throws IllegalValueException {
        try {
            @NotNull String s = (String) object;
            return read(s.getBytes());
        } catch (@NotNull Throwable throwable) {
            throw new IllegalValueException("Object is not a String");
        }
    }

    @Override
    public @NotNull String read(byte @NotNull [] bytes) throws IllegalValueException {
        @NotNull String s = new String(bytes, StandardCharsets.UTF_8);
        if (s.trim().isEmpty()) {
            throw new IllegalValueException("String cannot be null");
        } else {
            return s;
        }
    }

    private StringDataType() {
    }
}
