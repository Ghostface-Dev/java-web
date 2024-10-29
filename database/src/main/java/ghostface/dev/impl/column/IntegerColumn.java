package ghostface.dev.impl.column;

import ghostface.dev.mapping.column.AbstractColumn;
import ghostface.dev.type.ConcreteType;
import ghostface.dev.impl.datatype.IntegerDataType;
import org.jetbrains.annotations.NotNull;

public final class IntegerColumn extends AbstractColumn<Integer> {

    public IntegerColumn(@NotNull String label) {
        super(label);
    }

    @Override
    public @NotNull ConcreteType<Integer> dataType() {
        return IntegerDataType.getInstance();
    }
}
