package ghostface.dev.utils;

import com.google.gson.JsonParser;
import ghostface.dev.exception.HttpParserException;
import ghostface.dev.HttpMethod;
import ghostface.dev.HttpVersion;
import org.jetbrains.annotations.NotNull;

import java.net.URI;


/*
* This utility class is designed to read the first line of an HTTP request.
* */
public final class HttpReader {

    public static @NotNull HttpVersion readVersion(@NotNull String firstLine) throws HttpParserException {
        try {
            return HttpVersion.parse(checkAndSplit(firstLine)[2]);
        } catch (@NotNull Throwable throwable) {
            throw new HttpParserException("Cannot parse string '" + firstLine + "' as a valid HTTP Version", throwable);
        }
    }

    public static @NotNull URI readURI(@NotNull String firstLine) throws HttpParserException {
        try {
            return URI.create(checkAndSplit(firstLine)[1]);
        } catch (@NotNull Throwable throwable) {
            throw new HttpParserException("Cannot parse string '" + firstLine + "' as a valid URI", throwable);
        }
    }

    public static @NotNull HttpMethod readMethod(@NotNull String firstLine) throws HttpParserException {
        try {
            return HttpMethod.valueOf(checkAndSplit(firstLine)[0]);
        } catch (@NotNull Throwable throwable) {
            throw new HttpParserException("Cannot parse string '" + firstLine + "' as a valid HTTP Method", throwable);
        }
    }

    /*
    * Private method of HttpUtils to validate, read and split the first line of an HTTP request
    * */
    private static @NotNull String @NotNull [] checkAndSplit(@NotNull String firstLine) throws HttpParserException {
        if (firstLine.isEmpty()) {
            throw new HttpParserException("The string cannot be null");
        } else if (firstLine.length() < 14) {
            throw new HttpParserException("The first line is too small");
        } else if (firstLine.split("\\s").length != 3) {
            throw new HttpParserException("The first line string has illegal format");
        } else {
            return firstLine.split("\\s");
        }
    }

    // Constructor
    private HttpReader() {
        throw new UnsupportedOperationException();
    }
}




