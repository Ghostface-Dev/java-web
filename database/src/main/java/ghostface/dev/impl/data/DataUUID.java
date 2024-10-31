package ghostface.dev.impl.data;


import ghostface.dev.mapping.Key;
import ghostface.dev.mapping.Table;
import ghostface.dev.mapping.provider.AbstractData;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.UUID;


public final class DataUUID extends AbstractData<Key<UUID>> {
    public DataUUID(@NotNull Key<UUID> key, @NotNull Table<Key<UUID>> table) {
        super(key, table, new HashMap<>());
    }
}
