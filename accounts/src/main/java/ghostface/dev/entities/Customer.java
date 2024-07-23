package ghostface.dev.entities;

import ghostface.dev.core.Account;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;
import java.util.List;

public class Customer {
    private final @NotNull String id;
    private final @NotNull String name;
    private final @NotNull String surname;
    private final @NotNull String email;
    private final @NotNull String tel;

    private final @NotNull List<@NotNull Account> accounts = new LinkedList<>();

    public Customer(@NotNull String id, @NotNull String name, @NotNull String surname, @NotNull String email, @NotNull String tel) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.tel = tel;
        Bank.putCustomer(this);
    }

    // getters

    public @NotNull String getId() {
        return id;
    }

    public @NotNull String getCompleteName() {
        return name + " " + surname;
    }

    public @NotNull String getEmail() {
        return email;
    }

    public @NotNull String getTel() {
        return tel;
    }

    @NotNull List<@NotNull Account> getAccounts() {
        return accounts;
    }
}
