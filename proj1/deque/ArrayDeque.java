package deque;

public class ArrayDeque<T> {

    private T[] sentinel;
    private int size;
    private int head = 3;
    private int tail = head + 1;

    private int INTITIAL_LENGTH = 8;

    private int end = INTITIAL_LENGTH - 1;
    private int start = 0;

    private double R_FACTOR = 1.25;


    public ArrayDeque() {
        sentinel = (T[]) new Object[INTITIAL_LENGTH];
    }

    public ArrayDeque(T item) {
        sentinel = (T[]) new Object[INTITIAL_LENGTH];
        sentinel[head] = item;
        head--;
        size++;
    }

    public void addFirst(T item) {
        // TODO resizing
        if (size == sentinel.length) {
            double initialTemp = sentinel.length * R_FACTOR;
            T[] temp = (T[]) new Object[(int) initialTemp];
            int mid = ((end - start) / 2);
            System.arraycopy(sentinel, start, temp, mid, size);
            sentinel = temp;
            head = mid - 1;
            tail = size + mid;

            // Start would be zero
            end = sentinel.length - 1;

        }

        if (head == start - 1) {
            head = end;
        }
        sentinel[head] = item;
        head--;
        size++;
    }


    public void addLast(T item) {
        // TODO resizing
        if (size == sentinel.length) {
            double initialTemp = sentinel.length * R_FACTOR;
            T[] temp = (T[]) new Object[(int) initialTemp];
            int mid = ((temp.length - end) / 2);
            System.arraycopy(sentinel, start, temp, mid, size);
            sentinel = temp;
            head = mid - 1;
            tail = size + mid;

            // Start would be zero
            end = sentinel.length - 1;

        }

        if (tail == end + 1) {
            tail = start;
        }

        sentinel[tail] = item;
        tail++;
        size++;
    }


    public boolean isEmpty() {
        return size == 0;
    }


    public int size() {
        return size;
    }


    public void printDeque() {
        int store = tail;
        int check = 0;
        if (size == sentinel.length) {
            store = head;
            check = 1;
        }
        for (int i = head + 1; i != store; i++) {

            if (i == end) {
                System.out.print(sentinel[i] + " ");
                i = start;
            }
            if (sentinel[i] == null)
                return;
            if (check == 1) {
                store = head + 1;
                check = 0;
            }
            System.out.print(sentinel[i] + " ");
        }
        System.out.println();
    }


    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T store;
        if (head == end) {
            head = start;
            store = sentinel[head];
            sentinel[head] = null;
            size--;
            return store;
        }
        store = sentinel[head + 1];
        sentinel[head + 1] = null;
        head++;
        size--;
        return store;
    }


    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T store;
        if (tail == start) {
            tail = end;
            store = sentinel[tail];
            sentinel[tail] = null;
            size--;
            return store;
        }
        store = sentinel[tail - 1];
        sentinel[tail - 1] = null;
        tail--;
        size--;
        return store;
    }


    public T get(int index) {
        // We will use recursion
        /*if (index >= sentinel.length) {
            return null;
        }
        int i = 0;
        int headCopy = head + 1;
        while (i < sentinel.length) {
            if (headCopy == size) {
                headCopy = start;
            }
            if (i == index) {
                return sentinel[headCopy];
            }
            i++;
            headCopy++;
        }
        return null;*/

        if (head + 1 + index >= sentinel.length)
        {
            return sentinel[(head + 1 + index) - sentinel.length];
        }
        return sentinel[head + 1 + index];
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        ArrayDeque<Integer> aList = new ArrayDeque<>();

        aList.addLast(10);
        aList.addLast(11);
        aList.addLast(12);
        aList.addLast(13);
        aList.addLast(14);
        aList.addLast(15);
        aList.addLast(16);
        aList.addLast(17);
        aList.addLast(10);
        aList.addLast(11);
        aList.addLast(12);
        aList.addLast(13);
        aList.addLast(14);
        aList.addLast(15);
        aList.addLast(16);
        aList.addLast(17);
        aList.addLast(10);
        aList.addLast(11);
        aList.addLast(12);
        aList.addLast(13);
        aList.addLast(14);
        aList.addLast(15);
        aList.addLast(16);
        aList.addLast(17);
        aList.addLast(10);
        aList.addLast(11);
        aList.addLast(12);
        aList.addLast(10);
        aList.addLast(11);
        aList.addLast(12);
        aList.addLast(13);
        aList.addLast(14);
        aList.addLast(15);
        aList.addLast(16);
        aList.addLast(17);
        aList.addLast(10);
        aList.addLast(11);
        aList.addLast(12);
        aList.addLast(13);
        aList.addLast(14);
        aList.addLast(15);
        aList.addLast(16);
        aList.addLast(17);
        aList.addLast(10);
        aList.addLast(11);
        aList.addLast(12);
        aList.addLast(13);
        aList.addLast(14);
        aList.addLast(15);
        aList.addLast(16);
        aList.addLast(17);
        aList.addLast(10);
        aList.addLast(11);
        aList.addLast(12);
        aList.addLast(10);
        aList.addLast(11);
        aList.addLast(12);
        aList.addLast(12);
        aList.addLast(12);
        aList.addLast(12);
        aList.printDeque();
        System.out.println(aList.get(2));
        System.out.println(aList.get(23));
        System.out.println(aList.get(31));
        /*aList.printDeque();
        aList.removeFirst();
        aList.removeLast();
        aList.removeFirst();
        aList.removeFirst();
        aList.removeLast();
        aList.removeFirst();
        aList.removeFirst();
        aList.printDeque();
        aList.removeLast();
        aList.printDeque();
        aList.addFirst(10);
        aList.addLast(10);
        aList.addLast(15);
        aList.addFirst(10);
        aList.addLast(15);
        aList.addLast(10);
        aList.addLast(15);
        aList.addLast(10);
        aList.addLast(15);

        aList.removeFirst();
        aList.removeLast();
        aList.removeFirst();
        aList.removeFirst();
        aList.removeLast();
        aList.removeFirst();
        aList.removeFirst();
        aList.printDeque();
        aList.removeLast();
        aList.printDeque();
        ArrayDeque<Integer> aList2 = new ArrayDeque<>();
        System.out.println(aList2.isEmpty());
*/
//        //System.out.println("\nRFACTOR: " + aList.rFactorCalc());
//        //System.out.println(aList.items.length);
//        System.out.println(aList.size());
        long end = System.currentTimeMillis();
        System.out.println("\n Time taken: " + (end - start) + " ms");


    }
}
