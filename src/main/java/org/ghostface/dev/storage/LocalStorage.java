package org.ghostface.dev.storage;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public abstract class LocalStorage<T>  {

    protected final @NotNull File file;
    protected final @NotNull Set<T> entities;

    public LocalStorage(@NotNull String rootFolder) {
        @NotNull File directory = new File(rootFolder);
        if (!directory.exists()) {
            throw new RuntimeException("Folder is not exist");
        } else if (directory.isFile()){
            throw new RuntimeException("Directory must be a empty folder");
        } else {
            this.file = new File(directory, "LocalStorageEntities.ser");
            this.entities = new HashSet<>();
            createFile();
        }
    }

    private void createFile() {
        try {
            if (file.createNewFile()) {
                System.out.println("File created on " + file.getAbsoluteFile());
            }
        } catch (IOException e) {
            System.err.println("Failed to create a file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public final void register(T obj) {
        if (entities.contains(obj)) {
            return;
        }
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
            entities.add(obj);
            out.writeObject(obj);
            System.out.println("registered " + getClass().getName());
        } catch (IOException e) {
            System.err.println("Failed to register: " + e.getCause());
        }
    }

    abstract @Nullable T getById(long id);
}
