package ghostface.dev.impl.table;

import ghostface.dev.mapping.column.Columns;
import ghostface.dev.mapping.key.Key;
import ghostface.dev.mapping.table.AbstractTable;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public final class IntegerTable extends AbstractTable<Key<UUID>> {

    public IntegerTable(@NotNull Columns columns) {
        super(columns);
    }

}
