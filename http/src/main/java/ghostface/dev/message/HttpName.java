package ghostface.dev.message;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;

public class HttpName {

    // Providers

    public static final @NotNull HttpName ACCEPT = new HttpName("accept");
    public static final @NotNull HttpName AGE = new HttpName("age");
    public static final @NotNull HttpName ALLOW = new HttpName("allow");
    public static final @NotNull HttpName AUTHORIZATION = new HttpName("authorization");
    public static final @NotNull HttpName CONNECTION = new HttpName("connection");
    public static final @NotNull HttpName COOKIE = new HttpName("cookie");
    public static final @NotNull HttpName DATE = new HttpName("date");
    public static final @NotNull HttpName ETAG = new HttpName("etag");
    public static final @NotNull HttpName EXPECT = new HttpName("expect");
    public static final @NotNull HttpName EXPIRES = new HttpName("expires");
    public static final @NotNull HttpName FORWARDED = new HttpName("forwarded");
    public static final @NotNull HttpName FROM = new HttpName("from");
    public static final @NotNull HttpName HOST = new HttpName("host");
    public static final @NotNull HttpName LINK = new HttpName("link");
    public static final @NotNull HttpName LOCATION = new HttpName("location");
    public static final @NotNull HttpName ORIGIN = new HttpName("origin");
    public static final @NotNull HttpName RANGE = new HttpName("range");
    public static final @NotNull HttpName REFERER = new HttpName("referer");
    public static final @NotNull HttpName REFRESH = new HttpName("refresh");
    public static final @NotNull HttpName SERVER = new HttpName("server");
    public static final @NotNull HttpName TE = new HttpName("te");
    public static final @NotNull HttpName TRAILER = new HttpName("trailer");
    public static final @NotNull HttpName UPGRADE = new HttpName("upgrade");
    public static final @NotNull HttpName VARY = new HttpName("vary");
    public static final @NotNull HttpName VIA = new HttpName("via");
    public static final @NotNull HttpName PRIORITY = new HttpName("priority");

