package ghostface.dev.mapping.key;

import ghostface.dev.exception.data.IllegalValueException;
import ghostface.dev.type.number.LongDataType;
import org.jetbrains.annotations.NotNull;

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
        return LongDataType.getInstance().read(bytes);
    }

    @Override
    public @NotNull Long read(@NotNull Object object) throws IllegalValueException {
        return LongDataType.getInstance().read(object);
    }

    @Override
    public @NotNull Long read(@NotNull InputStream stream) throws IllegalValueException, IOException {
        return LongDataType.getInstance().read(stream);
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
