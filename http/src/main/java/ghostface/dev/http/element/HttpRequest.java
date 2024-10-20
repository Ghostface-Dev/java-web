package ghostface.dev.http.element;

import ghostface.dev.http.HttpMethod;

import ghostface.dev.http.HttpVersion;
import ghostface.dev.http.exception.FormatHttpException;
import org.jetbrains.annotations.NotNull;

import java.net.URI;

public interface HttpRequest extends HttpElement {

    static @NotNull Object[] readFirstLine(@NotNull String firtsLine) throws FormatHttpException {
        if (firtsLine.isEmpty()) {
            throw new FormatHttpException("The string cannot be null");
        } else if (!firtsLine.endsWith("\r\n")) {
            throw new FormatHttpException("The first line does not have a CRLF");
        } else if (firtsLine.length() < 14) {
            throw new FormatHttpException("The first line is too small");
        } else if (firtsLine.split("\\s").length != 3) {
            throw new FormatHttpException("The first line string has illegal format");
        } else {
            @NotNull String[] parts = firtsLine.split("\\s");

            try {
                @NotNull HttpMethod method = HttpMethod.valueOf(parts[0]);
                @NotNull URI uri = URI.create(parts[1]);
                @NotNull HttpVersion version = HttpVersion.parse(parts[2]);
                return new Object[] {method, uri, version};
            } catch (@NotNull Throwable throwable) {
                throw new FormatHttpException("Cannot parse string '" + firtsLine + "'", throwable);
            }
        }
    }

    @NotNull HttpMethod getMethod();

    @NotNull URI getURI();

    // todo: add URI Authority
}
