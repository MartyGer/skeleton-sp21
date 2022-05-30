package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
    private T[] sentinel;
    private int size;
    private int initialHead = 3;
    private int head = initialHead;
    private int tail = head + 1;
    private int INTITIAL_LENGTH = 8;

    private int end = INTITIAL_LENGTH - 1;
    private int start = 0;

    private double R_FACTOR = 1.25;


    public ArrayDeque() {
        sentinel = (T[]) new Object[INTITIAL_LENGTH];
    }

    private ArrayDeque(T item) {
        sentinel = (T[]) new Object[INTITIAL_LENGTH];
        sentinel[head] = item;
        head--;
        size++;
    }

    @Override
    public void addFirst(T item) {
        if (head == start - 1) {
            double initialTemp = sentinel.length * R_FACTOR;
            T[] temp = (T[]) new Object[(int) initialTemp];
            int mid = ((temp.length - end) / 2);
            System.arraycopy(sentinel, head + 1, temp, mid, size);
            sentinel = temp;
            head = mid - 1;
            tail += mid;
            end = sentinel.length - 1;
            // Start would be unchanged.
        }

        sentinel[head] = item;
        head--;
        size++;
    }

    @Override
    public void addLast(T item) {
        if (tail == end + 1) {
            double initialTemp = (sentinel.length * R_FACTOR) + 1;
            T[] temp = (T[]) new Object[(int) initialTemp];
            int mid = ((temp.length - end) / 2);
            System.arraycopy(sentinel, head + 1, temp, mid, size);
            sentinel = temp;
            tail = mid + size;
            head = mid - 1;


            // Start would remain unchanged.
            end = sentinel.length - 1;
        }

        sentinel[tail] = item;
        tail++;
        size++;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        /*int store = tail;
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
        System.out.println();*/
        for (int i = 0; i < size; i++) {
            System.out.print(get(i) + " ");
        }
        System.out.println();
    }

    @Override
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

    @Override
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

    @Override
    public T get(int index) {
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

        if (head + 1 + index >= sentinel.length) {
            return sentinel[(head + 1 + index) - sentinel.length];
        }
        return sentinel[head + 1 + index];
    }

    public Iterator<T> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<T> {
        int wizPos = 0;

        public boolean hasNext() {
            return wizPos < size;
        }

        public T next() {
            T temp = get(wizPos);
            wizPos++;
            return temp;
        }
    }

    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (!(o instanceof ArrayDeque)) {
            /*LinkedListDeque<T> tempArray = (LinkedListDeque<T>) o;
            if (tempArray.size() != size()) {
                return false;
            }
            for (int i = 0; i < this.size; i++) {
                if (tempArray.get(i) != this.get(i)) {
                    return false;
                }
            }*/
            return false;
        }

        ArrayDeque<T> tempArray = (ArrayDeque<T>) o;
        if (tempArray.size() != size()) {
            return false;
        }
        for (int i = 0; i < this.size; i++) {
            if (tempArray.get(i) != this.get(i)) {
                return false;
            }
        }

        return true;

    }

    /*private static void main(String[] args) {
        ArrayDeque<Integer> aList = new ArrayDeque<>();
        aList.addFirst(2);
        aList.addFirst(3);
        aList.addFirst(4);
        aList.addFirst(5);
        LinkedListDeque<Integer> List = new LinkedListDeque<>();
        List.addFirst(2);
        List.addFirst(3);
        List.addFirst(4);
        List.addFirst(5);

        System.out.println(aList.equals(List));
    }*/
  /*  public static void main(String[] args)
    {
        ArrayDeque<Integer> aList = new ArrayDeque<>();
        aList.addFirst(2);
        aList.addFirst(3);
        aList.addFirst(4);
        aList.addFirst(5);
        aList.addFirst(6);
        aList.addFirst(7);
        aList.addFirst(8);
        aList.addLast(9);
        aList.addLast(10);
        aList.addLast(11);
        aList.addLast(12);
        aList.addLast(13);
        aList.addLast(14);
        aList.addLast(15);
        aList.addLast(16);
        aList.addLast(17);
        aList.addLast(18);
        aList.addLast(19);
        aList.addLast(20);
        aList.addLast(21);
        aList.addLast(22);
        aList.addLast(23);
        aList.addLast(24);
        aList.addLast(25);
        aList.addLast(26);
        aList.addFirst(27);
        aList.addFirst(28);
        aList.addFirst(29);
        aList.addFirst(30);
        aList.addFirst(31);
        aList.addFirst(32);
        aList.addFirst(33);
        aList.addFirst(2);
        aList.addFirst(3);
        aList.addFirst(4);
        aList.addFirst(5);
        aList.addFirst(6);
        aList.addFirst(7);
        aList.addFirst(8);
        aList.addLast(9);
        aList.addLast(10);
        aList.addLast(11);
        aList.addLast(12);
        aList.addLast(13);
        aList.addLast(14);
        aList.addLast(15);
        aList.addLast(16);
        aList.addLast(17);
        aList.addLast(18);
        aList.addLast(19);
        aList.addLast(20);
        aList.addLast(21);
        aList.addLast(22);
        aList.addLast(23);
        aList.addLast(24);
        aList.addLast(25);
        aList.addLast(26);
        aList.addFirst(27);
        aList.addFirst(28);
        aList.addFirst(29);
        aList.addFirst(30);
        aList.addFirst(31);
        aList.addFirst(32);
        aList.addFirst(33);
        System.out.println(aList.get(10));
    }*/

    /* public static void main(String[] args) {
     *//*long start = System.currentTimeMillis();
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
        *//**//*System.out.println(aList.get(2));
        System.out.println(aList.get(23));
        System.out.println(aList.get(31));*//**//*
     *//**//*aList.printDeque();
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
*//**//*
//        //System.out.println("\nRFACTOR: " + aList.rFactorCalc());
//        //System.out.println(aList.items.length);
//        System.out.println(aList.size());

        for (Integer i : aList) {
            System.out.println(i);
        }
        long end = System.currentTimeMillis();
        System.out.println("\n Time taken: " + (end - start) + " ms");


    }*//*
    }*/
}
