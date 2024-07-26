package ghostface.dev.system;

import ghostface.dev.core.Account;
import ghostface.dev.client.Customer;
import ghostface.dev.core.Movement;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public final class DatabaseImpl implements DatabaseFactory {

    private final Set<@NotNull Customer> customers = new HashSet<>();
    private final Set<@NotNull Account> accounts = new HashSet<>();
    private final List<@NotNull Movement> movements = new ArrayList<>();

    @Override
    public void recordCustomer(@NotNull Customer customer, @NotNull String name, int cpf) {
        if (customer.getCompleteName().equalsIgnoreCase(name) && customer.getCpf() == cpf) {
            customers.add(customer);
        } else {
            System.out.println("An error ocurred.");
        }
    }

    @Override
    public void recordAccount(@NotNull Account account, @NotNull Customer customer) {
        if (account.getCustomer().equals(customer)) {
            accounts.add(account);
        }
    }

    @Override
    public @NotNull Customer getCustomer(int id) {
        return customers.stream()
                .filter(customer -> customer.getId() == id)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Customer not found"));
    }

    @Override
    public @NotNull Account getAccount(int id) {
        return accounts.stream()
                .filter((account -> account.getId() == id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Account not found"));
    }

    @Override
    public @NotNull Movement getMovement(int id) {
        return movements.stream()
                .filter((movement -> movement.getId() == id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Movement not found"));
    }

}
