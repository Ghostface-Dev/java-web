package ghostface.dev.http.headers;

import ghostface.dev.http.media.MediaType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Stream;

public final class HttpHeaders implements Collection<@NotNull HttpHeader<?>> {

    private final @NotNull Set<@NotNull HttpHeader<?>> headers = new LinkedHashSet<>();
    private final @NotNull Target target;

    public HttpHeaders(@NotNull Target target, @NotNull HttpHeader<?> @NotNull ... headers) {
        if (headers.length == 0) {
            throw new IllegalArgumentException("the headers array cannot be null");
        }
        this.target = target;
        this.headers.addAll(Set.of(headers));
    }

    public @NotNull Target getTarget() {
        return target;
    }

    public @NotNull Optional<@NotNull HttpHeader<?>> getHeader(@NotNull HttpHeaderName httpName) {
        return headers.stream().filter(header -> header.getKey().equals(httpName)).findFirst();
    }

    public @NotNull List<@NotNull HttpHeader<?>> getHeaders(@NotNull Target target) {
        return headers.stream().filter(header -> header.getTarget().equals(target)).toList();
    }

    public @NotNull Optional<@NotNull MediaType<?>> getMediaType() {
        @Nullable HttpHeader<?> header = headers.stream().filter(httpHeader -> httpHeader.getValue() instanceof MediaType<?>).findFirst().orElse(null);

        if (header != null) {
            return Optional.of((MediaType<?>) header.getValue());
        } else {
            return Optional.empty();
        }
    }

    @Override
    public int size() {
        return headers.size();
    }

    @Override
    public boolean isEmpty() {
        return headers.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return headers.contains(o);
    }


    @Override
    public @NotNull Iterator<@NotNull HttpHeader<?>> iterator() {
        return headers.iterator();
    }

    @Override
    public @NotNull Object @NotNull [] toArray() {
        return headers.toArray();
    }

    @Override
    public <T> @NotNull T @NotNull [] toArray(@NotNull T[] a) {
        return headers.toArray(a);
    }

    public boolean put(@NotNull HttpHeader<?> header) {
        remove(header);
        return add(header);
    }

    @Override
    public boolean add(@NotNull HttpHeader<?> header) {
        if ((target.isRequest() && !header.getTarget().isRequest()) || (target.isResponse() && !header.getTarget().isResponse())) {
            return false;
        } else {
            return headers.add(header);
        }
    }

    @Override
    public boolean remove(Object o) {
        return headers.remove(o);
    }

    @Override
    public boolean containsAll(@NotNull Collection<?> c) {
        return headers.containsAll(c);
    }

    @Override
    public boolean addAll(@NotNull Collection<? extends @NotNull HttpHeader<?>> c) {
        return headers.addAll(c);
    }

    @Override
    public boolean removeAll(@NotNull Collection<?> c) {
        return headers.removeAll(c);
    }

    @Override
    public boolean removeIf(Predicate<? super @NotNull HttpHeader<?>> filter) {
        return headers.removeIf(filter);
    }

    @Override
    public boolean retainAll(@NotNull Collection<?> c) {
        return headers.retainAll(c);
    }

    @Override
    public void clear() {
        headers.clear();
    }

    @Override
    public Stream<@NotNull HttpHeader<?>> stream() {
        return headers.stream();
    }

}
