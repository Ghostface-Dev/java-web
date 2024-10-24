package ghostface.dev;

import ghostface.dev.exception.version.HttpVersionException;
import org.jetbrains.annotations.NotNull;


import java.util.Objects;

public final class HttpVersion {

    public static @NotNull HttpVersion HTTP_1_1 = new HttpVersion(1, 1);
    public static @NotNull HttpVersion HTTP_1_0 = new HttpVersion(1, 1);

    public static @NotNull HttpVersion parse(@NotNull String string) throws HttpVersionException {
        if (!string.toUpperCase().matches("^HTTP/(1\\.1|1\\.0)$")) {
            throw new HttpVersionException("Cannot parse the string '" + string + "' as a valid http version");
        } else {
            return new HttpVersion(1, Integer.parseInt(String.valueOf(string.toCharArray()[7])));
        }
    }

    // Objects

    private final int major;
    private final int minor;

    public HttpVersion(int major, int minor) {
        this.major = major;
        this.minor = minor;
    }

    public int getMajor() {
        return major;
    }

    public int getMinor() {
        return minor;
    }

    @Override
    public @NotNull String toString() {
        return "HTTP/" + getMajor() + "." + getMinor();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        HttpVersion version = (HttpVersion) object;
        return major == version.major && minor == version.minor;
    }

    @Override
    public int hashCode() {
        return Objects.hash(major, minor);
    }
}
