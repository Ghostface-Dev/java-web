package ghostface.dev.message;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public final class Status {

    // Providers

    public static final @NotNull Status CONTINUE = new Status(100, "Continue");
    public static final @NotNull Status SWITCHING_PROTOCOLS = new Status(101, "Switching Protocols");
    public static final @NotNull Status PROCESSING = new Status(102, "Processing");
    public static final @NotNull Status EARLY_HINTS = new Status(103, "Early Hints");
    public static final @NotNull Status OK = new Status(200, "OK");
    public static final @NotNull Status CREATED = new Status(201, "Created");
    public static final @NotNull Status ACCEPTED = new Status(202, "Accepted");
    public static final @NotNull Status NON_AUTHORITATIVE_INFORMATION = new Status(203, "Non-Authoritative Information");
    public static final @NotNull Status NO_CONTENT = new Status(204, "No Content");
    public static final @NotNull Status RESET_CONTENT = new Status(205, "Reset Content");
    public static final @NotNull Status PARTIAL_CONTENT = new Status(206, "Partial Content");
    public static final @NotNull Status MULTI_STATUS = new Status(207, "Multi-Status");
    public static final @NotNull Status ALREADY_REPORTED = new Status(208, "Already Reported");
    public static final @NotNull Status IM_USED = new Status(226, "IM Used");
    public static final @NotNull Status MULTIPLE_CHOICES = new Status(300, "Multiple Choices");
    public static final @NotNull Status MOVED_PERMANENTLY = new Status(301, "Moved Permanently");
    public static final @NotNull Status FOUND = new Status(302, "Found");
    public static final @NotNull Status SEE_OTHER = new Status(303, "See Other");
    public static final @NotNull Status NOT_MODIFIED = new Status(304, "Not Modified");
    public static final @NotNull Status TEMPORARY_REDIRECT = new Status(307, "Temporary Redirect");
    public static final @NotNull Status PERMANENT_REDIRECT = new Status(308, "Permanent Redirect");
    public static final @NotNull Status BAD_REQUEST = new Status(400, "Bad Request");
    public static final @NotNull Status UNAUTHORIZED = new Status(401, "Unauthorized");
    public static final @NotNull Status PAYMENT_REQUIRED = new Status(402, "Payment Required");
    public static final @NotNull Status FORBIDDEN = new Status(403, "Forbidden");
    public static final @NotNull Status NOT_FOUND = new Status(404, "Not Found");
    public static final @NotNull Status METHOD_NOT_ALLOWED = new Status(405, "Method Not Allowed");
    public static final @NotNull Status NOT_ACCEPTABLE = new Status(406, "Not Acceptable");
    public static final @NotNull Status PROXY_AUTHENTICATION_REQUIRED = new Status(407, "Proxy Authentication Required");
    public static final @NotNull Status REQUEST_TIMEOUT = new Status(408, "Request Timeout");
    public static final @NotNull Status CONFLICT = new Status(409, "Conflict");
    public static final @NotNull Status GONE = new Status(410, "Gone");
    public static final @NotNull Status LENGTH_REQUIRED = new Status(411, "Length Required");
    public static final @NotNull Status PRECONDITION_FAILED = new Status(412, "Precondition Failed");
    public static final @NotNull Status CONTENT_TOO_LARGE = new Status(413, "Content Too Large");
    public static final @NotNull Status URI_TOO_LONG = new Status(414, "URI Too Long");
    public static final @NotNull Status UNSUPPORTED_MEDIA_TYPE = new Status(415, "Unsupported Media Type");
    public static final @NotNull Status RANGE_NOT_SATISFIABLE = new Status(416, "Range Not Satisfiable");
    public static final @NotNull Status IM_A_TEAPOT = new Status(418, "I'm a teapot");
    public static final @NotNull Status UNPROCESSABLE_CONTENT = new Status(422, "Unprocessable Content");
    public static final @NotNull Status LOCKED = new Status(423, "Locked");
    public static final @NotNull Status FAILED_DEPENDENCY = new Status(424, "Failed Dependency");
    public static final @NotNull Status TOO_EARLY = new Status(425, "Too Early");
    public static final @NotNull Status INTERNAL_SERVER_ERROR = new Status(500, "Internal Server Error");
    public static final @NotNull Status NOT_IMPLEMENTED = new Status(501, "Not Implemented");
    public static final @NotNull Status BAD_GATEWAY = new Status(502, "Bad Gateway");
    public static final @NotNull Status SERVICE_UNAVAILABLE = new Status(503, "Service Unavailable");
    public static final @NotNull Status GATEWAY_TIMEOUT = new Status(504, "Gateway Timeout");
    public static final @NotNull Status HTTP_VERSION_NOT_SUPPORTED = new Status(505, "HTTP Version Not Supported");
    public static final @NotNull Status VARIANT_ALSO_NEGOTIATES = new Status(506, "Variant Also Negotiates");
    public static final @NotNull Status INSUFFICIENT_STORAGE = new Status(507, "Insufficient Storage");
    public static final @NotNull Status LOOP_DETECTED = new Status(508, "Loop Detected");
    public static final @NotNull Status NOT_EXTENDED = new Status(510, "Not Extended");
    public static final @NotNull Status NETWORK_AUTHENTICATION_REQUIRED = new Status(511, "Network Authentication Required");
    public static final @NotNull Status EXPECTATION_FAILED = new Status(417, "Expectation Failed");
    public static final @NotNull Status UPGRADE_REQUIRED = new Status(426, "Upgrade Required");
    public static final @NotNull Status PRECONDITION_REQUIRED = new Status(428, "Precondition Required");
    public static final @NotNull Status TOO_MANY_REQUESTS = new Status(429, "Too Many Requests");
    public static final @NotNull Status REQUEST_HEADER_FIELDS_TOO_LARGE = new Status(431, "Request Header Fields Too Large");
    public static final @NotNull Status MISDIRECTED_REQUEST = new Status(421, "Misdirected Request");
    public static final @NotNull Status UNAVAILABLE_FOR_LEGAL_REASONS = new Status(451, "Unavailable For Legal Reasons");

    // Objects

    private final int code;
    private final @NotNull String message;

    public Status(int code, @NotNull String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public @NotNull String getMessage() {
        return message;
    }
}
