package ghostface.dev.impl.column;

import ghostface.dev.mapping.column.AbstractColumn;
import ghostface.dev.type.ConcreteType;
import ghostface.dev.impl.datatype.LongDataType;
import org.jetbrains.annotations.NotNull;

public final class LongColumn extends AbstractColumn<Long> {

    public LongColumn(@NotNull String label) {
        super(label);
    }

    @Override
    public @NotNull ConcreteType<Long> dataType() {
        return LongDataType.getInstance();
    }
}
