package ghostface.dev.http.headers;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;

public final class HttpHeaderKey {

    public static final @NotNull HttpHeaderKey ACCEPT = new HttpHeaderKey("accept");
    public static final @NotNull HttpHeaderKey AGE = new HttpHeaderKey("age");
    public static final @NotNull HttpHeaderKey ALLOW = new HttpHeaderKey("allow");
    public static final @NotNull HttpHeaderKey AUTHORIZATION = new HttpHeaderKey("authorization");
    public static final @NotNull HttpHeaderKey CONNECTION = new HttpHeaderKey("connection");
    public static final @NotNull HttpHeaderKey COOKIE = new HttpHeaderKey("cookie");
    public static final @NotNull HttpHeaderKey DATE = new HttpHeaderKey("date");
    public static final @NotNull HttpHeaderKey ETAG = new HttpHeaderKey("etag");
    public static final @NotNull HttpHeaderKey EXPECT = new HttpHeaderKey("expect");
    public static final @NotNull HttpHeaderKey EXPIRES = new HttpHeaderKey("expires");
    public static final @NotNull HttpHeaderKey FORWARDED = new HttpHeaderKey("forwarded");
    public static final @NotNull HttpHeaderKey FROM = new HttpHeaderKey("from");
    public static final @NotNull HttpHeaderKey HOST = new HttpHeaderKey("host");
    public static final @NotNull HttpHeaderKey LINK = new HttpHeaderKey("link");
    public static final @NotNull HttpHeaderKey LOCATION = new HttpHeaderKey("location");
    public static final @NotNull HttpHeaderKey ORIGIN = new HttpHeaderKey("origin");
    public static final @NotNull HttpHeaderKey RANGE = new HttpHeaderKey("range");
    public static final @NotNull HttpHeaderKey REFERER = new HttpHeaderKey("referer");
    public static final @NotNull HttpHeaderKey REFRESH = new HttpHeaderKey("refresh");
    public static final @NotNull HttpHeaderKey SERVER = new HttpHeaderKey("server");
    public static final @NotNull HttpHeaderKey TE = new HttpHeaderKey("te");
    public static final @NotNull HttpHeaderKey TRAILER = new HttpHeaderKey("trailer");
    public static final @NotNull HttpHeaderKey UPGRADE = new HttpHeaderKey("upgrade");
    public static final @NotNull HttpHeaderKey VARY = new HttpHeaderKey("vary");
    public static final @NotNull HttpHeaderKey VIA = new HttpHeaderKey("via");
    public static final @NotNull HttpHeaderKey PRIORITY = new HttpHeaderKey("priority");

