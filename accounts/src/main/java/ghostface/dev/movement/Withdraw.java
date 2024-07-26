package ghostface.dev.movement;

import ghostface.dev.core.Account;
import ghostface.dev.core.Movement;
import org.jetbrains.annotations.NotNull;

public final class Withdraw extends Movement {
    private final double value;

    public Withdraw(int id, @NotNull Account from, double value) {
        super(id, from);
        this.value = value;
        operation(value);
    }

    @Override
    protected void operation(double value) {
        getFrom().setBalance(getFrom().getBalance() - value);
    }

    @Override
    public double getValue() {
        return value;
    }


}
