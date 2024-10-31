package ghostface.dev.impl.data;


import ghostface.dev.mapping.Key;
import ghostface.dev.mapping.Table;
import ghostface.dev.mapping.provider.AbstractData;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public final class DataLong extends AbstractData<Key<Long>> {
    public DataLong(@NotNull Key<Long> key, @NotNull Table<Key<Long>> table) {
        super(key, table, new HashMap<>());
    }
}
