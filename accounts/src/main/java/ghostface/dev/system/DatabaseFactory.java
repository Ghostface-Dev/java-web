package ghostface.dev.system;

import ghostface.dev.core.Account;
import ghostface.dev.client.Customer;
import ghostface.dev.core.Movement;
import org.jetbrains.annotations.NotNull;

public interface DatabaseFactory {

    void recordCustomer(@NotNull Customer customer, @NotNull String name, @NotNull String cpf);
    void recordAccount(@NotNull Account account, @NotNull Customer customer);
    @NotNull Customer getCustomer(int id);
    @NotNull Account getAccount(int id);
    @NotNull Movement getMovement(int id);

}
