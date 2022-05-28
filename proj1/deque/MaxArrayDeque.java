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
        if (size() == 0) {
            return null;
        }

        T store = get(0);
        for (int i = 1; i < size(); i++) {
            if (c.compare(store, get(i)) > 0) {
                store = get(i);
            }
        }

        return store;
    }
}
