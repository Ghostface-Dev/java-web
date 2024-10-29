package ghostface.dev.mapping.key;

import ghostface.dev.type.ConcreteType;
import ghostface.dev.impl.datatype.UUIDDataType;
import org.jetbrains.annotations.NotNull;


import java.util.UUID;

public final class UUIDKey extends Key<@NotNull UUID> {

    public static @NotNull Key<@NotNull UUID> create() {
        return new UUIDKey(UUID.randomUUID());
    }

    // Objects

    public UUIDKey(@NotNull UUID uuid) {
        super(uuid);
    }

    @Override
    public @NotNull ConcreteType<@NotNull UUID> concreteType() {
        return UUIDDataType.getInstance();
    }

}
