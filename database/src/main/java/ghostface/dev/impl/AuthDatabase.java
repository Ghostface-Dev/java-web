package ghostface.dev.impl;

import ghostface.dev.config.Authentication;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public final class AuthDatabase implements Authentication {

    private final @NotNull String user;
    private final @NotNull String password;

    public AuthDatabase(@NotNull String user, @NotNull String password) {
        this.user = user;
        this.password = password;
    }

    public @NotNull String getUser() {
        return user;
    }

    public @NotNull String getPassword() {
        return password;
    }

    @Override
    public boolean equals(@Nullable Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        @NotNull AuthDatabase that = (AuthDatabase) o;
        return Objects.equals(that.user, this.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user);
    }
}
