package entities.spot;

import entities.client.Client;
import entities.client.Clientimp;
import entities.vehicle.Vehicle;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.OffsetDateTime;

public final class ParkinSpotImp implements ParkingSpot {

    private final int id;
    private @Nullable Client client;
    private @Nullable Vehicle vehicle;
    private @Nullable OffsetDateTime dateTime;
    private @NotNull Status status;

    public ParkinSpotImp(int id) {
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
    public void ocuppy(@NotNull Clientimp client) {
        this.client = client;
        this.vehicle = client.getVehicle();
        this.status = Status.OCCUPIED;
    }

    @Override
    public void setStatus(@NotNull Status status) {
        this.status = status;
    }

    @Override
    public void setClient(@Nullable Client client) {
        if (client == null) {
            this.client = null;
            this.vehicle = null;
        } else {
            this.client = client;
            this.vehicle = client.getVehicle();
        }

    }

    @Override
    public void setTime(@NotNull OffsetDateTime dateTime) {
        this.dateTime = dateTime;
    }


}
