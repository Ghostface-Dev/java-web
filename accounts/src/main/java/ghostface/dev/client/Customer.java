package ghostface.dev.client;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public final class Customer {
    private final int id;
    private final @NotNull String name;
    private final @NotNull String surname;
    private final @NotNull String email;
    private final int cpf;
    private final Status status;
    private final Pendencies pendencies;

    public Customer(int id, @NotNull String name, @NotNull String surname, @NotNull String email, int cpf) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.cpf = cpf;
        this.status = Status.OK;
        this.pendencies = Pendencies.NONE;
    }

    public static Customer create(int id, @NotNull String name, @NotNull String surname, @NotNull String email, int cpf) {
        if (id <=0) {
            throw new IllegalArgumentException("ID invalid");
        }
        return new Customer(id,name,surname,email,cpf);
    }

    public int getId() {
        return id;
    }

    public @NotNull String getCompleteName() {
        return name + " " + surname;
    }

    public @NotNull String getEmail() {
        return email;
    }

    public int getCpf() {
        return cpf;
    }

    public enum Status {
        OK, PENDENCIES
    }

    public enum Pendencies {
        NONE, DOCUMENT, OWING
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Customer customer = (Customer) object;
        return id == customer.id && cpf == customer.cpf && Objects.equals(email, customer.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, cpf);
    }
}
