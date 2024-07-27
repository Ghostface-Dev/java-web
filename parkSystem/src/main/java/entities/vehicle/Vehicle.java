package entities.vehicle;

import entities.client.Client;
import entities.spot.ParkingSpot;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Vehicle {

    @NotNull String getPlate();

    @NotNull Client getClient();

    @NotNull String getName();

    @NotNull String getBrand();

    @NotNull String getColor();

}
