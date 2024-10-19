package ghostface.dev.http;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


import java.util.Objects;
import java.util.Optional;

public final class HttpVersion {

    public static @NotNull HttpVersion HTTP_1_1 = new HttpVersion(1, 1);

    public static @Nullable HttpVersion parse(@NotNull String string) {
        @NotNull Optional<@NotNull HttpVersion> optional = string.equalsIgnoreCase(HTTP_1_1.getId()) ? Optional.of(HTTP_1_1) : Optional.empty();
        return optional.orElse(null);
    }

    // Objects

    private final int major;
    private final int minor;
    private final @NotNull String id;

    public HttpVersion(int minor, int major) {
        this.minor = minor;
        this.major = major;
        this.id = "HTTP/" + major + "." + minor;
    }

    public int getMajor() {
        return major;
    }

    public int getMinor() {
        return minor;
    }

    public @NotNull String getId() {
        return id;
    }

    @Override
    public String toString() {
        return id;
    }

    @Override
    public boolean equals(@Nullable Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        @NotNull HttpVersion that = (HttpVersion) object;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

}
