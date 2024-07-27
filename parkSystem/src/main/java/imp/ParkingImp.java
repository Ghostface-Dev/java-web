package imp;

import entities.client.Client;
import entities.spot.ParkingSpot;
import entities.vehicle.Vehicle;
import factory.ParkingFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

public abstract class ParkingImp implements ParkingFactory {

    @Override
    public final void setClientVehicle(@NotNull Client client, @NotNull Vehicle vehicle) {
        client.setVehicle(vehicle);
    }

    @Override
    public final void setClientEmail(@NotNull Client client, @NotNull String email) {
        client.setEmail(email);
    }

    protected abstract void checkers(@Range(from = 0, to = Long.MAX_VALUE) int spotId);

    protected abstract boolean isAvaliable(@NotNull ParkingSpot spot);

}
