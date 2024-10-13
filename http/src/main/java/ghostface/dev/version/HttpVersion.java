package ghostface.dev.version;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

// TODO: transform HTTP Version in abstract class because each http version has specific factory
public class HttpVersion {

    // Static Providers

    public static @NotNull HttpVersion HTTP_1_0 = new HttpVersion(1, 0);
    public static @NotNull HttpVersion HTTP_1_1 = new HttpVersion(1, 1);

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
