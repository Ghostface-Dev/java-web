package ghostface.dev.http.element;

import ghostface.dev.http.HttpMethod;

import org.jetbrains.annotations.NotNull;

import java.net.URI;

public interface HttpRequest extends HttpElement {

    @NotNull HttpMethod getMethod();

    @NotNull URI getURI();

    // todo: add URI Authority
}
