package ghostface.dev.http.headers;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;

public final class HttpHeaderName {

    public static final @NotNull HttpHeaderName ACCEPT = new HttpHeaderName("accept", Target.REQUEST);
    public static final @NotNull HttpHeaderName AGE = new HttpHeaderName("age", Target.RESPONSE);
    public static final @NotNull HttpHeaderName ALLOW = new HttpHeaderName("allow", Target.RESPONSE);
    public static final @NotNull HttpHeaderName AUTHORIZATION = new HttpHeaderName("authorization", Target.REQUEST);
    public static final @NotNull HttpHeaderName CONNECTION = new HttpHeaderName("connection", Target.REQUEST);
    public static final @NotNull HttpHeaderName COOKIE = new HttpHeaderName("cookie", Target.REQUEST);
    public static final @NotNull HttpHeaderName DATE = new HttpHeaderName("date", Target.BOTH);
    public static final @NotNull HttpHeaderName ETAG = new HttpHeaderName("etag", Target.RESPONSE);
    public static final @NotNull HttpHeaderName EXPECT = new HttpHeaderName("expect", Target.REQUEST);
    public static final @NotNull HttpHeaderName EXPIRES = new HttpHeaderName("expires", Target.RESPONSE);
    public static final @NotNull HttpHeaderName FORWARDED = new HttpHeaderName("forwarded", Target.REQUEST);
    public static final @NotNull HttpHeaderName FROM = new HttpHeaderName("from", Target.REQUEST);
    public static final @NotNull HttpHeaderName HOST = new HttpHeaderName("host", Target.REQUEST);
    public static final @NotNull HttpHeaderName LINK = new HttpHeaderName("link", Target.RESPONSE);
    public static final @NotNull HttpHeaderName LOCATION = new HttpHeaderName("location", Target.RESPONSE);
    public static final @NotNull HttpHeaderName ORIGIN = new HttpHeaderName("origin", Target.REQUEST);
    public static final @NotNull HttpHeaderName RANGE = new HttpHeaderName("range", Target.REQUEST);
    public static final @NotNull HttpHeaderName REFERER = new HttpHeaderName("referer", Target.REQUEST);
    public static final @NotNull HttpHeaderName REFRESH = new HttpHeaderName("refresh", Target.RESPONSE);
    public static final @NotNull HttpHeaderName SERVER = new HttpHeaderName("server", Target.RESPONSE);
    public static final @NotNull HttpHeaderName TE = new HttpHeaderName("te", Target.REQUEST);
    public static final @NotNull HttpHeaderName TRAILER = new HttpHeaderName("trailer", Target.RESPONSE);
    public static final @NotNull HttpHeaderName UPGRADE = new HttpHeaderName("upgrade", Target.BOTH);
    public static final @NotNull HttpHeaderName VARY = new HttpHeaderName("vary", Target.RESPONSE);
    public static final @NotNull HttpHeaderName VIA = new HttpHeaderName("via", Target.BOTH);
    public static final @NotNull HttpHeaderName PRIORITY = new HttpHeaderName("priority", Target.REQUEST);

