package ghostface.dev.message;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;

public final class HttpStatus {

    // Providers

    public static final @NotNull HttpStatus CONTINUE = new HttpStatus(100, "Continue");
    public static final @NotNull HttpStatus SWITCHING_PROTOCOLS = new HttpStatus(101, "Switching Protocols");
    public static final @NotNull HttpStatus PROCESSING = new HttpStatus(102, "Processing");
    public static final @NotNull HttpStatus EARLY_HINTS = new HttpStatus(103, "Early Hints");
    public static final @NotNull HttpStatus OK = new HttpStatus(200, "OK");
    public static final @NotNull HttpStatus CREATED = new HttpStatus(201, "Created");
    public static final @NotNull HttpStatus ACCEPTED = new HttpStatus(202, "Accepted");
    public static final @NotNull HttpStatus NON_AUTHORITATIVE_INFORMATION = new HttpStatus(203, "Non-Authoritative Information");
    public static final @NotNull HttpStatus NO_CONTENT = new HttpStatus(204, "No Content");
    public static final @NotNull HttpStatus RESET_CONTENT = new HttpStatus(205, "Reset Content");
    public static final @NotNull HttpStatus PARTIAL_CONTENT = new HttpStatus(206, "Partial Content");
    public static final @NotNull HttpStatus MULTI_HTTP_STATUS = new HttpStatus(207, "Multi-Status");
    public static final @NotNull HttpStatus ALREADY_REPORTED = new HttpStatus(208, "Already Reported");
    public static final @NotNull HttpStatus IM_USED = new HttpStatus(226, "IM Used");
    public static final @NotNull HttpStatus MULTIPLE_CHOICES = new HttpStatus(300, "Multiple Choices");
    public static final @NotNull HttpStatus MOVED_PERMANENTLY = new HttpStatus(301, "Moved Permanently");
    public static final @NotNull HttpStatus FOUND = new HttpStatus(302, "Found");
    public static final @NotNull HttpStatus SEE_OTHER = new HttpStatus(303, "See Other");
    public static final @NotNull HttpStatus NOT_MODIFIED = new HttpStatus(304, "Not Modified");
    public static final @NotNull HttpStatus TEMPORARY_REDIRECT = new HttpStatus(307, "Temporary Redirect");
    public static final @NotNull HttpStatus PERMANENT_REDIRECT = new HttpStatus(308, "Permanent Redirect");
    public static final @NotNull HttpStatus BAD_REQUEST = new HttpStatus(400, "Bad Request");
    public static final @NotNull HttpStatus UNAUTHORIZED = new HttpStatus(401, "Unauthorized");
    public static final @NotNull HttpStatus PAYMENT_REQUIRED = new HttpStatus(402, "Payment Required");
    public static final @NotNull HttpStatus FORBIDDEN = new HttpStatus(403, "Forbidden");
    public static final @NotNull HttpStatus NOT_FOUND = new HttpStatus(404, "Not Found");
    public static final @NotNull HttpStatus METHOD_NOT_ALLOWED = new HttpStatus(405, "Method Not Allowed");
    public static final @NotNull HttpStatus NOT_ACCEPTABLE = new HttpStatus(406, "Not Acceptable");
    public static final @NotNull HttpStatus PROXY_AUTHENTICATION_REQUIRED = new HttpStatus(407, "Proxy Authentication Required");
    public static final @NotNull HttpStatus REQUEST_TIMEOUT = new HttpStatus(408, "Request Timeout");
    public static final @NotNull HttpStatus CONFLICT = new HttpStatus(409, "Conflict");
    public static final @NotNull HttpStatus GONE = new HttpStatus(410, "Gone");
    public static final @NotNull HttpStatus LENGTH_REQUIRED = new HttpStatus(411, "Length Required");
    public static final @NotNull HttpStatus PRECONDITION_FAILED = new HttpStatus(412, "Precondition Failed");
    public static final @NotNull HttpStatus CONTENT_TOO_LARGE = new HttpStatus(413, "Content Too Large");
    public static final @NotNull HttpStatus URI_TOO_LONG = new HttpStatus(414, "URI Too Long");
    public static final @NotNull HttpStatus UNSUPPORTED_MEDIA_TYPE = new HttpStatus(415, "Unsupported Media Type");
    public static final @NotNull HttpStatus RANGE_NOT_SATISFIABLE = new HttpStatus(416, "Range Not Satisfiable");
    public static final @NotNull HttpStatus IM_A_TEAPOT = new HttpStatus(418, "I'm a teapot");
    public static final @NotNull HttpStatus UNPROCESSABLE_CONTENT = new HttpStatus(422, "Unprocessable Content");
    public static final @NotNull HttpStatus LOCKED = new HttpStatus(423, "Locked");
    public static final @NotNull HttpStatus FAILED_DEPENDENCY = new HttpStatus(424, "Failed Dependency");
    public static final @NotNull HttpStatus TOO_EARLY = new HttpStatus(425, "Too Early");
    public static final @NotNull HttpStatus INTERNAL_SERVER_ERROR = new HttpStatus(500, "Internal Server Error");
    public static final @NotNull HttpStatus NOT_IMPLEMENTED = new HttpStatus(501, "Not Implemented");
    public static final @NotNull HttpStatus BAD_GATEWAY = new HttpStatus(502, "Bad Gateway");
    public static final @NotNull HttpStatus SERVICE_UNAVAILABLE = new HttpStatus(503, "Service Unavailable");
    public static final @NotNull HttpStatus GATEWAY_TIMEOUT = new HttpStatus(504, "Gateway Timeout");
    public static final @NotNull HttpStatus HTTP_VERSION_NOT_SUPPORTED = new HttpStatus(505, "HTTP Version Not Supported");
    public static final @NotNull HttpStatus VARIANT_ALSO_NEGOTIATES = new HttpStatus(506, "Variant Also Negotiates");
    public static final @NotNull HttpStatus INSUFFICIENT_STORAGE = new HttpStatus(507, "Insufficient Storage");
    public static final @NotNull HttpStatus LOOP_DETECTED = new HttpStatus(508, "Loop Detected");
    public static final @NotNull HttpStatus NOT_EXTENDED = new HttpStatus(510, "Not Extended");
    public static final @NotNull HttpStatus NETWORK_AUTHENTICATION_REQUIRED = new HttpStatus(511, "Network Authentication Required");
    public static final @NotNull HttpStatus EXPECTATION_FAILED = new HttpStatus(417, "Expectation Failed");
    public static final @NotNull HttpStatus UPGRADE_REQUIRED = new HttpStatus(426, "Upgrade Required");
    public static final @NotNull HttpStatus PRECONDITION_REQUIRED = new HttpStatus(428, "Precondition Required");
    public static final @NotNull HttpStatus TOO_MANY_REQUESTS = new HttpStatus(429, "Too Many Requests");
    public static final @NotNull HttpStatus REQUEST_HEADER_FIELDS_TOO_LARGE = new HttpStatus(431, "Request Header Fields Too Large");
    public static final @NotNull HttpStatus MISDIRECTED_REQUEST = new HttpStatus(421, "Misdirected Request");
    public static final @NotNull HttpStatus UNAVAILABLE_FOR_LEGAL_REASONS = new HttpStatus(451, "Unavailable For Legal Reasons");

