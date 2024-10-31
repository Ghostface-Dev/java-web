package ghostface.dev.impl.table;

import ghostface.dev.mapping.Columns;
import ghostface.dev.mapping.Key;
import ghostface.dev.mapping.provider.AbstractTable;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public final class IntegerTable extends AbstractTable<Key<UUID>> {

    public IntegerTable(@NotNull Columns columns) {
        super(columns);
    }

}
