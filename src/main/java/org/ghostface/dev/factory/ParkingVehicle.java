package org.ghostface.dev.factory;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.time.OffsetDateTime;

public abstract non-sealed class ParkingVehicle implements VehicleFactory, Serializable {

    private final long id;

    public ParkingVehicle(long id) {
        this.id = id;
    }

    @Override
    public final long getId() {
        return id;
    }

    public abstract void setClient(@NotNull ParkingClient client);

}
