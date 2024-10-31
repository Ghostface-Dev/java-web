package ghostface.dev.impl.table;

import ghostface.dev.mapping.Columns;

import ghostface.dev.mapping.Key;
import ghostface.dev.mapping.provider.AbstractTable;
import org.jetbrains.annotations.NotNull;


public final class LongTable extends AbstractTable<Key<Long>> {

    public LongTable(@NotNull Columns columns) {
        super(columns);
    }

}
