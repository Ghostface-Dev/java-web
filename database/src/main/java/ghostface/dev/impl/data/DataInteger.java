package ghostface.dev.impl.data;

import ghostface.dev.mapping.Key;
import ghostface.dev.mapping.Table;
import ghostface.dev.mapping.provider.AbstractData;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ConcurrentHashMap;

public final class DataInteger extends AbstractData<Key<Integer>> {
    public DataInteger(@NotNull Key<Integer> key, @NotNull Table<Key<Integer>> table) {
        super(key, table, new ConcurrentHashMap<>());
    }
}
