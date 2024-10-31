package headers;

import ghostface.dev.headers.HttpHeaderName;
import ghostface.dev.headers.HttpHeaders;
import ghostface.dev.headers.Target;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public final class HeadersTest {

    @Test
    public void request() throws Throwable {
        @NotNull HttpHeaders headers = new HttpHeaders(Target.REQUEST);
        Assertions.assertTrue(headers.add(HttpHeaderName.CONNECTION.parse("Connection: close")));
        Assertions.assertFalse(headers.add(HttpHeaderName.X_POWERED_BY.parse("X-POWERED-BY: Java")));
    }

    @Test
    public void response() throws Throwable {
        @NotNull HttpHeaders headers = new HttpHeaders(Target.RESPONSE);
        Assertions.assertTrue(headers.add(HttpHeaderName.X_POWERED_BY.parse("X-POWERED-BY: Java")));
        Assertions.assertTrue(headers.add(HttpHeaderName.CONNECTION.parse("Connection: close")));
        Assertions.assertFalse(headers.add(HttpHeaderName.HOST.parse("Host: localhost:8080")));
    }

    @Test
    public void both() throws Throwable {
        @NotNull HttpHeaders headers = new HttpHeaders(Target.BOTH);
        Assertions.assertTrue(headers.add(HttpHeaderName.CONNECTION.parse("Connection: close")));
        Assertions.assertFalse(headers.add(HttpHeaderName.X_POWERED_BY.parse("X-POWERED-BY: Java")));
        Assertions.assertFalse(headers.add(HttpHeaderName.HOST.parse("Host: localhost:8080")));
    }
}
