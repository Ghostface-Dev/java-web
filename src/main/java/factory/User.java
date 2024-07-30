package factory;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

public interface User extends Serializable {
    int getId();
    @NotNull String getName();
}
