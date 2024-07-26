package ghostface.dev.core;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.OffsetDateTime;
import java.util.Objects;

public abstract class Movement {
    private final int id;
    private final @NotNull Account from;
    private final @NotNull OffsetDateTime dateTime;

    public Movement(int id, @NotNull Account from) {
        this.id = id;
        this.from = from;
        this.dateTime = OffsetDateTime.now();
        from.getMovements().add(this);
    }

    protected abstract void operation(double value);

    public abstract double getValue();

    public final int getId() {
        return id;
    }

    public final @NotNull Account getFrom() {
        return from;
    }

    public final @NotNull OffsetDateTime getDateTime() {
        return dateTime;
    }


    @Override
    public boolean equals(@Nullable Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        @NotNull Movement movement = (Movement) object;
        return id == movement.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
