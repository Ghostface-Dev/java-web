package org.ghostface.dev.factory;

import org.jetbrains.annotations.NotNull;

sealed interface UserFactory permits ParkingClient {

    @NotNull String getName();

    @NotNull String getEmail();

    @NotNull String getCpf();

    void setEmail(@NotNull String email);

}
