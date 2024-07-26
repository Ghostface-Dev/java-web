package entities.vehicle;

import entities.client.Client;
import entities.spot.ParkingSpot;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class VehicleImp implements Vehicle {
    private final @NotNull String plate;
    private final @NotNull String name;
    private final @NotNull String brand;
    private @NotNull String color;
    private final @NotNull Client client;
    private @Nullable ParkingSpot spot;

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
    public @Nullable ParkingSpot getSpot() {
        return spot;
    }

    @Override
    public void setColor(@NotNull String color) {
        this.color = color;
    }

    @Override
    public void setSpot(@Nullable ParkingSpot spot) {
        this.spot = spot;
    }


}
