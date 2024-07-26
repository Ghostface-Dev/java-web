package factory;

import entities.client.Client;
import entities.client.Clientimp;
import entities.client.vehicle.Vehicle;
import entities.spot.ParkingSpot;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface Parking {

    boolean isAvaliable(@NotNull ParkingSpot spot);

    boolean isAvaliable(int spotId);

    void reserveSpot(@NotNull ParkingSpot spot, @NotNull Client client);

    void reserveSpot(int spotId, @NotNull Client client);

    void releaseSpot(@NotNull ParkingSpot spot);

    void releaseSpot(int spotID);

    @Nullable ParkingSpot getSpot(@NotNull Vehicle vehicle);

    @Nullable ParkingSpot getSpot(@NotNull Client client);

    @NotNull Vehicle getVehicle(@NotNull Client client);

    @NotNull Client getClient(@NotNull String cpf);

    @NotNull ParkingSpot getSpot(int id);

    @NotNull List<@NotNull ParkingSpot> getAvaliableSpots();

    @NotNull List<@NotNull ParkingSpot> getOccupedSpot();

    void registerClient(@NotNull Client client);

    void registerVehicle(@NotNull Vehicle vehicle);


}
