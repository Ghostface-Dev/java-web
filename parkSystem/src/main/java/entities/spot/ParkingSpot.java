package entities.spot;

import entities.client.Client;
import entities.client.Clientimp;
import entities.client.vehicle.Vehicle;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.OffsetDateTime;

public interface ParkingSpot {

    int getId();

    @Nullable Vehicle getVehicle();

    @Nullable Client getClient();

    @Nullable OffsetDateTime getInitialHour();

    @NotNull Status getStatus();

    void ocuppy(@NotNull Clientimp client);

    void setStatus(@NotNull Status status);

    void setClient(@Nullable Client client);

    void setTime(@Nullable OffsetDateTime dateTime);

    enum Status {
        AVALIABLE, OCCUPIED
    }
}