    public static final @NotNull HttpHeaderName ACCEPT_CH = new HttpHeaderName("accept-ch", Target.REQUEST);
    public static final @NotNull HttpHeaderName ACCEPT_CHARSET = new HttpHeaderName("accept-charset", Target.REQUEST);
    public static final @NotNull HttpHeaderName ACCEPT_ENCODING = new HttpHeaderName("accept-encoding", Target.REQUEST);
    public static final @NotNull HttpHeaderName ACCEPT_LANGUAGE = new HttpHeaderName("accept-language", Target.REQUEST);
    public static final @NotNull HttpHeaderName ACCEPT_PATCH = new HttpHeaderName("accept-patch", Target.RESPONSE);
    public static final @NotNull HttpHeaderName ACCEPT_POST = new HttpHeaderName("accept-post", Target.RESPONSE);
    public static final @NotNull HttpHeaderName ACCEPT_RANGES = new HttpHeaderName("accept-ranges", Target.RESPONSE);
    public static final @NotNull HttpHeaderName ACCESS_CONTROL_ALLOW_CREDENTIALS = new HttpHeaderName("access-control-allow-credentials", Target.RESPONSE);
    public static final @NotNull HttpHeaderName ACCESS_CONTROL_ALLOW_HEADERS = new HttpHeaderName("access-control-allow-headers", Target.RESPONSE);
    public static final @NotNull HttpHeaderName ACCESS_CONTROL_ALLOW_METHODS = new HttpHeaderName("access-control-allow-methods", Target.RESPONSE);
    public static final @NotNull HttpHeaderName ACCESS_CONTROL_ALLOW_ORIGIN = new HttpHeaderName("access-control-allow-origin", Target.RESPONSE);
    public static final @NotNull HttpHeaderName ACCESS_CONTROL_EXPOSE_HEADERS = new HttpHeaderName("access-control-expose-headers", Target.RESPONSE);
    public static final @NotNull HttpHeaderName ACCESS_CONTROL_MAX_AGE = new HttpHeaderName("access-control-max-age", Target.RESPONSE);
    public static final @NotNull HttpHeaderName ACCESS_CONTROL_REQUEST_HEADERS = new HttpHeaderName("access-control-request-headers", Target.REQUEST);
    public static final @NotNull HttpHeaderName ACCESS_CONTROL_REQUEST_METHOD = new HttpHeaderName("access-control-request-method", Target.REQUEST);
    public static final @NotNull HttpHeaderName ALT_SVC = new HttpHeaderName("alt-svc", Target.RESPONSE);
    public static final @NotNull HttpHeaderName ALT_USED = new HttpHeaderName("alt-used", Target.REQUEST);
    public static final @NotNull HttpHeaderName CACHE_CONTROL = new HttpHeaderName("cache-control", Target.BOTH);
    public static final @NotNull HttpHeaderName CLEAR_SITE_DATA = new HttpHeaderName("clear-site-data", Target.RESPONSE);
    public static final @NotNull HttpHeaderName CONTENT_DISPOSITION = new HttpHeaderName("content-disposition", Target.RESPONSE);
    public static final @NotNull HttpHeaderName CONTENT_ENCODING = new HttpHeaderName("content-encoding", Target.RESPONSE);
    public static final @NotNull HttpHeaderName CONTENT_LANGUAGE = new HttpHeaderName("content-language", Target.RESPONSE);
    public static final @NotNull HttpHeaderName CONTENT_LENGTH = new HttpHeaderName("content-length", Target.BOTH);
    public static final @NotNull HttpHeaderName CONTENT_LOCATION = new HttpHeaderName("content-location", Target.RESPONSE);
    public static final @NotNull HttpHeaderName CONTENT_RANGE = new HttpHeaderName("content-range", Target.RESPONSE);
    public static final @NotNull HttpHeaderName CONTENT_SECURITY_POLICY = new HttpHeaderName("content-security-policy", Target.RESPONSE);
    public static final @NotNull HttpHeaderName CONTENT_SECURITY_POLICY_REPORT_ONLY = new HttpHeaderName("content-security-policy-report-only", Target.RESPONSE);
    public static final @NotNull HttpHeaderName CONTENT_TYPE = new HttpHeaderName("content-type", Target.BOTH);
    public static final @NotNull HttpHeaderName CROSS_ORIGIN_EMBEDDER_POLICY = new HttpHeaderName("cross-origin-embedder-policy", Target.RESPONSE);
    public static final @NotNull HttpHeaderName CROSS_ORIGIN_OPENER_POLICY = new HttpHeaderName("cross-origin-opener-policy", Target.RESPONSE);
    public static final @NotNull HttpHeaderName CROSS_ORIGIN_RESOURCE_POLICY = new HttpHeaderName("cross-origin-resource-policy", Target.RESPONSE);
    public static final @NotNull HttpHeaderName DEVICE_MEMORY = new HttpHeaderName("device-memory", Target.REQUEST);
    public static final @NotNull HttpHeaderName IF_MATCH = new HttpHeaderName("if-match", Target.REQUEST);
    public static final @NotNull HttpHeaderName IF_MODIFIED_SINCE = new HttpHeaderName("if-modified-since", Target.REQUEST);
    public static final @NotNull HttpHeaderName IF_NONE_MATCH = new HttpHeaderName("if-none-match", Target.REQUEST);
    public static final @NotNull HttpHeaderName IF_RANGE = new HttpHeaderName("if-range", Target.REQUEST);
    public static final @NotNull HttpHeaderName IF_UNMODIFIED_SINCE = new HttpHeaderName("if-unmodified-since", Target.REQUEST);
    public static final @NotNull HttpHeaderName KEEP_ALIVE = new HttpHeaderName("keep-alive", Target.BOTH);
    public static final @NotNull HttpHeaderName LAST_MODIFIED = new HttpHeaderName("last-modified", Target.RESPONSE);
    public static final @NotNull HttpHeaderName MAX_FORWARDS = new HttpHeaderName("max-forwards", Target.REQUEST);
    public static final @NotNull HttpHeaderName PERMISSIONS_POLICY = new HttpHeaderName("permissions-policy", Target.RESPONSE);
    public static final @NotNull HttpHeaderName PROXY_AUTHENTICATE = new HttpHeaderName("proxy-authenticate", Target.RESPONSE);
    public static final @NotNull HttpHeaderName PROXY_AUTHORIZATION = new HttpHeaderName("proxy-authorization", Target.REQUEST);
    public static final @NotNull HttpHeaderName REFERRER_POLICY = new HttpHeaderName("referrer-policy", Target.RESPONSE);
    public static final @NotNull HttpHeaderName RETRY_AFTER = new HttpHeaderName("retry-after", Target.RESPONSE);
    public static final @NotNull HttpHeaderName SEC_FETCH_DEST = new HttpHeaderName("sec-fetch-dest", Target.REQUEST);
    public static final @NotNull HttpHeaderName SEC_FETCH_MODE = new HttpHeaderName("sec-fetch-mode", Target.REQUEST);
    public static final @NotNull HttpHeaderName SEC_FETCH_SITE = new HttpHeaderName("sec-fetch-site", Target.REQUEST);
    public static final @NotNull HttpHeaderName SEC_FETCH_USER = new HttpHeaderName("sec-fetch-user", Target.REQUEST);
    public static final @NotNull HttpHeaderName SEC_PURPOSE = new HttpHeaderName("sec-purpose", Target.REQUEST);
    public static final @NotNull HttpHeaderName SEC_WEBSOCKET_ACCEPT = new HttpHeaderName("sec-websocket-accept", Target.RESPONSE);
    public static final @NotNull HttpHeaderName SEC_WEBSOCKET_EXTENSIONS = new HttpHeaderName("sec-websocket-extensions", Target.REQUEST);
    public static final @NotNull HttpHeaderName SEC_WEBSOCKET_KEY = new HttpHeaderName("sec-websocket-key", Target.REQUEST);
    public static final @NotNull HttpHeaderName SEC_WEBSOCKET_PROTOCOL = new HttpHeaderName("sec-websocket-protocol", Target.BOTH);
    public static final @NotNull HttpHeaderName SEC_WEBSOCKET_VERSION = new HttpHeaderName("sec-websocket-version", Target.BOTH);
    public static final @NotNull HttpHeaderName SERVER_TIMING = new HttpHeaderName("server-timing", Target.RESPONSE);
    public static final @NotNull HttpHeaderName SERVICE_WORKER_NAVIGATION_PRELOAD = new HttpHeaderName("service-worker-navigation-preload", Target.RESPONSE);
    public static final @NotNull HttpHeaderName SET_COOKIE = new HttpHeaderName("set-cookie", Target.RESPONSE);
    public static final @NotNull HttpHeaderName STRICT_TRANSPORT_SECURITY = new HttpHeaderName("strict-transport-security", Target.RESPONSE);
    public static final @NotNull HttpHeaderName TIMING_ALLOW_ORIGIN = new HttpHeaderName("timing-allow-origin", Target.RESPONSE);
    public static final @NotNull HttpHeaderName TRANSFER_ENCODING = new HttpHeaderName("transfer-encoding", Target.RESPONSE);
    public static final @NotNull HttpHeaderName UPGRADE_INSECURE_REQUESTS = new HttpHeaderName("upgrade-insecure-requests", Target.REQUEST);
    public static final @NotNull HttpHeaderName USER_AGENT = new HttpHeaderName("user-agent", Target.REQUEST);
    public static final @NotNull HttpHeaderName WWW_AUTHENTICATE = new HttpHeaderName("www-authenticate", Target.RESPONSE);
    public static final @NotNull HttpHeaderName X_CONTENT_TYPE_OPTIONS = new HttpHeaderName("x-content-type-options", Target.RESPONSE);
    public static final @NotNull HttpHeaderName X_FRAME_OPTIONS = new HttpHeaderName("x-frame-options", Target.RESPONSE);


