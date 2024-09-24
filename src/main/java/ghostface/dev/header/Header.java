package ghostface.dev.header;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public interface Header {

    @NotNull Name getName();

    @NotNull String getValue();

    class Name {

        // Static Providers

        public static final @NotNull Name ACCEPT = new Name("accept");
        public static final @NotNull Name AGE = new Name("age");
        public static final @NotNull Name ALLOW = new Name("allow");
        public static final @NotNull Name AUTHORIZATION = new Name("authorization");
        public static final @NotNull Name CONNECTION = new Name("connection");
        public static final @NotNull Name COOKIE = new Name("cookie");
        public static final @NotNull Name DATE = new Name("date");
        public static final @NotNull Name ETAG = new Name("etag");
        public static final @NotNull Name EXPECT = new Name("expect");
        public static final @NotNull Name EXPIRES = new Name("expires");
        public static final @NotNull Name FORWARDED = new Name("forwarded");
        public static final @NotNull Name FROM = new Name("from");
        public static final @NotNull Name HOST = new Name("host");
        public static final @NotNull Name LINK = new Name("link");
        public static final @NotNull Name LOCATION = new Name("location");
        public static final @NotNull Name ORIGIN = new Name("origin");
        public static final @NotNull Name RANGE = new Name("range");
        public static final @NotNull Name REFERER = new Name("referer");
        public static final @NotNull Name REFRESH = new Name("refresh");
        public static final @NotNull Name SERVER = new Name("server");
        public static final @NotNull Name TE = new Name("te");
        public static final @NotNull Name TRAILER = new Name("trailer");
        public static final @NotNull Name UPGRADE = new Name("upgrade");
        public static final @NotNull Name VARY = new Name("vary");
        public static final @NotNull Name VIA = new Name("via");
        public static final @NotNull Name PRIORITY = new Name("priority");

        public static final @NotNull Name ACCEPT_CH = new Name("accept-ch");
        public static final @NotNull Name ACCEPT_CHARSET = new Name("accept-charset");
        public static final @NotNull Name ACCEPT_ENCODING = new Name("accept-encoding");
        public static final @NotNull Name ACCEPT_LANGUAGE = new Name("accept-language");
        public static final @NotNull Name ACCEPT_PATCH = new Name("accept-patch");
        public static final @NotNull Name ACCEPT_POST = new Name("accept-post");
        public static final @NotNull Name ACCEPT_RANGES = new Name("accept-ranges");
        public static final @NotNull Name ACCESS_CONTROL_ALLOW_CREDENTIALS = new Name("access-control-allow-credentials");
        public static final @NotNull Name ACCESS_CONTROL_ALLOW_HEADERS = new Name("access-control-allow-headers");
        public static final @NotNull Name ACCESS_CONTROL_ALLOW_METHODS = new Name("access-control-allow-methods");
        public static final @NotNull Name ACCESS_CONTROL_ALLOW_ORIGIN = new Name("access-control-allow-origin");
        public static final @NotNull Name ACCESS_CONTROL_EXPOSE_HEADERS = new Name("access-control-expose-headers");
        public static final @NotNull Name ACCESS_CONTROL_MAX_AGE = new Name("access-control-max-age");
        public static final @NotNull Name ACCESS_CONTROL_REQUEST_HEADERS = new Name("access-control-request-headers");
        public static final @NotNull Name ACCESS_CONTROL_REQUEST_METHOD = new Name("access-control-request-method");
        public static final @NotNull Name ALT_SVC = new Name("alt-svc");
        public static final @NotNull Name ALT_USED = new Name("alt-used");
        public static final @NotNull Name CACHE_CONTROL = new Name("cache-control");
        public static final @NotNull Name CLEAR_SITE_DATA = new Name("clear-site-data");
        public static final @NotNull Name CONTENT_DISPOSITION = new Name("content-disposition");
        public static final @NotNull Name CONTENT_ENCODING = new Name("content-encoding");
        public static final @NotNull Name CONTENT_LANGUAGE = new Name("content-language");
        public static final @NotNull Name CONTENT_LENGTH = new Name("content-length");
        public static final @NotNull Name CONTENT_LOCATION = new Name("content-location");
        public static final @NotNull Name CONTENT_RANGE = new Name("content-range");
        public static final @NotNull Name CONTENT_SECURITY_POLICY = new Name("content-security-policy");
        public static final @NotNull Name CONTENT_SECURITY_POLICY_REPORT_ONLY = new Name("content-security-policy-report-only");
        public static final @NotNull Name CONTENT_TYPE = new Name("content-type");
        public static final @NotNull Name CROSS_ORIGIN_EMBEDDER_POLICY = new Name("cross-origin-embedder-policy");
        public static final @NotNull Name CROSS_ORIGIN_OPENER_POLICY = new Name("cross-origin-opener-policy");
        public static final @NotNull Name CROSS_ORIGIN_RESOURCE_POLICY = new Name("cross-origin-resource-policy");
        public static final @NotNull Name DEVICE_MEMORY = new Name("device-memory");
        public static final @NotNull Name IF_MATCH = new Name("if-match");
        public static final @NotNull Name IF_MODIFIED_SINCE = new Name("if-modified-since");
        public static final @NotNull Name IF_NONE_MATCH = new Name("if-none-match");
        public static final @NotNull Name IF_RANGE = new Name("if-range");
        public static final @NotNull Name IF_UNMODIFIED_SINCE = new Name("if-unmodified-since");
        public static final @NotNull Name KEEP_ALIVE = new Name("keep-alive");
        public static final @NotNull Name LAST_MODIFIED = new Name("last-modified");
        public static final @NotNull Name MAX_FORWARDS = new Name("max-forwards");
        public static final @NotNull Name PERMISSIONS_POLICY = new Name("permissions-policy");
        public static final @NotNull Name PROXY_AUTHENTICATE = new Name("proxy-authenticate");
        public static final @NotNull Name PROXY_AUTHORIZATION = new Name("proxy-authorization");
        public static final @NotNull Name REFERRER_POLICY = new Name("referrer-policy");
        public static final @NotNull Name RETRY_AFTER = new Name("retry-after");
        public static final @NotNull Name SEC_FETCH_DEST = new Name("sec-fetch-dest");
        public static final @NotNull Name SEC_FETCH_MODE = new Name("sec-fetch-mode");
        public static final @NotNull Name SEC_FETCH_SITE = new Name("sec-fetch-site");
        public static final @NotNull Name SEC_FETCH_USER = new Name("sec-fetch-user");
        public static final @NotNull Name SEC_PURPOSE = new Name("sec-purpose");
        public static final @NotNull Name SEC_WEBSOCKET_ACCEPT = new Name("sec-websocket-accept");
        public static final @NotNull Name SEC_WEBSOCKET_EXTENSIONS = new Name("sec-websocket-extensions");
        public static final @NotNull Name SEC_WEBSOCKET_KEY = new Name("sec-websocket-key");
        public static final @NotNull Name SEC_WEBSOCKET_PROTOCOL = new Name("sec-websocket-protocol");
        public static final @NotNull Name SEC_WEBSOCKET_VERSION = new Name("sec-websocket-version");
        public static final @NotNull Name SERVER_TIMING = new Name("server-timing");
        public static final @NotNull Name SERVICE_WORKER_NAVIGATION_PRELOAD = new Name("service-worker-navigation-preload");
        public static final @NotNull Name SET_COOKIE = new Name("set-cookie");
        public static final @NotNull Name STRICT_TRANSPORT_SECURITY = new Name("strict-transport-security");
        public static final @NotNull Name TIMING_ALLOW_ORIGIN = new Name("timing-allow-origin");
        public static final @NotNull Name TRANSFER_ENCODING = new Name("transfer-encoding");
        public static final @NotNull Name UPGRADE_INSECURE_REQUESTS = new Name("upgrade-insecure-requests");
        public static final @NotNull Name USER_AGENT = new Name("user-agent");
        public static final @NotNull Name WWW_AUTHENTICATE = new Name("www-authenticate");
        public static final @NotNull Name X_CONTENT_TYPE_OPTIONS = new Name("x-content-type-options");
        public static final @NotNull Name X_FRAME_OPTIONS = new Name("x-frame-options");

        public static boolean validate(@NotNull String s) {
            if (s.split("-").length > 6) {
                return false;
            } else if (!Character.isLetter(s.charAt(0))) {
                return false;
            } else {
                return s.matches("^[A-Za-z-]{2,35}$");
            }
        }

        private final @NotNull String string;

        public Name(@NotNull String string) {
            if (!validate(string)) throw new IllegalArgumentException("This Header name is not valid");
            this.string = string;
        }

        public @NotNull String string() {
            return string;
        }

        @Override
        public boolean equals(@Nullable Object object) {
            if (this == object) return true;
            if (object == null || getClass() != object.getClass()) return false;
            Name name = (Name) object;
            return Objects.equals(string, name.string);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(string);
        }
    }
}
