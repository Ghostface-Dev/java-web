package factory;

import entities.client.Client;
import entities.vehicle.Vehicle;
import entities.spot.ParkingSpot;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;

import java.util.List;

public interface ParkingFactory {
    // todo criar classes abstratas

    // final
    boolean isAvaliable(@NotNull ParkingSpot spot);

    boolean isAvaliable(@Range(from = 0, to = Long.MAX_VALUE) int spotId);

    void reserveSpot(@NotNull ParkingSpot spot, @NotNull Client client);

    void reserveSpot(@Range(from = 0, to = Long.MAX_VALUE) int spotId, @NotNull Client client);

    void releaseSpot(@NotNull ParkingSpot spot);

    void releaseSpot(@Range(from = 0, to = Long.MAX_VALUE) int spotID);

    @Nullable ParkingSpot getSpot(@NotNull Vehicle vehicle);

    @Nullable ParkingSpot getSpot(@NotNull Client client);

    @NotNull Vehicle getVehicle(@NotNull Client client);

    @NotNull Vehicle getVehicle(@NotNull String plate);

    @Nullable Vehicle getVehicle(@Range(from = 0, to = Long.MAX_VALUE) int spotId);

    @Nullable Vehicle getVehicle(@NotNull ParkingSpot spot);

    @NotNull Client getClient(@Range(from = 0, to = Long.MAX_VALUE) int clientId);

    @Nullable Client getclient(@Range(from = 0, to = Long.MAX_VALUE) int spotId);

    @NotNull ParkingSpot getSpot(@Range(from = 0, to = Long.MAX_VALUE) int id);

    // not final
    @NotNull List<@NotNull Integer> getAvaliableSpots();

    @NotNull List<@NotNull Integer> getOccupedSpot();

    void registerClient(@NotNull Client client);

    void registerVehicle(@NotNull Vehicle vehicle);
    // not final

    // todo criar metodos que peguem infos do veiculos e client
    // todo criar sets de veiculos e emails
}
