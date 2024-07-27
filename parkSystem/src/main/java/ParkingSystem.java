import entities.client.Client;
import entities.vehicle.Vehicle;
import entities.spot.ParkinSpotImp;
import entities.spot.ParkingSpot;
import factory.Parking;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;

import java.time.OffsetDateTime;
import java.util.*;
import java.util.stream.Collectors;

public final class ParkingSystem implements Parking {

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
        return spot.isEmpty();
    }

    @Override
    public boolean isAvaliable(@Range(from = 0, to = Long.MAX_VALUE) int spotId) {
        try {
            checkers(spotId);
            if (spotId == 0) {
                System.out.println("Id invalid");
                return false;
            }
            return spots.get(spotId).isEmpty();
        } catch (IllegalArgumentException e) {
            System.out.println("Id invalid");
            return false;
        }
    }

    @Override
    public void reserveSpot(@NotNull ParkingSpot spot, @NotNull Client client) {
        if (!isAvaliable(spot)) {
            System.out.println("This spot is in use");
        } else {
            spot.ocuppy(client);
        }
    }

    @Override
    public void reserveSpot(int spotId, @NotNull Client client) {
        if (!isAvaliable(spots.get(spotId))) {
            System.out.println("This spot is in use");
        } else {
            spots.get(spotId).ocuppy(client);
        }
    }

    @Override
    public void releaseSpot(@NotNull ParkingSpot spot) {
        spot.vacate();
    }

    @Override
    public void releaseSpot(int spotID) {
        spots.get(spotID).vacate();
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
    public @NotNull Vehicle getVehcle(@NotNull String plate) {
        return vehicles.get(plate);
    }

    @Override
    public @NotNull Client getClient(@NotNull String cpf) {
        return clients.get(cpf);
    }

    @Override
    public @NotNull ParkingSpot getSpot(int id) {
        try {
            checkers(id);
            return spots.get(id);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Id invalid");
        }
    }

    @Override
    public @NotNull List<@NotNull Integer> getAvaliableSpots() {
        return spots.values().stream()
                .filter(spot -> spot.getStatus() == ParkingSpot.Status.AVALIABLE)
                .map(ParkingSpot::getId)
                .collect(Collectors.toList());
    }

    @Override
    public @NotNull List<@NotNull Integer> getOccupedSpot() {
        return spots.values().stream()
                .filter(spot -> spot.getStatus() == ParkingSpot.Status.OCCUPIED)
                .map(ParkingSpot::getId)
                .collect(Collectors.toList());
    }

    @Override
    public void registerClient(@NotNull Client client) {
        clients.put(client.getCpf(), client);
    }

    @Override
    public void registerVehicle(@NotNull Vehicle vehicle) {
        vehicles.put(vehicle.getPlate(), vehicle);
    }

    private void checkers(@Range(from = 0, to = Long.MAX_VALUE) int id) {
        if (id > spots.size()) {
            throw new IllegalArgumentException();
        }
    }
}
