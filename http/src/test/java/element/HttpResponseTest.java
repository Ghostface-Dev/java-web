package element;

import codes.laivy.address.host.HttpHost;
import ghostface.dev.element.HttpRequest;
import ghostface.dev.headers.HttpHeader;
import ghostface.dev.headers.HttpHeaderName;
import ghostface.dev.utils.HttpRequestUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HttpResponseTest {

    private final @NotNull String @NotNull [] valids = new String[] {
            "GET /index.java HTTP/1.1\r\nHost: localhost\r\n\r\n",
            "GET /index HTTP/1.1\r\nHost: 192.0.2.1:8080\r\n\r\n",
            "PUT /user/123 HTTP/1.1\r\nHost: 192.0.2.1:8080\r\n\r\n",
    };

    @Test
    @DisplayName("Tests an simple request")
    public void validate() throws Throwable {
        for (@NotNull String valid : valids) {
            @NotNull HttpRequest request = HttpRequestUtils.read(valid);
            @NotNull String @NotNull [] first = valid.split("\r\n")[0].split("\\s");

            Assertions.assertEquals(request.getMethod().name(), first[0]);
            Assertions.assertEquals(request.getURI().getPath(), first[1]);
            Assertions.assertEquals(request.getVersion().toString(), first[2]);
            Assertions.assertEquals(0, request.getBody().length());

            @Nullable HttpHeader<@NotNull HttpHost<?>> host = request.getHeaders().getHeader(HttpHeaderName.HOST).orElse(null);

            assert host != null;
            Assertions.assertEquals(host.getAsString(), valid.split("\r\n")[1]);
        }
    }

    @Test
    @DisplayName("Verify if the Body length matches the Content-Length value")
    public void bodyEquals() throws Throwable {
        @NotNull String str =
                        "POST /users HTTP/1.1\r\n" +
                        "Host: localhost\r\n" +
                        "Connection: keep-alive\r\n" +
                        "Content-Type: application/json\r\n" +
                        "Content-Length: 22\r\n\r\n" +
                        "{\"test\":\"hello world\"}";

        @NotNull HttpRequest request = HttpRequestUtils.read(str);
        @NotNull String @NotNull [] first = str.split("\r\n\r\n")[0].split("\\s");

        Assertions.assertEquals(request.getMethod().name(), first[0]);
        Assertions.assertEquals(request.getURI().getPath(), first[1]);
        Assertions.assertEquals(request.getVersion().toString(), first[2]);

        @Nullable HttpHeader<@NotNull Integer> length = request.getHeaders().getHeader(HttpHeaderName.CONTENT_LENGTH).orElse(null);

        assert length != null;
        Assertions.assertEquals(request.getBody().length(), length.getValue());
    }
}
