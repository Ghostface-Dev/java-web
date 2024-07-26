package entities.client.vehicle;

import entities.client.Client;
import org.jetbrains.annotations.NotNull;

public class VehicleImp implements Vehicle {
    private final @NotNull String plate;
    private final @NotNull String name;
    private final @NotNull String brand;
    private @NotNull String color;
    private final @NotNull Client client;

    public VehicleImp(@NotNull String plate, @NotNull String name, @NotNull String brand, @NotNull String color, @NotNull Client client) {
        this.plate = plate;
        this.name = name;
        this.brand = brand;
        this.color = color;
        this.client = client;
    }

    @Override
    public @NotNull String getPlate() {
        return plate;
    }

    @Override
    public @NotNull Client getClient() {
        return client;
    }

    @Override
    public @NotNull String getName() {
        return name;
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
    public void setColor(@NotNull String color) {
        this.color = color;
    }
}
