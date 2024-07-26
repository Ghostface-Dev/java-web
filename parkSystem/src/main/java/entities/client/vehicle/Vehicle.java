package entities.client.vehicle;

import entities.client.Client;
import org.jetbrains.annotations.NotNull;

public interface Vehicle {

    @NotNull String getPlate();
    @NotNull Client getClient();
    @NotNull String getName();
    @NotNull String getBrand();
    @NotNull String getColor();
    void setColor(@NotNull String color);


}
