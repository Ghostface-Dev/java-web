package org.ghostface.dev.imp;

import org.ghostface.dev.factory.ParkingClient;
import org.ghostface.dev.factory.ParkingVehicle;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.OffsetDateTime;

public class Vehicle extends ParkingVehicle {

    private @Nullable ParkingClient client;
    private final @NotNull String name;
    private final @NotNull String plate;
    private final @NotNull String brand;
    private @NotNull String color;
    private final @NotNull OffsetDateTime creationDate;

    public Vehicle(long id, @NotNull ParkingClient client, @NotNull String plate, @NotNull String name, @NotNull String brand, @NotNull String color, @NotNull OffsetDateTime creationDate) {
        super(id);
        this.client = client;
        this.plate = plate;
        this.name = name;
        this.brand = brand;
        this.color = color;
        this.creationDate = creationDate;
    }

    @Override
    public @Nullable ParkingClient getClient() {
        return client;
    }

    @Override
    public @NotNull String getPlate() {
        return plate;
    }

    @Override
    public @NotNull String getBrand() {
        return brand;
    }

    @Override
    public @NotNull String getColor() {
        return color;
    }

    @Override
    public @NotNull OffsetDateTime getTime() {
        return creationDate;
    }

    public @NotNull String getName() {
        return name;
    }

    @Override
    public void setColor(@NotNull String color) {
        this.color = color;
    }

    @Override
    public void setClient(@NotNull ParkingClient client) {
        this.client = client;
    }

}
