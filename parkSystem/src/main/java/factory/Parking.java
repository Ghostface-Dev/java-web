package factory;

import entities.client.Client;
import entities.vehicle.Vehicle;
import entities.spot.ParkingSpot;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;

import java.util.List;

public interface Parking {
    // todo colocar verificador nos iD (maior que a quantidade de vagas)

    boolean isAvaliable(@NotNull ParkingSpot spot);

    boolean isAvaliable(@Range(from = 0, to = Long.MAX_VALUE) int spotId);

    void reserveSpot(@NotNull ParkingSpot spot, @NotNull Client client);

    void reserveSpot(@Range(from = 0, to = Long.MAX_VALUE) int spotId, @NotNull Client client);

    void releaseSpot(@NotNull ParkingSpot spot);

    void releaseSpot(@Range(from = 0, to = Long.MAX_VALUE) int spotID);

    @Nullable ParkingSpot getSpot(@NotNull Vehicle vehicle);

    @Nullable ParkingSpot getSpot(@NotNull Client client);

    @NotNull Vehicle getVehicle(@NotNull Client client);

    @NotNull Vehicle getVehcle(@NotNull String plate);

    @NotNull Client getClient(@NotNull String cpf);

    @NotNull ParkingSpot getSpot(@Range(from = 0, to = Long.MAX_VALUE) int id);

    @NotNull List<@NotNull Integer> getAvaliableSpots();

    @NotNull List<@NotNull Integer> getOccupedSpot();

    void registerClient(@NotNull Client client);

    void registerVehicle(@NotNull Vehicle vehicle);


}
