import entities.client.Client;
import entities.vehicle.Vehicle;
import entities.spot.ParkinSpotImp;
import entities.spot.ParkingSpot;
import factory.Parking;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.OffsetDateTime;
import java.util.*;

public class ParkingSystem implements Parking {
    private final @NotNull Map<@NotNull String, @NotNull Client> clients;
    private final @NotNull Map<@NotNull String, @NotNull Vehicle> vehicles;
    private final @NotNull Map<@NotNull Integer, @NotNull ParkingSpot> spots;

    public ParkingSystem(int totalSpots) {
        this.clients = new HashMap<>();
        this.vehicles = new HashMap<>();
        this.spots = new HashMap<>();
        for (int i = 1; i < totalSpots; i++) {
            spots.put(i, new ParkinSpotImp(i));
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
        return spots.get(spotId).getStatus() == ParkingSpot.Status.AVALIABLE;
    }

    @Override
    public void reserveSpot(@NotNull ParkingSpot spot, @NotNull Client client) {
        if (!isAvaliable(spot)) {
            System.out.println("This spot is in use");
        } else {
            spot.setClient(client);
            spot.setStatus(ParkingSpot.Status.OCCUPIED);
            spot.setTime(OffsetDateTime.now());
        }
    }

    @Override
    public void reserveSpot(int spotId, @NotNull Client client) {
        if (!isAvaliable(spots.get(spotId))) {
            System.out.println("This spot is in use");
        } else {
            spots.get(spotId).setClient(client);
            spots.get(spotId).setStatus(ParkingSpot.Status.OCCUPIED);
            spots.get(spotId).setTime(OffsetDateTime.now());
        }
    }

    @Override
    public void releaseSpot(@NotNull ParkingSpot spot) {
        spot.setClient(null);
        spot.setStatus(ParkingSpot.Status.AVALIABLE);
        spot.setTime(null);
    }

    @Override
    public void releaseSpot(int spotID) {
        spots.get(spotID).setClient(null);
        spots.get(spotID).setStatus(ParkingSpot.Status.AVALIABLE);
        spots.get(spotID).setTime(null);
    }

    @Override
    public @Nullable ParkingSpot getSpot(@NotNull Vehicle vehicle) {
        return vehicle.getSpot();
    }

    @Override
    public @Nullable ParkingSpot getSpot(@NotNull Client client) {
        return client.getVehicle().getSpot();
    }

    @Override
    public @NotNull Vehicle getVehicle(@NotNull Client client) {
        return client.getVehicle();
    }

    @Override
    public @Nullable Vehicle getVehcle(@NotNull String plate) {
        return vehicles.get(plate);
    }

    @Override
    public @Nullable Client getClient(@NotNull String cpf) {
        return clients.get(cpf);
    }

    @Override
    public @Nullable ParkingSpot getSpot(int id) {
        return spots.get(id);
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
        clients.put(client.getCpf(), client);
    }

    @Override
    public void registerVehicle(@NotNull Vehicle vehicle) {
        vehicles.put(vehicle.getPlate(), vehicle);
    }
}
