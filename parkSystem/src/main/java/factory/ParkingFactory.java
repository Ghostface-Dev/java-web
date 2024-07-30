package factory;

import entities.client.Client;
import entities.vehicle.Vehicle;
import entities.spot.ParkingSpot;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ParkingFactory {

    void reserveSpot(@Range(from = 0, to = Long.MAX_VALUE) int spotId, @NotNull Client client);

    void reserveSpot(@NotNull ParkingSpot spot, @NotNull Client client);

    void releaseSpot(@Range(from = 0, to = Long.MAX_VALUE) int spotId);

    @Nullable ParkingSpot getSpot(@NotNull Vehicle vehicle);

    @Nullable ParkingSpot getSpot(@NotNull Client client);

    @NotNull Vehicle getVehicle(@NotNull String plate);

    @Nullable Vehicle getVehicle(@Range(from = 0, to = Long.MAX_VALUE) int spotId);

    @Nullable Client getClient(@Range(from = 0, to = Long.MAX_VALUE) int spotId);

    @Nullable Client getClient(@NotNull String cpf);

    @NotNull Optional<@NotNull Client> getClient(@Range(from = 0, to = Long.MAX_VALUE) long spotId);

    @NotNull ParkingSpot getSpot(@Range(from = 0, to = Long.MAX_VALUE) int id);

    @Nullable OffsetDateTime getTime(@Range(from = 0, to = Long.MAX_VALUE) int spotId);

    @NotNull Set<@NotNull Integer> getAvaliableSpots();

    @NotNull Set<@NotNull Integer> getOccupedSpot();

    void registerClient(@NotNull Client client);

    void registerVehicle(@NotNull Vehicle vehicle);

}
