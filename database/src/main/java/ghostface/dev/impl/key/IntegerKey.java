package ghostface.dev.impl.key;

import ghostface.dev.mapping.Key;
import ghostface.dev.type.ConcreteType;
import ghostface.dev.impl.datatype.IntegerDataType;
import org.jetbrains.annotations.NotNull;


import java.util.concurrent.atomic.AtomicInteger;

public final class IntegerKey extends Key<@NotNull Integer> {

    public static @NotNull Key<@NotNull Integer> create() {
        return new IntegerKey(IdGenerate.generate());
    }

    // Objects

    public IntegerKey(int value) {
        super(value);
    }

    @Override
    public @NotNull ConcreteType<@NotNull Integer> concreteType() {
        return IntegerDataType.getInstance();
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
