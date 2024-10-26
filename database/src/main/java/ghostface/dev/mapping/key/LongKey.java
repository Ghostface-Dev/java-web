package ghostface.dev.mapping.key;

import ghostface.dev.exception.IllegalValueException;
import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.atomic.AtomicInteger;

public final class LongKey extends Key<@NotNull Long> {

    public static @NotNull Key<@NotNull Long> create() {
        return new LongKey(IdGenerate.generate());
    }

    public LongKey(long value) {
        super(value);
    }

    @Override
    public @NotNull Long read(byte @NotNull [] bytes) throws IllegalValueException {
        try {
            return Long.parseLong(new String(bytes));
        } catch (NumberFormatException e) {
            throw new IllegalValueException(e.getMessage());
        }
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

    // Classes

    private static final class IdGenerate {
        private static final @NotNull AtomicInteger atomic = new AtomicInteger(0);

        private static int generate() {
            return atomic.incrementAndGet();
        }

        private IdGenerate() {
            throw new UnsupportedOperationException();
        }
    }
}
