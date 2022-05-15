package deque;

public class ArrayDeque<T> {

    private T[] sentinel;
    private int size;
    private int head = 3;
    private int tail = head + 1;

    private int INTITIAL_LENGTH = 8;

    private int end = INTITIAL_LENGTH - 1;
    private int start = 0;

    private double R_FACTOR = 1.75;


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
            int mid = ((end - start) / 2);
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
        return size > 0;
    }

    
    public int size() {
        return size;
    }

    
    public void printDeque() {
        for (int i = 0; i < sentinel.length; i++) {
            System.out.print(sentinel[i] + " ");
        }
        System.out.println();
    }

    
    public T removeFirst() {
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
        return sentinel[index];
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        ArrayDeque<Integer> aList = new ArrayDeque<>();

        aList.addLast(10);
        aList.addLast(15);
        aList.addLast(10);
        aList.addLast(15);
        aList.addLast(10);
        aList.addLast(15);
        aList.addLast(10);
        aList.addLast(15);
        aList.printDeque();
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


//        //System.out.println("\nRFACTOR: " + aList.rFactorCalc());
//        //System.out.println(aList.items.length);
//        System.out.println(aList.size());
        long end = System.currentTimeMillis();
        System.out.println("\n Time taken: " + (end - start) + " ms");


    }
}
