package ghostface.dev.protocol;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public abstract class HttpVersion {

    public static @NotNull HttpVersion HTTP1_1() {
        return new HttpVersion("http/1.1", 1, 1) {};
    }

    // Object

    private final @NotNull String id;
    private final int majorVersion;
    private final int minorVersion;

    protected HttpVersion(@NotNull String id, int majorVersion, int minorVersion) {
        this.id = id.toLowerCase();
        this.majorVersion = majorVersion;
        this.minorVersion = minorVersion;
    }

    public @NotNull String getId() {
        return id;
    }

    public int getMajor() {
        return majorVersion;
    }

    public int getMinor() {
        return minorVersion;
    }

    // Natives

    @Override
    public String toString() {
        return "HTTP/" + getMajor() + "." + getMinor();
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