    public static final @NotNull HttpHeaderKey ACCEPT_CH = new HttpHeaderKey("accept-ch");
    public static final @NotNull HttpHeaderKey ACCEPT_CHARSET = new HttpHeaderKey("accept-charset");
    public static final @NotNull HttpHeaderKey ACCEPT_ENCODING = new HttpHeaderKey("accept-encoding");
    public static final @NotNull HttpHeaderKey ACCEPT_LANGUAGE = new HttpHeaderKey("accept-language");
    public static final @NotNull HttpHeaderKey ACCEPT_PATCH = new HttpHeaderKey("accept-patch");
    public static final @NotNull HttpHeaderKey ACCEPT_POST = new HttpHeaderKey("accept-post");
    public static final @NotNull HttpHeaderKey ACCEPT_RANGES = new HttpHeaderKey("accept-ranges");
    public static final @NotNull HttpHeaderKey ACCESS_CONTROL_ALLOW_CREDENTIALS = new HttpHeaderKey("access-control-allow-credentials");
    public static final @NotNull HttpHeaderKey ACCESS_CONTROL_ALLOW_HEADERS = new HttpHeaderKey("access-control-allow-headers");
    public static final @NotNull HttpHeaderKey ACCESS_CONTROL_ALLOW_METHODS = new HttpHeaderKey("access-control-allow-methods");
    public static final @NotNull HttpHeaderKey ACCESS_CONTROL_ALLOW_ORIGIN = new HttpHeaderKey("access-control-allow-origin");
    public static final @NotNull HttpHeaderKey ACCESS_CONTROL_EXPOSE_HEADERS = new HttpHeaderKey("access-control-expose-headers");
    public static final @NotNull HttpHeaderKey ACCESS_CONTROL_MAX_AGE = new HttpHeaderKey("access-control-max-age");
    public static final @NotNull HttpHeaderKey ACCESS_CONTROL_REQUEST_HEADERS = new HttpHeaderKey("access-control-request-headers");
    public static final @NotNull HttpHeaderKey ACCESS_CONTROL_REQUEST_METHOD = new HttpHeaderKey("access-control-request-method");
    public static final @NotNull HttpHeaderKey ALT_SVC = new HttpHeaderKey("alt-svc");
    public static final @NotNull HttpHeaderKey ALT_USED = new HttpHeaderKey("alt-used");
    public static final @NotNull HttpHeaderKey CACHE_CONTROL = new HttpHeaderKey("cache-control");
    public static final @NotNull HttpHeaderKey CLEAR_SITE_DATA = new HttpHeaderKey("clear-site-data");
    public static final @NotNull HttpHeaderKey CONTENT_DISPOSITION = new HttpHeaderKey("content-disposition");
    public static final @NotNull HttpHeaderKey CONTENT_ENCODING = new HttpHeaderKey("content-encoding");
    public static final @NotNull HttpHeaderKey CONTENT_LANGUAGE = new HttpHeaderKey("content-language");
    public static final @NotNull HttpHeaderKey CONTENT_LENGTH = new HttpHeaderKey("content-length");
    public static final @NotNull HttpHeaderKey CONTENT_LOCATION = new HttpHeaderKey("content-location");
    public static final @NotNull HttpHeaderKey CONTENT_RANGE = new HttpHeaderKey("content-range");
    public static final @NotNull HttpHeaderKey CONTENT_SECURITY_POLICY = new HttpHeaderKey("content-security-policy");
    public static final @NotNull HttpHeaderKey CONTENT_SECURITY_POLICY_REPORT_ONLY = new HttpHeaderKey("content-security-policy-report-only");
    public static final @NotNull HttpHeaderKey CONTENT_TYPE = new HttpHeaderKey("content-type");
    public static final @NotNull HttpHeaderKey CROSS_ORIGIN_EMBEDDER_POLICY = new HttpHeaderKey("cross-origin-embedder-policy");
    public static final @NotNull HttpHeaderKey CROSS_ORIGIN_OPENER_POLICY = new HttpHeaderKey("cross-origin-opener-policy");
    public static final @NotNull HttpHeaderKey CROSS_ORIGIN_RESOURCE_POLICY = new HttpHeaderKey("cross-origin-resource-policy");
    public static final @NotNull HttpHeaderKey DEVICE_MEMORY = new HttpHeaderKey("device-memory");
    public static final @NotNull HttpHeaderKey IF_MATCH = new HttpHeaderKey("if-match");
    public static final @NotNull HttpHeaderKey IF_MODIFIED_SINCE = new HttpHeaderKey("if-modified-since");
    public static final @NotNull HttpHeaderKey IF_NONE_MATCH = new HttpHeaderKey("if-none-match");
    public static final @NotNull HttpHeaderKey IF_RANGE = new HttpHeaderKey("if-range");
    public static final @NotNull HttpHeaderKey IF_UNMODIFIED_SINCE = new HttpHeaderKey("if-unmodified-since");
    public static final @NotNull HttpHeaderKey KEEP_ALIVE = new HttpHeaderKey("keep-alive");
    public static final @NotNull HttpHeaderKey LAST_MODIFIED = new HttpHeaderKey("last-modified");
    public static final @NotNull HttpHeaderKey MAX_FORWARDS = new HttpHeaderKey("max-forwards");
    public static final @NotNull HttpHeaderKey PERMISSIONS_POLICY = new HttpHeaderKey("permissions-policy");
    public static final @NotNull HttpHeaderKey PROXY_AUTHENTICATE = new HttpHeaderKey("proxy-authenticate");
    public static final @NotNull HttpHeaderKey PROXY_AUTHORIZATION = new HttpHeaderKey("proxy-authorization");
    public static final @NotNull HttpHeaderKey REFERRER_POLICY = new HttpHeaderKey("referrer-policy");
    public static final @NotNull HttpHeaderKey RETRY_AFTER = new HttpHeaderKey("retry-after");
    public static final @NotNull HttpHeaderKey SEC_FETCH_DEST = new HttpHeaderKey("sec-fetch-dest");
    public static final @NotNull HttpHeaderKey SEC_FETCH_MODE = new HttpHeaderKey("sec-fetch-mode");
    public static final @NotNull HttpHeaderKey SEC_FETCH_SITE = new HttpHeaderKey("sec-fetch-site");
    public static final @NotNull HttpHeaderKey SEC_FETCH_USER = new HttpHeaderKey("sec-fetch-user");
    public static final @NotNull HttpHeaderKey SEC_PURPOSE = new HttpHeaderKey("sec-purpose");
    public static final @NotNull HttpHeaderKey SEC_WEBSOCKET_ACCEPT = new HttpHeaderKey("sec-websocket-accept");
    public static final @NotNull HttpHeaderKey SEC_WEBSOCKET_EXTENSIONS = new HttpHeaderKey("sec-websocket-extensions");
    public static final @NotNull HttpHeaderKey SEC_WEBSOCKET_KEY = new HttpHeaderKey("sec-websocket-key");
    public static final @NotNull HttpHeaderKey SEC_WEBSOCKET_PROTOCOL = new HttpHeaderKey("sec-websocket-protocol");
    public static final @NotNull HttpHeaderKey SEC_WEBSOCKET_VERSION = new HttpHeaderKey("sec-websocket-version");
    public static final @NotNull HttpHeaderKey SERVER_TIMING = new HttpHeaderKey("server-timing");
    public static final @NotNull HttpHeaderKey SERVICE_WORKER_NAVIGATION_PRELOAD = new HttpHeaderKey("service-worker-navigation-preload");
    public static final @NotNull HttpHeaderKey SET_COOKIE = new HttpHeaderKey("set-cookie");
    public static final @NotNull HttpHeaderKey STRICT_TRANSPORT_SECURITY = new HttpHeaderKey("strict-transport-security");
    public static final @NotNull HttpHeaderKey TIMING_ALLOW_ORIGIN = new HttpHeaderKey("timing-allow-origin");
    public static final @NotNull HttpHeaderKey TRANSFER_ENCODING = new HttpHeaderKey("transfer-encoding");
    public static final @NotNull HttpHeaderKey UPGRADE_INSECURE_REQUESTS = new HttpHeaderKey("upgrade-insecure-requests");
    public static final @NotNull HttpHeaderKey USER_AGENT = new HttpHeaderKey("user-agent");
    public static final @NotNull HttpHeaderKey WWW_AUTHENTICATE = new HttpHeaderKey("www-authenticate");
    public static final @NotNull HttpHeaderKey X_CONTENT_TYPE_OPTIONS = new HttpHeaderKey("x-content-type-options");
    public static final @NotNull HttpHeaderKey X_FRAME_OPTIONS = new HttpHeaderKey("x-frame-options");