    private static final @NotNull Set<@NotNull HttpHeaderName> collection = new LinkedHashSet<>();

    public static boolean add(@NotNull HttpHeaderName name) {
        return collection.add(name);
    }

    public static boolean remove(@NotNull HttpHeaderName name) {
        return collection.remove(name);
    }

    public static boolean contains(@NotNull HttpHeaderName name) {
        return collection.contains(name);
    }

    public static @NotNull Collection<@NotNull HttpHeaderName> collection() {
        return Collections.unmodifiableSet(collection);
    }

    // Static initializer

    static {
        for (@NotNull Field field : HttpHeaderName.class.getDeclaredFields()) {
            if (Modifier.isPublic(field.getModifiers()) && Modifier.isStatic(field.getModifiers())) {
                try {
                    @NotNull HttpHeaderName name = (HttpHeaderName) field.get(HttpHeaderName.class);
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
    private final @NotNull Target target;

    public HttpHeaderName(@NotNull String name, @NotNull Target target) {
        if (!validate(name)) {
            throw new IllegalArgumentException("The name '" + name + "' is not a valid http name");
        }
        this.name = name.toLowerCase();
        this.target = target;
    }

    public @NotNull String name() {
        return name;
    }

    public @NotNull Target target() {
        return target;
    }

    @Override
    public boolean equals(@Nullable Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        @NotNull HttpHeaderName httpName = (HttpHeaderName) object;
        return Objects.equals(name, httpName.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
