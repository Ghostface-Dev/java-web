package factory;

import entities.Client;
import entities.Vehicle;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface Parking {

    boolean isAvaliable(@NotNull ParkingSpot spot);

    void releaseSpot(@NotNull ParkingSpot spot);

    void reserveSpot(@NotNull ParkingSpot spot);

    @Nullable ParkingSpot getSpot(@NotNull Vehicle vehicle);

    @Nullable ParkingSpot getSpot(@NotNull Client client);

    @NotNull Vehicle getVehicle(@NotNull Client client);

    @NotNull ParkingSpot getSpot(int id);

    @NotNull List<@NotNull ParkingSpot> getAvaliableSpots();

    @NotNull List<@NotNull ParkingSpot> getOccupedSpot();

    void registerClient(@NotNull Client client);

    void registerVehicle(@NotNull Vehicle vehicle);

}