    private static final @NotNull Set<@NotNull HttpHeaderKey> collection = new LinkedHashSet<>();

    public static boolean add(@NotNull HttpHeaderKey name) {
        return collection.add(name);
    }

    public static boolean remove(@NotNull HttpHeaderKey name) {
        return collection.remove(name);
    }

    public static boolean contains(@NotNull HttpHeaderKey name) {
        return collection.contains(name);
    }

    public static @NotNull Collection<@NotNull HttpHeaderKey> collection() {
        return Collections.unmodifiableSet(collection);
    }

    // Static initializer

    static {
        for (@NotNull Field field : HttpHeaderKey.class.getDeclaredFields()) {
            if (Modifier.isPublic(field.getModifiers()) && Modifier.isStatic(field.getModifiers())) {
                try {
                    @NotNull HttpHeaderKey name = (HttpHeaderKey) field.get(HttpHeaderKey.class);
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

    private final @NotNull String name;

    public HttpHeaderKey(@NotNull String name) {
        if (!validate(name)) {
            throw new IllegalArgumentException("The name '" + name + "' is not a valid http name");
        }
        this.name = name.toLowerCase();
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(@Nullable Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        @NotNull HttpHeaderKey httpName = (HttpHeaderKey) object;
        return Objects.equals(name, httpName.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
