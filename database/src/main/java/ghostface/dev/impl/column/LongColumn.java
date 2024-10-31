package ghostface.dev.impl.column;

import ghostface.dev.mapping.Column;
import ghostface.dev.type.ConcreteType;
import ghostface.dev.impl.datatype.LongDataType;
import org.jetbrains.annotations.NotNull;

public final class LongColumn extends Column<Long> {

    public LongColumn(@NotNull String label) {
        super(label);
    }

    @Override
    public @NotNull ConcreteType<Long> concreteType() {
        return LongDataType.getInstance();
    }
}
