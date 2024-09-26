package ghostface.dev.message;

public enum Target {

    REQUEST(true, false),
    RESPONSE(false, true),
    BOTH(true, true);

    private final boolean request;
    private final boolean response;

    Target(boolean request, boolean response) {
        this.request = request;
        this.response = response;
    };

    public boolean isRequest() {
        return request;
    }

    public boolean isResponse() {
        return response;
    }

    public boolean isBoth() {
        return request && response;
    }
}
