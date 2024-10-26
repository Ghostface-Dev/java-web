package ghostface.dev.type.email;

import ghostface.dev.exception.IllegalValueException;
import ghostface.dev.type.ConcreteType;
import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public final class EmailDataType implements ConcreteType<Email> {

    @Override
    public @NotNull Email read(@NotNull InputStream stream) throws IllegalValueException, IOException {
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

            return Email.parse(builder.toString());
        } catch (@NotNull Throwable e) {
            throw new IllegalValueException(e.getMessage());
        }
    }

    @Override
    public @NotNull Email read(byte @NotNull [] bytes) throws IllegalValueException, IOException {
        return this.read(new ByteArrayInputStream(bytes));
    }
}
