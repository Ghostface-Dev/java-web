package entities.client;

import entities.client.vehicle.Vehicle;
import org.jetbrains.annotations.NotNull;

public interface Client {

    int getId();
    @NotNull String getCpf();
    @NotNull String getEmail();
    @NotNull Vehicle getVehicle();
    @NotNull String getName();
    void setVehicle(@NotNull Vehicle vehicle);
    void setEmail(@NotNull String email);

}
