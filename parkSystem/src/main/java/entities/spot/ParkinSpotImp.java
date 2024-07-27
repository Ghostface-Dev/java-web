package entities.spot;

import entities.client.Client;
import entities.vehicle.Vehicle;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;

import java.time.OffsetDateTime;
import java.util.Objects;

public final class ParkinSpotImp implements ParkingSpot {

    private final @Range(from = 0, to = Long.MAX_VALUE) int id;
    private @Nullable Client client;
    private @Nullable Vehicle vehicle;
    private final @Nullable OffsetDateTime dateTime;
    private @NotNull Status status;

    public ParkinSpotImp(@Range(from = 0, to = Long.MAX_VALUE) int id) {
        this.id = id;
        this.client = null;
        this.vehicle = null;
        this.dateTime = null;
        this.status = Status.AVALIABLE;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public @Nullable Vehicle getVehicle() {
        if (client == null) {
            return null;
        }
        this.vehicle = client.getVehicle();
        return vehicle;
    }

    @Override
    public @Nullable Client getClient() {
        return client;
    }

    @Override
    public @Nullable OffsetDateTime getInitialHour() {
        return dateTime;
    }


    @Override
    public @NotNull Status getStatus() {
        return status;
    }

    @Override
    public void ocuppy(@NotNull Client client) {
        this.client = client;
        this.vehicle = client.getVehicle();
        this.status = Status.OCCUPIED;
    }

    @Override
    public void vacate() {
        this.status = Status.AVALIABLE;
        this.client = null;
        this.vehicle = null;
    }

    @Override
    public boolean isEmpty() {
        return status == Status.AVALIABLE;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        ParkinSpotImp that = (ParkinSpotImp) object;
        return id == that.id && Objects.equals(client, that.client) && Objects.equals(vehicle, that.vehicle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, client, vehicle);
    }
}