    private static final @NotNull Set<@NotNull HttpStatus> collection = new LinkedHashSet<>();

    public static boolean add(@NotNull HttpStatus httpStatus) {
        return collection.add(httpStatus);
    }

    public static boolean remove(@NotNull HttpStatus httpStatus) {
        return collection.remove(httpStatus);
    }

    public static boolean contains(@NotNull HttpStatus httpStatus) {
        return collection.contains(httpStatus);
    }

    public static @NotNull Collection<@NotNull HttpStatus> collection() {
        return Collections.unmodifiableSet(collection);
    }

    // Static initializer

    static {
        for (@NotNull Field field : HttpStatus.class.getDeclaredFields()) {
            if (Modifier.isPublic(field.getModifiers()) && Modifier.isStatic(field.getModifiers())) {
                try {
                    @NotNull HttpStatus name = (HttpStatus) field.get(HttpStatus.class);
                    collection.add(name);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("Cannot loading http name '" + field.getName() + "'", e) ;
                }
            }
        }
    }

    // Objects

    private final int code;
    private final @NotNull String message;

    @NotNull Optional<@NotNull HttpStatus> getStatus(@NotNull String message) {
        return collection.stream().filter(httpStatus -> httpStatus.getMessage().equalsIgnoreCase(message)).findFirst();
    }

    public HttpStatus(int code, @NotNull String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public @NotNull String getMessage() {
        return message;
    }

    // Natives

    @Override
    public boolean equals(@Nullable Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        @NotNull HttpStatus httpStatus = (HttpStatus) object;
        return code == httpStatus.code && Objects.equals(message, httpStatus.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, message);
    }
}
