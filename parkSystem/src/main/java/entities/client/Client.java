package entities.client;

import entities.client.email.Email;
import entities.vehicle.Vehicle;
import org.jetbrains.annotations.NotNull;

public interface Client {

    int getId();

    @NotNull String getCpf();

    @NotNull String getEmail();

    @NotNull Vehicle getVehicle();

    @NotNull String getName();

    void setVehicle(@NotNull Vehicle vehicle);

    void setVehicle(@NotNull Client client, @NotNull Vehicle vehicle);

    void setEmail(@NotNull String email);

    void setEmail(@NotNull Client client, @NotNull String email);

}
