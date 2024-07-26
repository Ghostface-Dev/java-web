package entities.client;


import entities.client.vehicle.Vehicle;
import org.jetbrains.annotations.NotNull;

public final class Clientimp implements Client {
    private final int id;
    private final @NotNull String name;
    private final @NotNull String cpf;
    private @NotNull String email;
    private @NotNull Vehicle vehicle;

    public Clientimp(@NotNull Vehicle vehicle, @NotNull String email, @NotNull String cpf, @NotNull String name, int id) {
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
    public void setEmail(@NotNull String email) {
        this.email = email;
    }
}
