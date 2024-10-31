package headers;

import com.google.gson.JsonElement;
import ghostface.dev.exception.header.HttpHeaderException;
import ghostface.dev.headers.HttpHeader;
import ghostface.dev.headers.HttpHeaderName;
import ghostface.dev.media.MediaType;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public final class HttpHeaderTest {

    @Nested
    final class JsonContentType {
        private final @NotNull String @NotNull [] valids = new String[] {
                "Content-Type: application/json; charset=UTF-8",
                "Content-Type: application/json",
        };

        @Test
        @DisplayName("Tests application/json")
        public void validate() throws HttpHeaderException {
            for (@NotNull String valid : valids) {
                @NotNull HttpHeader<@NotNull MediaType<@NotNull JsonElement>> header = HttpHeaderName.JSON_CONTENT.parse(valid);
                Assertions.assertEquals(header.getKey().name(), "Content-Type");
                Assertions.assertEquals(header.getAsString(), valid);
                Assertions.assertEquals(header.getValue().toString(), valid.split("\\s*:\\s")[1]);

                Assertions.assertFalse(header.getAsString().equalsIgnoreCase("Content-Type: application/json; charset=UTF-INVALID"));
            }
        }
    }

    @Nested
    final class Connection {

        private final @NotNull String @NotNull [] valids = new String[]{
                "Connection: keep-alive",
                "Connection: close"
        };

        @Test
        @DisplayName("Test the Connection Header")
        public void validate() throws HttpHeaderException {
            for (@NotNull String valid: valids) {
                @NotNull HttpHeader<@NotNull Boolean> header = HttpHeaderName.CONNECTION.parse(valid);
                Assertions.assertEquals(header.getAsString(), valid);
                Assertions.assertEquals(header.getKey().name(), "Connection");

                if (header.getValue() && valid.equals("Connection: keep-alive")) {
                    Assertions.assertTrue(true);
                } else if (!header.getValue() && valid.equals("Connection: close")) {
                    Assertions.assertTrue(true);
                } else {
                   Assertions.fail("Divergent");
                }
            }
        }
    }

    @Nested
    final class ContentLength {

        @Test
        @DisplayName("Test the Content-Length Header")
        public void validate() throws HttpHeaderException {
            @NotNull HttpHeader<@NotNull Integer> content = HttpHeaderName.CONTENT_LENGTH.parse("Content-Length: 137");
            Assertions.assertEquals(content.getValue(), 137);
            Assertions.assertNotEquals(content.getValue(), 136);

            @NotNull HttpHeader<@NotNull Integer> content2 = HttpHeaderName.CONTENT_LENGTH.parse("Content-Length: 0");
            Assertions.assertEquals(content2.getValue(), 0);
            Assertions.assertNotEquals(content2.getValue(), 1);

            try {
                @NotNull HttpHeader<@NotNull Integer> content3 = HttpHeaderName.CONTENT_LENGTH.parse("Content-Length: -500");
                Assertions.fail("Negative number");
            } catch (HttpHeaderException e) {
                Assertions.assertTrue(true);
            }
        }
    }

}
