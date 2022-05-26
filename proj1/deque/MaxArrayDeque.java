package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {


    private Comparator<T> max;

    public MaxArrayDeque(Comparator<T> c) {
        max = c;
    }

    public T max() {
        return max(max);
    }

    public T max(Comparator<T> c) {
        if (size == 0) {
            return null;
        }

        T store = sentinel[0];
        for (int i = 1; i < size; i++) {
            if (c.compare(store, sentinel[i]) > 0) {
                store = sentinel[i];
            }
        }

        return store;
    }
}
