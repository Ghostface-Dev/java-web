package ghostface.dev.impl.column;

import ghostface.dev.mapping.Column;
import ghostface.dev.type.ConcreteType;
import ghostface.dev.impl.datatype.IntegerDataType;
import org.jetbrains.annotations.NotNull;

public final class IntegerColumn extends Column<Integer> {

    public IntegerColumn(@NotNull String label) {
        super(label);
    }

    @Override
    public @NotNull ConcreteType<Integer> concreteType() {
        return IntegerDataType.getInstance();
    }
}
