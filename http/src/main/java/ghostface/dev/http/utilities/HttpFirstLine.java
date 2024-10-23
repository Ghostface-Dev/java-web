package ghostface.dev.http.utilities;

import ghostface.dev.http.HttpMethod;
import ghostface.dev.http.HttpVersion;
import ghostface.dev.http.exception.HttpFormatException;
import ghostface.dev.http.exception.version.HttpVersionParserException;
import org.jetbrains.annotations.NotNull;

import java.net.URI;


public final class HttpFirstLine {

    // Classes

    public static @NotNull HttpVersion readVersion(@NotNull String firstLine) throws HttpVersionParserException {
        try {
            return HttpVersion.parse(checkAndSplit(firstLine)[2]);
        } catch (@NotNull Throwable throwable) {
            throw new HttpVersionParserException("Cannot parse string '" + firstLine + "' as a valid HTTP Version", throwable);
        }
    }

    public static @NotNull URI readURI(@NotNull String firstLine) throws HttpVersionParserException {
        try {
            return URI.create(checkAndSplit(firstLine)[1]);
        } catch (@NotNull Throwable throwable) {
            throw new HttpVersionParserException("Cannot parse string '" + firstLine + "' as a valid URI", throwable);
        }
    }

    public static @NotNull HttpMethod readMethod(@NotNull String firstLine) throws HttpVersionParserException {
        try {
            return HttpMethod.valueOf(checkAndSplit(firstLine)[0]);
        } catch (@NotNull Throwable throwable) {
            throw new HttpVersionParserException("Cannot parse string '" + firstLine + "' as a valid HTTP Method", throwable);
        }
    }

    /*
    * Private method of HttpUtils to validate, read and split the first line of an HTTP request
    * */
    private static @NotNull String @NotNull [] checkAndSplit(@NotNull String firstLine) throws HttpFormatException {
        if (firstLine.isEmpty()) {
            throw new HttpFormatException("The string cannot be null");
        } else if (firstLine.length() < 14) {
            throw new HttpFormatException("The first line is too small");
        } else if (firstLine.split("\\s").length != 3) {
            throw new HttpFormatException("The first line string has illegal format");
        } else {
            return firstLine.split("\\s");
        }
    }

    // Constructor
    private HttpFirstLine() {
        throw new UnsupportedOperationException();
    }
}




