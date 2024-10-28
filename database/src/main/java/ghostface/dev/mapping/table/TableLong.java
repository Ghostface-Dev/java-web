package ghostface.dev.mapping.table;

import ghostface.dev.mapping.column.Columns;
import ghostface.dev.mapping.key.LongKey;
import org.jetbrains.annotations.NotNull;

public final class TableLong extends AbstractTable<LongKey> {

    public TableLong(@NotNull Columns columns) {
        super(columns);
    }

}
