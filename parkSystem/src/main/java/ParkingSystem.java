import entities.client.Client;
import entities.client.vehicle.Vehicle;
import entities.spot.ParkinSpotImp;
import entities.spot.ParkingSpot;
import factory.Parking;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.OffsetDateTime;
import java.util.*;

public class ParkingSystem implements Parking {
    private final @NotNull Set<@NotNull Client> clients;
    private final @NotNull Set<@NotNull Vehicle> vehicles;
    private final @NotNull Set<@NotNull ParkingSpot> spots;

    private final @NotNull Map<@NotNull Integer, @NotNull ParkingSpot> spotMap = new HashMap<>();

    public ParkingSystem(int totalSpots) {
        this.clients = new HashSet<>();
        this.vehicles = new HashSet<>();
        this.spots = new HashSet<>();
        for (int i = 1; i < totalSpots; i++) {
            spotMap.put(i, new ParkinSpotImp(i));
        }

    }

    @Override
    public boolean isAvaliable(@NotNull ParkingSpot spot) {
        return spot.getStatus() == ParkingSpot.Status.AVALIABLE;
    }

    @Override
    public boolean isAvaliable(int spotId) {
        if (spotId <=0) {
            System.out.println("Id invalid");
            return false;
        }
        return spots.stream()
                .filter((spot -> spot.getId() == spotId))
                .findFirst()
                .map(spots -> spots.getStatus() == ParkingSpot.Status.AVALIABLE)
                .orElse(false);
    }

    @Override
    public void reserveSpot(@NotNull ParkingSpot spot, @NotNull Client client) {
        if (!isAvaliable(spot)) {
            System.out.println("This spot is in use");
            return;
        }
        spot.setClient(client);
        spot.setStatus(ParkingSpot.Status.OCCUPIED);
        spot.setTime(OffsetDateTime.now());
    }

    @Override
    public void reserveSpot(int spotId, @NotNull Client client) {
        Optional<@NotNull ParkingSpot> optional = spots.stream()
                .filter(spot -> spot.getId() == spotId)
                .findFirst();
        optional.ifPresentOrElse(
                spot -> {
                    spot.setClient(client);
                    spot.setStatus(ParkingSpot.Status.OCCUPIED);
                    spot.setTime(OffsetDateTime.now());
                }, () -> System.out.println("This spot is in use")
        );
    }

    @Override
    public void releaseSpot(@NotNull ParkingSpot spot) {
        spot.setClient(null);
        spot.setStatus(ParkingSpot.Status.AVALIABLE);
        spot.setTime(null);
    }

    @Override
    public void releaseSpot(int spotID) {

    }

    @Override
    public @Nullable ParkingSpot getSpot(@NotNull Vehicle vehicle) {
        return null;
    }

    @Override
    public @Nullable ParkingSpot getSpot(@NotNull Client client) {
        return null;
    }

    @Override
    public @NotNull Vehicle getVehicle(@NotNull Client client) {
        return null;
    }

    @Override
    public @NotNull Client getClient(@NotNull String cpf) {
        return null;
    }

    @Override
    public @NotNull ParkingSpot getSpot(int id) {
        return null;
    }

    @Override
    public @NotNull List<@NotNull ParkingSpot> getAvaliableSpots() {
        return List.of();
    }

    @Override
    public @NotNull List<@NotNull ParkingSpot> getOccupedSpot() {
        return List.of();
    }

    @Override
    public void registerClient(@NotNull Client client) {

    }

    @Override
    public void registerVehicle(@NotNull Vehicle vehicle) {

    }
}
