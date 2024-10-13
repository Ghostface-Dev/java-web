package ghostface.dev.headers;

import ghostface.dev.message.HttpName;
import ghostface.dev.message.Target;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Stream;

public final class HttpHeaders implements Collection<@NotNull HttpHeader<?>> {

    private final @NotNull List<@NotNull HttpHeader<?>> headers = new LinkedList<>();

    public HttpHeaders(@NotNull HttpHeader<?> @NotNull ... headers) {
        if (headers.length == 0) {
            throw new IllegalArgumentException("the headers array cannot be null");
        }
        this.headers.addAll(List.of(headers));
    }

    public @NotNull Optional<@NotNull HttpHeader<?>> getHeader(@NotNull HttpName httpName) {
        return headers.stream().filter(header -> header.getName().equals(httpName)).findFirst();
    }

    public @NotNull List<@NotNull HttpHeader<?>> getHeaders(@NotNull Target target) {
        return headers.stream().filter(header -> header.getTarget().equals(target)).toList();
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

    @NotNull
    @Override
    public Iterator<@NotNull HttpHeader<?>> iterator() {
        return headers.iterator();
    }

    @NotNull
    @Override
    public Object @NotNull [] toArray() {
        return headers.toArray();
    }

    @NotNull
    @Override
    public <T> T @NotNull [] toArray(@NotNull T[] a) {
        return headers.toArray(a);
    }

    @Override
    public boolean add(@NotNull HttpHeader<?> httpHeader) {
        return headers.add(httpHeader);
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
