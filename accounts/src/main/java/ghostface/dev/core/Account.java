package ghostface.dev.core;

import ghostface.dev.entities.Customer;
import org.jetbrains.annotations.NotNull;

public abstract class Account {
    private double balance;

    protected void add(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Ammount cannot be negative");
        }
        setBalance(getBalance() + amount);
    }

    protected void substract(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Ammount cannot be negative");
        }
        if (getBalance() <= 0) {
            throw new IllegalArgumentException("Insuficient Balance");
        }
        setBalance(getBalance() - amount);
    }

    private void setBalance(double balance) {
        this.balance = balance;
    }

    public abstract @NotNull String getId();

    public abstract @NotNull Customer getCustomer();

    public double getBalance() {
        return balance;
    }

}

