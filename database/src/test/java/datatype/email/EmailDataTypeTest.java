package datatype.email;

import ghostface.dev.exception.data.IllegalValueException;
import ghostface.dev.impl.datatype.EmailDataType;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public final class EmailDataTypeTest {

    public @NotNull InputStream @NotNull [] streams = new InputStream[] {
            new ByteArrayInputStream("simple@example.com".getBytes()),
            new ByteArrayInputStream("very.common@example.com".getBytes()),
            new ByteArrayInputStream("disposable.style.email.with+symbol@example.com".getBytes()),
            new ByteArrayInputStream("x@example.com".getBytes()),
    };

    @Test
    @DisplayName("Parse input stream")
    public void parse() throws IllegalValueException, IOException {
        @NotNull EmailDataType email = EmailDataType.getInstance();

        for (@NotNull InputStream stream : streams) {
            email.read(stream);
        }
    }
}
