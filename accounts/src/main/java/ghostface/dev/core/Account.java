package ghostface.dev.core;

import ghostface.dev.client.Customer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public final class Account {
    private final int id;
    private final @NotNull String login;
    private final @NotNull Customer customer;

    private double balance;

    private final @NotNull Set<@NotNull Movement> movements;

    public Account(int id, @NotNull String login, @NotNull Customer customer) {
        this.id = id;
        this.login = login;
        this.customer = customer;
        this.movements = new HashSet<>();
    }

    public int getId() {
        return id;
    }

    public @NotNull String getLogin() {
        return login;
    }

    public @NotNull Customer getCustomer() {
        return customer;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @NotNull Set<@NotNull Movement> getMovements() {
        return movements;
    }

    @Override
    public boolean equals(@Nullable Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        @NotNull Account account = (Account) object;
        return id == account.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
