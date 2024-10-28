package ghostface.dev.mapping.table;

import ghostface.dev.mapping.column.Columns;
import ghostface.dev.mapping.key.DefaultKey;
import org.jetbrains.annotations.NotNull;

public final class TableInteger extends AbstractTable<DefaultKey> {

    public TableInteger(@NotNull Columns columns) {
        super(columns);
    }

}
