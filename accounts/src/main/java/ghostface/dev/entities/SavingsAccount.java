package ghostface.dev.entities;

import ghostface.dev.core.Account;
import ghostface.dev.exception.IndentifyAlreadyExistException;
import org.jetbrains.annotations.NotNull;

public class SavingsAccount extends Account {
    private final @NotNull String id;
    private final @NotNull Customer customer;

    public SavingsAccount(@NotNull String id, @NotNull Customer customer) {
        if (Bank.getAccountMap().containsKey(id)) {
            throw new IndentifyAlreadyExistException("Account ID is already exist.");
        }
        this.id = id;
        this.customer = customer;
        customer.getAccounts().add(this);
        Bank.putAccount(customer);
    }

    @Override
    public @NotNull String getId() {
        return id;
    }

    @Override
    public @NotNull Customer getCustomer() {
        return customer;
    }

}
