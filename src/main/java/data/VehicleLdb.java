package data;

import entities.vehicle.Vehicle;
import impl.client.Client;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashSet;
import java.util.Set;

public class VehicleLdb extends EntityLdb<Vehicle> {

    public VehicleLdb(@NotNull String rootFolder) {
        super(rootFolder);
    }

    @Override
    public @Nullable Vehicle getById(int id) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            @NotNull Vehicle vehicle;
            while (true) {
                vehicle = (Vehicle) ois.readObject();
                if (vehicle.getId() == id) {
                    return vehicle;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Failed to read entity");
            e.printStackTrace();
        }
        return null;
    }


}
