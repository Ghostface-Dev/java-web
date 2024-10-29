package ghostface.dev.mapping.table;

import ghostface.dev.mapping.column.Columns;
import ghostface.dev.mapping.key.Key;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public final class UUIDTable extends AbstractTable<Key<UUID>> {

    public UUIDTable(@NotNull Columns columns) {
        super(columns);
    }

}
