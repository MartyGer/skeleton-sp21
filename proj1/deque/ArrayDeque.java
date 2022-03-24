package deque;

public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private double rFactor;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        rFactor = 0;
    }

    public double rFactorCalc() {
        rFactor = (double) size / items.length;
        return rFactor;
    }

    public void addFirst(T item) {
        if (size == items.length) {
            sizeIncreaseFirst();
        }
        items[0] = item;
    }

    public void addLast(T item) {

        if (size == items.length) {
            sizeIncreaseLast();
        }
        items[size] = item;
        size++;
    }

    public T[] sizeIncreaseLast() {

        double newLengthDouble = items.length * 0.75;
        int newLengthInt = (int) newLengthDouble + items.length;
        System.out.println("New Int Length: " + newLengthInt);

        T[] flag = (T[]) new Object[newLengthInt];
        System.arraycopy(items, 0, flag, 0, size);
        items = flag;

        return items;
    }

    public T[] sizeIncreaseFirst() {
        double newLengthDouble = items.length * 0.75;
        int newLengthInt = (int) newLengthDouble + items.length;
        T[] flag = (T[]) new Object[newLengthInt];
        System.arraycopy(items, 0, flag, 1, size);
        items = flag;
        return items;
    }

    public T[] sizeDecreaseFirst() {

        double newLengthDouble = 0;
        if (rFactorCalc() < 0.25) {
            newLengthDouble = items.length * 0.75;
        }
        int newLengthInt = items.length - (int) newLengthDouble;
        T[] flag = (T[]) new Object[newLengthInt];
        System.arraycopy(items, 1, flag, 0, size);
        items = flag;

        return items;
    }

    public T[] sizeDecreaseLast() {

        double newLengthDouble = 0;
        if (rFactorCalc() < 0.25) {
            newLengthDouble = items.length * 0.75;
        }
        int newLengthInt = items.length - (int) newLengthDouble;
        T[] flag = (T[]) new Object[newLengthInt];
        System.arraycopy(items, 0, flag, 0, size - 1);
        items = flag;

        return items;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        int i = 0;
        while (i < size) {
            System.out.print(items[i] + " ");
            i++;
        }
    }

    public T removeFirst() {

        if (size == 0)
        {
            return null;
        }
        T store = items[0];
        sizeDecreaseFirst();
        size--;
        return store;
    }

    public T removeLast() {
        if (size == 0)
        {
            return null;
        }
        T store = items[size - 1];
        sizeDecreaseLast();
        size--;
        return store;

    }

    public T get(int index) {
        return items[index];
    }

    public static void main(String[] args) {

        long start = System.currentTimeMillis();
        ArrayDeque aList = new ArrayDeque();
        aList.addLast(10);
        aList.addLast(15);
        aList.addLast(10);
        aList.addLast(15);
        aList.addLast(10);
        aList.addLast(15);
        aList.addLast(10);
        aList.addLast(15);
        aList.addLast(10);
        aList.addLast(15);
        aList.addLast(10);
        aList.addLast(10);
        aList.addLast(15);
        aList.addLast(10);
        aList.addLast(15);
        aList.addLast(10);
        aList.addLast(15);
        aList.addLast(10);
        aList.addLast(15);
        aList.addLast(10);
        aList.addLast(15);
        aList.addLast(10);
        System.out.println("\nRFACTOR: " + aList.rFactorCalc());
        aList.addLast(10);
        aList.addLast(15);
        aList.addLast(10);
        aList.addLast(15);
        aList.addLast(10);
        aList.addLast(15);
        aList.addLast(10);
        aList.addLast(15);
        System.out.println("\nRFACTOR: " + aList.rFactorCalc());
        System.out.println(aList.items.length);
        System.out.println(aList.size());
        aList.addLast(10);
        aList.addLast(15);
        aList.addLast(10);
        aList.addLast(10);

        aList.removeFirst();
        aList.removeFirst();
        aList.removeFirst();
        aList.removeFirst();
        aList.removeFirst();
        aList.removeFirst();
        aList.removeFirst();
        aList.removeFirst();
        aList.removeFirst();
        aList.removeFirst();
        aList.removeFirst();
        aList.removeFirst();
        aList.removeFirst();
        aList.removeFirst();
        aList.removeFirst();
        aList.removeFirst();
        aList.removeFirst();
        aList.removeFirst();
        aList.removeFirst();
        aList.removeFirst();
        aList.removeFirst();
        aList.removeFirst();
        aList.removeFirst();
        aList.removeFirst();
        aList.removeFirst();
        aList.removeFirst();
        aList.removeLast();


        aList.printDeque();
        System.out.println("\nRFACTOR: " + aList.rFactorCalc());
        System.out.println(aList.items.length);
        System.out.println(aList.size());
        long end = System.currentTimeMillis();
        System.out.println("\n Time taken: " + (end - start) + " ms");
    }

}
