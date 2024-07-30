package org.ghostface.dev.storage;

import org.ghostface.dev.imp.Client;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ClientRecords extends LocalStorage<@NotNull Client> {

    public ClientRecords(@NotNull String rootFolder) {
        super(rootFolder);
    }

    @Override
    @Nullable Client getById(long clientId) {
        if (entities.stream().anyMatch(client -> client.getId() == clientId)) {
            return entities.stream().filter(client -> client.getId() == clientId).findFirst().orElse(null);
        } else {
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
                @NotNull Client client;
                while (true) {
                    try {
                        client = (Client) in.readObject();
                        if (client.getId() == clientId) {
                            return client;
                        }
                    } catch (ClassNotFoundException e) {
                        break;
                    }
                }
            } catch (IOException e) {
                System.err.println("Failed to read file: " + e.getCause());
            }
        }
        return null;
    }
}
