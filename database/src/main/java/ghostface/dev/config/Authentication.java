package ghostface.dev.config;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Authentication {

    @NotNull String getUser();

    @NotNull String getPassword();

    @Override
    boolean equals(@Nullable Object object);

    @Override
    int hashCode();
}
