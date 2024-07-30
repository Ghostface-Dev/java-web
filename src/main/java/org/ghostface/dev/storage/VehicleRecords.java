package org.ghostface.dev.storage;

import org.ghostface.dev.imp.Vehicle;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class VehicleRecords extends LocalStorage<@NotNull Vehicle> {

    public VehicleRecords(@NotNull String rootFolder) {
        super(rootFolder);
    }

    @Override
    @Nullable Vehicle getById(long vehicleId) {
        if (entities.stream().anyMatch(vehicle -> vehicle.getId() == vehicleId)) {
            return entities.stream().filter(vehicle -> vehicle.getId() == vehicleId).findFirst().orElse(null);
        } else {
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
                @NotNull Vehicle vehicle;
                while (true) {
                    try {
                        vehicle = (Vehicle) in.readObject();
                        if (vehicle.getId() == vehicleId) {
                            return vehicle;
                        }
                    } catch (ClassNotFoundException e) {
                        break;
                    }
                }
            } catch (IOException e) {
                System.err.println("Fail to read file: " + e.getCause());
            }
        }
        return null;
    }
}
