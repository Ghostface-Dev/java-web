package factory;

import entities.Client;
import entities.Vehicle;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.OffsetDateTime;

public interface ParkingSpot {

    int getId();

    @Nullable Vehicle getVehicle(int id);

    @Nullable Client getClient(int id);

    @Nullable OffsetDateTime getHour(@NotNull ParkingSpot spot);

    @Nullable Client getClient(@NotNull ParkingSpot spot);

    void getSize();

    void getStatus();

    void setStatus();

}
