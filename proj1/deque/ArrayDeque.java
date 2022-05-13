package deque;

public class ArrayDeque<T> {

    // These will be used for the indexing purposes!
    private int frontIndex;
    private int size;
    private double RFACTOR = 0.25;

    private T[] aList;

    public ArrayDeque() {
        aList = (T[]) new Object[8];
    }

    public ArrayDeque(T item) {
        aList = (T[]) new Object[8];
        aList[size] = item;
        size++;
    }

    public void addFirst(T item) {
       /* if (aList[frontIndex] == null) {

            aList[frontIndex] = item;
            size++;
            return;
        }*/
        if (size == aList.length) {
            double tempSize = (double) size * (1 + RFACTOR);
            T[] temp = (T[]) new Object[(int) tempSize];
            System.arraycopy(aList, 0, temp, 1, size - 1);
            temp[frontIndex] = item;
            aList = temp;
            frontIndex = 0;
            size++;
        }

        System.arraycopy(aList, 0, aList, 1, size);
        aList[frontIndex] = item;
        frontIndex = 0;
        size++;
    }

    public void addLast(T item) {
        if (size == aList.length) {
            double tempSize = (double) size * (1 + RFACTOR);
            T[] temp = (T[]) new Object[(int) tempSize];
            System.arraycopy(aList, 0, temp, 0, size);
            temp[size] = item;
            aList = temp;
            size++;
            return;
        }

        aList[size] = item;
        size++;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return (size() == 0);
    }

    public void printDeque() {
        int i = 0;
        while (i < size) {
            System.out.print(aList[i] + " ");
            i++;
        }
        System.out.println();
    }

    public T removeFirst() {

        if (size == 0) {
            return null;
        } else if ((double) size / aList.length < (1 - RFACTOR)) {
            double tempSize = aList.length * (1 - RFACTOR);
            T temp = aList[0];
            T[] tempaList = (T[]) new Object[(int) tempSize];
            System.arraycopy(aList, 1, tempaList, 0, size);
            aList = tempaList;
            size--;
            return temp;
        }
        T temp = aList[0];
        System.arraycopy(aList, 1, aList, 0, size - 1);
        size--;
        return temp;
    }

    public T removeLast() {

        if (size == 0) {
            return null;
        }
        size--;
        if ((double) size / aList.length < (1 - RFACTOR)) {

            double tempSize = size * (1 + RFACTOR);
            T temp = aList[size];
            T[] tempaList = (T[]) new Object[(int) tempSize];
            System.arraycopy(aList, 0, tempaList, 0, size);
            aList = tempaList;

            return temp;
        }
        T temp = aList[size];
        T[] tempaList = (T[]) new Object[aList.length];
        System.arraycopy(aList, 0, tempaList, 0, size);
        aList = tempaList;

        return temp;
    }

    public T get(int index) {
        return aList[index];
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
        aList.addLast(10);
        aList.addLast(15);
        aList.addLast(10);
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
