package entities.spot;

import entities.client.Client;
import entities.client.Clientimp;
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

    void setStatus(@NotNull Status status);

    void setClient(@Nullable Client client);

    void setTime(@Nullable OffsetDateTime dateTime);

    boolean isEmpty();

    enum Status {
        AVALIABLE, OCCUPIED
    }
}
