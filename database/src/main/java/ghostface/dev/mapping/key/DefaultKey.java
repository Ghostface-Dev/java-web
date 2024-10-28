package ghostface.dev.mapping.key;

import ghostface.dev.exception.data.IllegalValueException;
import ghostface.dev.type.standard.DefaultDataType;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

public final class DefaultKey extends Key<@NotNull UUID> {

    public static @NotNull Key<@NotNull UUID> create() {
        return new DefaultKey(UUID.randomUUID());
    }

    // Objects

    public DefaultKey(@NotNull UUID uuid) {
        super(uuid);
    }

    @Override
    public @NotNull UUID read(byte @NotNull [] bytes) throws IllegalValueException {
        return DefaultDataType.getInstance().read(bytes);
    }

    @Override
    public @NotNull UUID read(@NotNull Object object) throws IllegalValueException {
        return DefaultDataType.getInstance().read(object);
    }

    @Override
    public @NotNull UUID read(@NotNull InputStream stream) throws IllegalValueException, IOException {
        return DefaultDataType.getInstance().read(stream);
    }
}
