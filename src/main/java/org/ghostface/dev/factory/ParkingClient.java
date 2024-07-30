package org.ghostface.dev.factory;

import org.ghostface.dev.imp.Vehicle;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

public abstract non-sealed class ParkingClient implements UserFactory, Serializable {
    private final long id;

    public ParkingClient(long id) {
        this.id = id;
    }

    public final long getId() {
        return id;
    }

}
