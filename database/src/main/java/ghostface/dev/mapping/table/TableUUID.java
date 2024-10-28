package ghostface.dev.mapping.table;

import ghostface.dev.mapping.column.Columns;
import ghostface.dev.mapping.key.DefaultKey;
import org.jetbrains.annotations.NotNull;

public final class TableUUID extends AbstractTable<DefaultKey> {

    public TableUUID(@NotNull Columns columns) {
        super(columns);
    }

}
