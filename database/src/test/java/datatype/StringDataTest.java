package datatype;

import ghostface.dev.exception.data.IllegalValueException;
import ghostface.dev.impl.datatype.StringDataType;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public final class StringDataTest {

    @Test
    @DisplayName("Test somes strings")
    public void test() throws IllegalValueException {
        @NotNull StringDataType str = StringDataType.getInstance();

        @NotNull String string = str.read("ilovejava...".getBytes());
        Assertions.assertEquals("ilovejava...", string);

        // special chars
        @NotNull String string1 = str.read("*&&ÉÍ´^oûÇ&*(ÁÁFÁS".getBytes());
        Assertions.assertEquals("*&&ÉÍ´^oûÇ&*(ÁÁFÁS", string1);

    }
}
