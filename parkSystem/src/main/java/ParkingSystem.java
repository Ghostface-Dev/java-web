import entities.client.Client;
import entities.vehicle.Vehicle;
import entities.spot.ParkinSpotImp;
import entities.spot.ParkingSpot;
import factory.ParkingFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;


import java.util.*;
import java.util.stream.Collectors;

public final class ParkingSystem implements ParkingFactory {

    private final @NotNull Map<@NotNull Integer, @NotNull Client> clients;
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

    // final
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

    // final
    @Override
    public void reserveSpot(@NotNull ParkingSpot spot, @NotNull Client client) {
        if (!isAvaliable(spot)) {
            System.out.println("This spot is in use");
        } else {
            spot.ocuppy(client);
        }
    }

    @Override
    public void reserveSpot(@Range(from = 0, to = Long.MAX_VALUE) int spotId, @NotNull Client client) {
        try {
            checkers(spotId);
            if (!isAvaliable(spots.get(spotId))) {
                System.out.println("This spot is in use");
            } else {
                spots.get(spotId).ocuppy(client);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Id invalid");
        }
    }
    // final
    @Override
    public void releaseSpot(@NotNull ParkingSpot spot) {
        spot.vacate();
    }

    @Override
    public void releaseSpot(@Range(from = 0, to = Long.MAX_VALUE) int spotID) {
        try {
            checkers(spotID);
            spots.get(spotID).vacate();
        } catch (IllegalArgumentException e) {
            System.out.println("Id invalid");
        }
    }
    // final
    @Override
    public @Nullable ParkingSpot getSpot(@NotNull Vehicle vehicle) {
        return vehicle.getSpot();
    }
    // final
    @Override
    public @Nullable ParkingSpot getSpot(@NotNull Client client) {
        return client.getVehicle().getSpot();
    }
    // final
    @Override
    public @NotNull Vehicle getVehicle(@NotNull Client client) {
        return client.getVehicle();
    }

    @Override
    public @NotNull Vehicle getVehicle(@NotNull String plate) {
        return vehicles.get(plate);
    }

    @Override
    public @Nullable Vehicle getVehicle(@Range(from = 0, to = Long.MAX_VALUE) int spotId) {
        try {
            checkers(spotId);
            return spots.get(spotId).getVehicle();
        } catch (IllegalArgumentException e) {
            System.out.println("id invalid");
            return null;
        }
    }

    @Override
    public @Nullable Vehicle getVehicle(@NotNull ParkingSpot spot) {
        return null;
    }

    @Override
    public @NotNull Client getClient(@Range(from = 0, to = Long.MAX_VALUE) int clientId) {
        return clients.get(clientId);
    }

    @Override
    public @Nullable Client getclient(@Range(from = 0, to = Long.MAX_VALUE) int spotId) {
        return spots.get(spotId).getClient();
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
        clients.put(client.getId(), client);
    }

    @Override
    public void registerVehicle(@NotNull Vehicle vehicle) {
        vehicles.put(vehicle.getPlate(), vehicle);
    }
    // abstract
    private void checkers(@Range(from = 0, to = Long.MAX_VALUE) int id) {
        if (id > spots.size()) {
            throw new IllegalArgumentException();
        }
    }
}