    public static final @NotNull HttpName ACCEPT_CH = new HttpName("accept-ch");
    public static final @NotNull HttpName ACCEPT_CHARSET = new HttpName("accept-charset");
    public static final @NotNull HttpName ACCEPT_ENCODING = new HttpName("accept-encoding");
    public static final @NotNull HttpName ACCEPT_LANGUAGE = new HttpName("accept-language");
    public static final @NotNull HttpName ACCEPT_PATCH = new HttpName("accept-patch");
    public static final @NotNull HttpName ACCEPT_POST = new HttpName("accept-post");
    public static final @NotNull HttpName ACCEPT_RANGES = new HttpName("accept-ranges");
    public static final @NotNull HttpName ACCESS_CONTROL_ALLOW_CREDENTIALS = new HttpName("access-control-allow-credentials");
    public static final @NotNull HttpName ACCESS_CONTROL_ALLOW_HEADERS = new HttpName("access-control-allow-headers");
    public static final @NotNull HttpName ACCESS_CONTROL_ALLOW_METHODS = new HttpName("access-control-allow-methods");
    public static final @NotNull HttpName ACCESS_CONTROL_ALLOW_ORIGIN = new HttpName("access-control-allow-origin");
    public static final @NotNull HttpName ACCESS_CONTROL_EXPOSE_HEADERS = new HttpName("access-control-expose-headers");
    public static final @NotNull HttpName ACCESS_CONTROL_MAX_AGE = new HttpName("access-control-max-age");
    public static final @NotNull HttpName ACCESS_CONTROL_REQUEST_HEADERS = new HttpName("access-control-request-headers");
    public static final @NotNull HttpName ACCESS_CONTROL_REQUEST_METHOD = new HttpName("access-control-request-method");
    public static final @NotNull HttpName ALT_SVC = new HttpName("alt-svc");
    public static final @NotNull HttpName ALT_USED = new HttpName("alt-used");
    public static final @NotNull HttpName CACHE_CONTROL = new HttpName("cache-control");
    public static final @NotNull HttpName CLEAR_SITE_DATA = new HttpName("clear-site-data");
    public static final @NotNull HttpName CONTENT_DISPOSITION = new HttpName("content-disposition");
    public static final @NotNull HttpName CONTENT_ENCODING = new HttpName("content-encoding");
    public static final @NotNull HttpName CONTENT_LANGUAGE = new HttpName("content-language");
    public static final @NotNull HttpName CONTENT_LENGTH = new HttpName("content-length");
    public static final @NotNull HttpName CONTENT_LOCATION = new HttpName("content-location");
    public static final @NotNull HttpName CONTENT_RANGE = new HttpName("content-range");
    public static final @NotNull HttpName CONTENT_SECURITY_POLICY = new HttpName("content-security-policy");
    public static final @NotNull HttpName CONTENT_SECURITY_POLICY_REPORT_ONLY = new HttpName("content-security-policy-report-only");
    public static final @NotNull HttpName CONTENT_TYPE = new HttpName("content-type");
    public static final @NotNull HttpName CROSS_ORIGIN_EMBEDDER_POLICY = new HttpName("cross-origin-embedder-policy");
    public static final @NotNull HttpName CROSS_ORIGIN_OPENER_POLICY = new HttpName("cross-origin-opener-policy");
    public static final @NotNull HttpName CROSS_ORIGIN_RESOURCE_POLICY = new HttpName("cross-origin-resource-policy");
    public static final @NotNull HttpName DEVICE_MEMORY = new HttpName("device-memory");
    public static final @NotNull HttpName IF_MATCH = new HttpName("if-match");
    public static final @NotNull HttpName IF_MODIFIED_SINCE = new HttpName("if-modified-since");
    public static final @NotNull HttpName IF_NONE_MATCH = new HttpName("if-none-match");
    public static final @NotNull HttpName IF_RANGE = new HttpName("if-range");
    public static final @NotNull HttpName IF_UNMODIFIED_SINCE = new HttpName("if-unmodified-since");
    public static final @NotNull HttpName KEEP_ALIVE = new HttpName("keep-alive");
    public static final @NotNull HttpName LAST_MODIFIED = new HttpName("last-modified");
    public static final @NotNull HttpName MAX_FORWARDS = new HttpName("max-forwards");
    public static final @NotNull HttpName PERMISSIONS_POLICY = new HttpName("permissions-policy");
    public static final @NotNull HttpName PROXY_AUTHENTICATE = new HttpName("proxy-authenticate");
    public static final @NotNull HttpName PROXY_AUTHORIZATION = new HttpName("proxy-authorization");
    public static final @NotNull HttpName REFERRER_POLICY = new HttpName("referrer-policy");
    public static final @NotNull HttpName RETRY_AFTER = new HttpName("retry-after");
    public static final @NotNull HttpName SEC_FETCH_DEST = new HttpName("sec-fetch-dest");
    public static final @NotNull HttpName SEC_FETCH_MODE = new HttpName("sec-fetch-mode");
    public static final @NotNull HttpName SEC_FETCH_SITE = new HttpName("sec-fetch-site");
    public static final @NotNull HttpName SEC_FETCH_USER = new HttpName("sec-fetch-user");
    public static final @NotNull HttpName SEC_PURPOSE = new HttpName("sec-purpose");
    public static final @NotNull HttpName SEC_WEBSOCKET_ACCEPT = new HttpName("sec-websocket-accept");
    public static final @NotNull HttpName SEC_WEBSOCKET_EXTENSIONS = new HttpName("sec-websocket-extensions");
    public static final @NotNull HttpName SEC_WEBSOCKET_KEY = new HttpName("sec-websocket-key");
    public static final @NotNull HttpName SEC_WEBSOCKET_PROTOCOL = new HttpName("sec-websocket-protocol");
    public static final @NotNull HttpName SEC_WEBSOCKET_VERSION = new HttpName("sec-websocket-version");
    public static final @NotNull HttpName SERVER_TIMING = new HttpName("server-timing");
    public static final @NotNull HttpName SERVICE_WORKER_NAVIGATION_PRELOAD = new HttpName("service-worker-navigation-preload");
    public static final @NotNull HttpName SET_COOKIE = new HttpName("set-cookie");
    public static final @NotNull HttpName STRICT_TRANSPORT_SECURITY = new HttpName("strict-transport-security");
    public static final @NotNull HttpName TIMING_ALLOW_ORIGIN = new HttpName("timing-allow-origin");
    public static final @NotNull HttpName TRANSFER_ENCODING = new HttpName("transfer-encoding");
    public static final @NotNull HttpName UPGRADE_INSECURE_REQUESTS = new HttpName("upgrade-insecure-requests");
    public static final @NotNull HttpName USER_AGENT = new HttpName("user-agent");
    public static final @NotNull HttpName WWW_AUTHENTICATE = new HttpName("www-authenticate");
    public static final @NotNull HttpName X_CONTENT_TYPE_OPTIONS = new HttpName("x-content-type-options");
    public static final @NotNull HttpName X_FRAME_OPTIONS = new HttpName("x-frame-options");

    private static final @NotNull Set<@NotNull HttpName> collection = new LinkedHashSet<>();

    public static boolean add(@NotNull HttpName name) {
        return collection.add(name);
    }

    public static boolean remove(@NotNull HttpName name) {
        return collection.remove(name);
    }

    public static boolean contains(@NotNull HttpName name) {
        return collection.contains(name);
    }

    public static @NotNull Collection<@NotNull HttpName> collection() {
        return Collections.unmodifiableSet(collection);
    }

    // Static initializer

    static {
        for (@NotNull Field field : HttpName.class.getDeclaredFields()) {
            if (Modifier.isPublic(field.getModifiers()) && Modifier.isStatic(field.getModifiers())) {
                try {
                    @NotNull HttpName name = (HttpName) field.get(HttpName.class);
                    collection.add(name);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("Cannot loading http name '" + field.getName() + "'", e) ;
                }
            }
        }
    }

    public static boolean validate(@NotNull String s) {
        if (s.split("-").length > 6) {
            return false;
        } else if (!Character.isLetter(s.charAt(0))) {
            return false;
        } else {
            return s.matches("^[A-Za-z-]{2,35}$");
        }
    }

    // Objects

    private final @NotNull String string;

    public HttpName(@NotNull String string) {
        if (!validate(string)) {
            throw new IllegalArgumentException("The string '" + string + "' is not a valid http name");
        }
        this.string = string.toLowerCase();
    }

    @Override
    public String toString() {
        return string;
    }

    @Override
    public boolean equals(@Nullable Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        @NotNull HttpName httpName = (HttpName) object;
        return Objects.equals(string, httpName.string);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(string);
    }
}
