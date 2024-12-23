public class Pair<T, V> {
    private final T first;

    private final V second;

    private Pair(T first, V second) {
        this.first = first;
        this.second = second;
    }

    public static <T, V> Pair<T, V> of(T first, V second) {
        return new Pair<>(first, second);
    }

    public T getFirst() {
        return first;
    }

    public V getSecond() {
        return second;
    }
}
