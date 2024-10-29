package datatype.email;

import ghostface.dev.impl.datatype.Email;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class EmailTest {

    private static final Logger log = LoggerFactory.getLogger(EmailTest.class);

    private final @NotNull String @NotNull [] valids = new String[] {
            "simple@example.com",
            "very.common@example.com",
            "disposable.style.email.with+symbol@example.com",
            "other.email-with-hyphen@example.com",
            "fully-qualified-domain@example.com",
            "user.name+tag+sorting@example.com",
            "x@example.com",
            "example-indeed@strange-example.com",
            "user@subdomain.example.com",
            "special_chars@example.co.uk",
            "user%example.com@example.org",
            "user.test@subdomin.more.example.com"
    };

    private final @NotNull String @NotNull [] invalids = new String[] {
            "plainaddress",
            ".starts.with.dot@example.com",
            "ends.with.dot.@example.com",
            "consecutive..dots@example.com",
            "username@-example.com",
            "username@@example.com",
            "username@example!domain.com",
            "username@domain",
            "user name@example.com",
            "username@example-.com",
            "username@.com"
    };

    @Test
    @DisplayName("Tests valids emails")
    public void testValid() {
        for (@NotNull String valid : valids) {
            Assertions.assertTrue(Email.validate(valid));

            @NotNull Email email = Email.parse(valid);
            Assertions.assertEquals(email.toString(), valid);
        }
    }

    @Test
    @DisplayName("Tests invalids emails")
    public void testInvalid() {
        for (@NotNull String invalid: invalids) {
            try {
                @NotNull Email email = Email.parse(invalid);
                Assertions.fail();
            } catch (@NotNull Throwable throwable) {
                log.info("String: {}", invalid);
                Assertions.assertFalse(Email.validate(invalid));
            }
        }
    }
}
