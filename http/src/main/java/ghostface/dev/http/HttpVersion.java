package ghostface.dev.http;

import ghostface.dev.http.exception.version.HttpVersionException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


import java.util.Objects;
import java.util.Optional;

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
    private final @NotNull String id;

    public HttpVersion(int major, int minor) {
        this.major = major;
        this.minor = minor;
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
