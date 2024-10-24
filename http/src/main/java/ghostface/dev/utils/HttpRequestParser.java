package ghostface.dev.utils;

import com.google.gson.JsonElement;
import ghostface.dev.HttpMethod;
import ghostface.dev.HttpVersion;
import ghostface.dev.body.HttpBody;
import ghostface.dev.element.HttpRequest;
import ghostface.dev.exception.HttpException;
import ghostface.dev.exception.HttpParserException;
import ghostface.dev.exception.header.HttpHeaderException;
import ghostface.dev.exception.media.MediaParserException;
import ghostface.dev.headers.HttpHeader;
import ghostface.dev.headers.HttpHeaderName;
import ghostface.dev.headers.HttpHeaders;
import ghostface.dev.headers.Target;
import ghostface.dev.impl.HttpRequestImpl;
import ghostface.dev.media.MediaType;
import ghostface.dev.media.json.JsonMediaType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;

public final class HttpRequestParser {

    private static final @NotNull String CRLF = "\r\n";

    public static @NotNull HttpRequest read(final @NotNull String string) throws HttpException {
        if (!validate(string)) {
            throw new HttpParserException("The string '" + string + "' is a invalid http request");
        } else {
            final @NotNull BufferedReader reader = new BufferedReader(new StringReader(string));

            try {
                /*
                * Instantly read and verify the first line of the HTTP request.
                * */
                @NotNull String line = reader.readLine();

                final @NotNull HttpMethod method = HttpRequestReadLine.readMethod(line);
                final @NotNull URI uri = HttpRequestReadLine.readURI(line);
                final @NotNull HttpVersion version = HttpRequestReadLine.readVersion(line);

                /*
                * Host
                *  */
                line = reader.readLine();
                if (line == null || line.isEmpty()) {
                    throw new HttpParserException("Cannot find the host");
                }


                /*
                * Preparing to read the headers until a double CRLF is encountered.
                * */
                final @NotNull HttpHeaders headers = new HttpHeaders(Target.REQUEST);

                @Nullable HttpHeader<MediaType<@NotNull JsonElement>> jsonMedia = null;
                @Nullable HttpHeader<@NotNull Integer> contentLength = null;

                while ((line = reader.readLine()) != null && !line.contains("\r\n\r\n")) {
                    if (line.contains("Connection")) {
                        @NotNull HttpHeader<@NotNull Boolean> connection = HttpHeaderName.CONNECTION.parse(line);
                        headers.add(connection);
                    } else if (line.contains("application/json")) {
                        jsonMedia = HttpHeaderName.JSON_CONTENT.parse(line);
                    } else if (line.contains("Content-Length")) {
                        contentLength = HttpHeaderName.CONTENT_LENGTH.parse(line);
                        headers.add(contentLength);
                    }
                }

                /*
                * Create body
                * */
                @NotNull HttpBody body;
                if (jsonMedia != null) {
                    body = HttpBody.create(string.split("\r\n\r\n")[1].getBytes());
                    @NotNull JsonElement element = JsonMediaType.parser.deserialize(body.getInputStream());
                    jsonMedia.setValue(new JsonMediaType(element, body));

                    headers.add(jsonMedia);
                } else {
                    body = HttpBody.empty();
                }

                if (contentLength != null && contentLength.getValue() != body.length()) {
                    throw new HttpException("The specified length differs from the actual content length");
                }

                return new HttpRequestImpl(method, uri, headers, body);

            } catch (IOException e) {
                throw new HttpParserException("A erro occurred: " + e.getMessage(), e.getCause());
            } catch (HttpHeaderException e) {
                throw new HttpParserException("Cannot parse headers", e);
            } catch (MediaParserException e) {
                throw new HttpParserException("Cannot parse the Body content: " + e.getMessage(), e.getCause().getCause());
            }
        }
    }

    private static boolean validate(@NotNull String str) {
        if (str.isEmpty()) {
            return false;
        } else if (!str.contains("\r\n")) {
            return false;
        } else if (!str.contains("\r\n\r\n")) {
            return false;
        } else if (str.split("\r\n").length < 2) {
            return false;
        } else {
            return true;
        }
    }

    private HttpRequestParser() {
        throw new UnsupportedOperationException();
    }
}
