package ghostface.dev.movement;

import ghostface.dev.core.Account;
import ghostface.dev.core.Movement;
import org.jetbrains.annotations.NotNull;

public final class Transference extends Movement {
    private final @NotNull Account receiver;
    private final double value;

    public Transference(int id, @NotNull Account from, double value, @NotNull Account to) {
        super(id, from);
        this.value = value;
        receiver = to;
        operation(value);
    }

    @Override
    protected void operation(double value) {
        receiver.setBalance(receiver.getBalance() + value);
        getFrom().setBalance(getFrom().getBalance() - value);
    }

    @Override
    public double getValue() {
        return value;
    }

}
