package org.ghostface.dev.imp;

import org.ghostface.dev.factory.ParkingClient;


import org.jetbrains.annotations.NotNull;

import java.time.OffsetDateTime;

public class Client extends ParkingClient {

    private final @NotNull String name;
    private final @NotNull String cpf;
    private @NotNull String email;
    private @NotNull Vehicle vehicle;
    public final @NotNull OffsetDateTime creationDate;

    public Client(long id, @NotNull String name, @NotNull String cpf, @NotNull String email, @NotNull Vehicle vehicle) {
        super(id);
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.vehicle = vehicle;
        this.creationDate = OffsetDateTime.now();
    }

    // getters

    public @NotNull Vehicle getVehicle() {
        return vehicle;
    }

    @Override
    public @NotNull String getName() {
        return name;
    }

    @Override
    public @NotNull String getEmail() {
        return email;
    }

    @Override
    public @NotNull String getCpf() {
        return cpf;
    }

    // setters
    //todo verificar emails duplicados
    @Override
    public void setEmail(@NotNull String email) {
        this.email = email;
    }

    public final void setVehicle(@NotNull Vehicle vehicle) {
        if (vehicle.getClient() != null) {
            System.err.println("Vehicle: " + vehicle.getName() + " already have a owner");
            return;
        }
        this.vehicle = vehicle;
        vehicle.setClient(this);
    }
}
