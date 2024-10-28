package ghostface.dev.mapping.key;

import ghostface.dev.exception.data.IllegalValueException;
import ghostface.dev.type.number.IntegerDataType;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.atomic.AtomicInteger;

public final class IntegerKey extends Key<@NotNull Integer> {

    public static @NotNull Key<@NotNull Integer> create() {
        return new IntegerKey(IdGenerate.generate());
    }

    public IntegerKey(int value) {
        super(value);
    }

    @Override
    public @NotNull Integer read(byte @NotNull [] bytes) throws IllegalValueException {
        return IntegerDataType.getInstance().read(bytes);
    }

    @Override
    public @NotNull Integer read(@NotNull Object object) throws IllegalValueException {
        return IntegerDataType.getInstance().read(object);
    }

    @Override
    public @NotNull Integer read(@NotNull InputStream stream) throws IllegalValueException, IOException {
        return IntegerDataType.getInstance().read(stream);
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
