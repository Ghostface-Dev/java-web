package entities.client;


import entities.client.email.Email;
import entities.vehicle.Vehicle;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public final class ClientImp implements Client {

    private final int id;
    private final @NotNull String name;
    private final @NotNull String cpf;
    private @NotNull String email;
    private @NotNull Vehicle vehicle;

    public ClientImp(@NotNull Vehicle vehicle, @NotNull String email, @NotNull String cpf, @NotNull String name, int id) {
        this.vehicle = vehicle;
        this.email = email;
        this.cpf = cpf;
        this.name = name;
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public @NotNull String getCpf() {
        return cpf;
    }

    @Override
    public @NotNull String getEmail() {
        return email;
    }

    @Override
    public @NotNull Vehicle getVehicle() {
        return vehicle;
    }

    @Override
    public @NotNull String getName() {
        return name;
    }

    @Override
    public void setVehicle(@NotNull Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public void setVehicle(@NotNull Client client, @NotNull Vehicle vehicle) {
        client.setVehicle(vehicle);
    }

    @Override
    public void setEmail(@NotNull String email) {
        this.email = email;
    }

    @Override
    public void setEmail(@NotNull Client client, @NotNull String email) {
        client.setEmail(email);
    }

    @Override
    public boolean equals(@Nullable Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        @NotNull ClientImp clientImp = (ClientImp) object;
        return id == clientImp.id && Objects.equals(cpf, clientImp.cpf) && Objects.equals(email, clientImp.email) && Objects.equals(vehicle, clientImp.vehicle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cpf, email, vehicle);
    }
}
