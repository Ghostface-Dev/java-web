package ghostface.dev.mapping.column;

import org.jetbrains.annotations.NotNull;

public final class ColumnKey<I> implements Column<I> {

    private final @NotNull I id;

    public ColumnKey(@NotNull I id) {
        this.id = id;
    }

    @Override
    public @NotNull String label() {
        return "id";
    }

    @Override
    public boolean isUnique() {
        return true;
    }

    @Override
    public @NotNull I getValue() {
        return id;
    }

    @Override
    public void setValue(I value) {
        throw new UnsupportedOperationException("Cannot change a ID");
    }
}
