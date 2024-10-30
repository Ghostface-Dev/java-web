package ghostface.dev.impl.column;

import ghostface.dev.mapping.column.Column;
import ghostface.dev.mapping.column.Columns;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.UnknownNullability;

import java.util.*;
import java.util.stream.Collectors;

public final class ColumnsImpl implements Columns {

    private final @NotNull Set<@NotNull Column<?>> columns = new LinkedHashSet<>();

    public ColumnsImpl(@NotNull Column<?> ... columns) {
        this.columns.addAll(Arrays.asList(columns));
    }

    @Override
    public boolean add(@NotNull Column<?> column) {
        return columns.add(column);
    }

    @Override
    public @NotNull Optional<@NotNull Column<?>> getColumn(@NotNull String label) {
        return columns.stream().filter(column -> column.label().equalsIgnoreCase(label)).findFirst();
    }

    @Override
    public @NotNull List<@NotNull String> getLabels() {
        return columns.stream().map(Column::label).collect(Collectors.toList());
    }

    @Override
    public boolean remove(@NotNull Column<?> column) {
        return columns.remove(column);
    }

    @Override
    public boolean remove(@NotNull String label) {
        return columns.removeIf(column -> column.label().equalsIgnoreCase(label));
    }

    @Override
    public boolean contains(@NotNull Column<?> column) {
        return columns.contains(column);
    }

    @Override
    public boolean contains(@NotNull String label) {
        return columns.stream().anyMatch(column -> column.label().equalsIgnoreCase(label));
    }

    @Override
    public @NotNull Column<?> @UnknownNullability [] toArray() {
        @NotNull Column<?> @UnknownNullability [] array = new Column[size()];
        return columns.toArray(array);
    }

    @Override
    public int size() {
        return columns.size();
    }

    @Override
    public @NotNull Columns clone() {
        try {
            return (Columns) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Cannot clone columns", e);
        }
    }

    @Override
    public @NotNull Iterator<@NotNull Column<?>> iterator() {
        return columns.iterator();
    }
}
