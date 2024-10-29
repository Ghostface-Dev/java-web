package ghostface.dev.mapping.key;

import ghostface.dev.type.ConcreteType;
import ghostface.dev.impl.datatype.LongDataType;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.atomic.AtomicLong;

public final class LongKey extends Key<@NotNull Long> {

    public static @NotNull Key<@NotNull Long> create() {
        return new LongKey(IdGenerate.generate());
    }

    // Objects

    public LongKey(long value) {
        super(value);
    }

    @Override
    public @NotNull ConcreteType<@NotNull Long> concreteType() {
        return LongDataType.getInstance();
    }

    // Classes

    private static final class IdGenerate {
        private static final @NotNull AtomicLong atomic = new AtomicLong(0);

        private static long generate() {
            return atomic.incrementAndGet();
        }

        private IdGenerate() {
            throw new UnsupportedOperationException();
        }
    }
}
