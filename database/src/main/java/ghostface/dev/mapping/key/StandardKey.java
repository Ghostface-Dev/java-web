package ghostface.dev.mapping.key;

import ghostface.dev.exception.IllegalValueException;
import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

public final class StandardKey extends Key<@NotNull UUID> {

    public static @NotNull Key<@NotNull UUID> create() {
        return new StandardKey(UUID.randomUUID());
    }

    // Objects

    public StandardKey(@NotNull UUID uuid) {
        super(uuid);
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
}
