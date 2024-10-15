package ghostface.dev.http.utils;

import ghostface.dev.http.HttpMethod;
import ghostface.dev.http.body.HttpBody;
import ghostface.dev.http.element.HttpRequest;
import ghostface.dev.http.headers.HttpHeaders;
import org.jetbrains.annotations.NotNull;

import java.net.URI;

public interface RequestUtils {

    @NotNull HttpMethod getMethod(@NotNull HttpRequest request);

    @NotNull URI getURI(@NotNull HttpRequest request);

    @NotNull HttpHeaders of(@NotNull HttpRequest request);

    @NotNull HttpBody getBody(@NotNull HttpRequest request);

}
