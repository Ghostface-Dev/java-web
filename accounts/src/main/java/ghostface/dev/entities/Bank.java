package ghostface.dev.entities;

import ghostface.dev.core.Account;
import ghostface.dev.exception.IndentifyAlreadyExistException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Bank {
    private static final @NotNull Map<@NotNull String, @NotNull Account> accounts = new HashMap<>();
    private static final @NotNull Map<@NotNull String, @NotNull Customer> customers = new HashMap<>();

    private static final @NotNull Map<@NotNull Customer, @NotNull List<@NotNull Account>> customerAccount = new HashMap<>();

    public static @Nullable Account getAccount(@NotNull String id) {
        return accounts.get(id);
    }

    public static @NotNull Customer getCustomer(@NotNull String id) {
        return customers.get(id);
    }

    public static @NotNull List<Account> getCustomerAccount(@NotNull Customer customer) {
        return customerAccount.get(customer);
    }

    static void putAccount(@NotNull Customer customer) {
        for (@NotNull Account account : customer.getAccounts()) {
            accounts.putIfAbsent(account.getId(), account);
            customerAccount.put(customer, customer.getAccounts());
        }
    }

    static void putCustomer(@NotNull Customer customer) {
        if (customers.containsKey(customer.getId())) {
            throw new IndentifyAlreadyExistException("Customer ID already exist");
        }
        customers.putIfAbsent(customer.getId(), customer);
    }

    static @NotNull Map<@NotNull String, @NotNull Account> getAccountMap() {
        return accounts;
    }

    static @NotNull Map<@NotNull String, @NotNull Customer> getCustomerMap() {
        return customers;
    }
}
