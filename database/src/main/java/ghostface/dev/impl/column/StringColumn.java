package ghostface.dev.impl.column;

import ghostface.dev.mapping.column.AbstractColumn;
import ghostface.dev.type.ConcreteType;
import ghostface.dev.impl.datatype.StringDataType;
import org.jetbrains.annotations.NotNull;

public final class StringColumn extends AbstractColumn<String> {

    public StringColumn(@NotNull String label) {
        super(label);
    }

    @Override
    public @NotNull ConcreteType<String> dataType() {
        return StringDataType.getInstance();
    }
}
