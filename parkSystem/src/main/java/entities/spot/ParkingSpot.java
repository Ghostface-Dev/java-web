package entities.spot;

import entities.client.Client;

import entities.vehicle.Vehicle;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;

import java.time.OffsetDateTime;

public interface ParkingSpot {

    @Range(from = 0, to = Long.MAX_VALUE) int getId();

    @Nullable Vehicle getVehicle();

    @Nullable Client getClient();

    @Nullable OffsetDateTime getInitialHour();

    @NotNull Status getStatus();

    void ocuppy(@NotNull Client client);

    void vacate();

    boolean isAvailable();

    enum Status {
        AVAILABLE, OCCUPIED
    }
}
