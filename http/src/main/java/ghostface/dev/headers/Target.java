package ghostface.dev.headers;

import org.jetbrains.annotations.NotNull;

public enum Target {

    BOTH(true, true),
    REQUEST(true, false),
    RESPONSE(false, true),
    ;

    private final boolean request;
    private final boolean response;

    Target(boolean request, boolean response) {
        this.request = request;
        this.response = response;
    }

    public boolean isRequest() {
        return request;
    }

    public boolean isResponse() {
        return response;
    }
}
