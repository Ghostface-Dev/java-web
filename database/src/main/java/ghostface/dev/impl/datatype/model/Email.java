package ghostface.dev.impl.datatype.model;

import codes.laivy.address.domain.SLD;
import codes.laivy.address.domain.Subdomain;
import codes.laivy.address.domain.TLD;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.StringJoiner;
import java.util.regex.Pattern;

public final class Email implements CharSequence {

    // regex is so useless
    @Deprecated
    private static final @NotNull Pattern regex = Pattern.compile("^(?!.*\\.\\.)[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?!-).*[a-zA-Z0-9\\-]+(?:\\.[a-zA-Z0-9-]+)*\\.[a-zA-Z]{2,63}$");

    public static boolean validate(@NotNull String str) {
        @NotNull String s = str.replace("(at)", "@").replace("(dot)", ".");

        if (s.length() < 6) {
            return false;
        } else if (s.length() > 256) {
            return false;
        }

        @NotNull String @NotNull [] parts = s.split("@");

        if (parts.length != 2) {
            return false;
        } else if (!parts[1].contains(".")) {
            return false;
        } else if (parts[0].matches(".*\\.{2,}.*")) {
            return false;
        } else if (parts[0].matches(".*\\s+.*")) {
            return false;
        } else if (parts[0].startsWith(".") || parts[0].endsWith(".")) {
            return false;
        } else if (parts[1].startsWith("-") || parts[1].startsWith(".") || parts[1].contains("!")) {
            return false;
        } else {
            @NotNull String @NotNull [] dots = parts[1].split("\\.");
            if (dots.length == 1) {
                return false;
            } else for (@NotNull String part : dots) {
                if (part.startsWith("-") || part.endsWith("-")) {
                    return false;
                }
            }
        }
        return true;
    }

    public static @NotNull Email parse(@NotNull String str) {
        if (!validate(str)) {
            throw new IllegalArgumentException("The string '" + str + "' is not a valid email");
        } else {
            @NotNull String @NotNull [] parts = str.split("@");

            /* Get the TLD */
            int last = parts[1].split("\\.").length -1;
            @NotNull String tld = parts[1].split("\\.")[last];

            /* Get the rest (without TLD) */
            int index = parts[1].lastIndexOf(".");
            @NotNull String @NotNull [] rest = parts[1].substring(0, index).split("\\.");

            @Nullable Subdomain @Nullable [] subdomains = null;
            if (rest.length > 1) {
                int subs = rest.length -1; // size of subdomains
                subdomains = new Subdomain[subs];
                for (int i = 0; i < subs ; i++) {
                    subdomains[i] = Subdomain.create(rest[i]);
                }
            }

            return new Email(parts[0], subdomains, SLD.parse(rest[rest.length - 1]), TLD.parse(tld));
        }
    }

    // Objects

    private final @NotNull String username;
    private final @Nullable Subdomain @Nullable [] subdomains;
    private final @NotNull SLD sld;
    private final @NotNull TLD tld;

    public Email(@NotNull String username, @Nullable Subdomain @Nullable [] subdomains, @NotNull SLD sld, @NotNull TLD tld) {
        this.username = username;
        this.subdomains = subdomains;
        this.sld = sld;
        this.tld = tld;
        if (!validate(toString())) throw new IllegalArgumentException("The string '" + this + "' is not a valid email");
    }

    public @NotNull String getUsername() {
        return username;
    }

    public @Nullable Subdomain @Nullable [] getSubdomains() {
        return subdomains;
    }

    public @NotNull SLD getSld() {
        return sld;
    }

    public @NotNull TLD getTld() {
        return tld;
    }

    @Override
    public int length() {
        return toString().length();
    }

    @Override
    public char charAt(int index) {
        return toString().charAt(index);
    }


    @Override
    public @NotNull CharSequence subSequence(int start, int end) {
        return toString().subSequence(start, end);
    }

    @Override
    public @NotNull String toString() {
        return getUsername() + "@" + (getSubdomains() == null ? "" : getSubdomainString() + ".") +  getSld() + "." + getTld();
    }

    private @NotNull String getSubdomainString() {
        if (getSubdomains() == null) throw new UnsupportedOperationException();

        @NotNull StringJoiner joiner = new StringJoiner(".");
        for (Subdomain subdomain : getSubdomains()) {
            assert subdomain != null;
            joiner.add(subdomain.toString());
        }

        return joiner.toString();
    }

    @Override
    public boolean equals(@Nullable Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        @NotNull Email email = (Email) object;
        return Objects.equals(username, email.username) && Objects.equals(sld, email.sld) && Objects.equals(tld, email.tld);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, sld, tld);
    }
}
