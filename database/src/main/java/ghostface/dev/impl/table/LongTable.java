package ghostface.dev.impl.table;

import ghostface.dev.mapping.column.Columns;

import ghostface.dev.mapping.key.Key;
import ghostface.dev.mapping.table.AbstractTable;
import org.jetbrains.annotations.NotNull;


public final class LongTable extends AbstractTable<Key<Long>> {

    public LongTable(@NotNull Columns columns) {
        super(columns);
    }

}
