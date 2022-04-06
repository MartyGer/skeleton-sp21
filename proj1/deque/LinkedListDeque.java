package deque;

public class LinkedListDeque<T> {

    private class Node {
        private Node prev;
        private T first;
        private Node next;

        private Node(Node prev, T first, Node next) {
            this.prev = prev;
            this.first = first;
            this.next = next;
        }
    }

    private Node sentinel;
    private int size;
    private Node pointer;

    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        size = 0;
    }

    public LinkedListDeque(T item) {
        sentinel = new Node(null, null, null);
        sentinel.next = new Node(sentinel, item, sentinel);
        sentinel.prev = sentinel.next;
        size++;
    }

    public void addFirst(T item) {
        if (size == 0) {
            sentinel.next = new Node(sentinel, item, sentinel);
            sentinel.prev = sentinel.next;
            size++;
            return;
        }
        pointer = sentinel.prev;
        sentinel.prev = null;
        sentinel.next = new Node(sentinel, item, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        sentinel.prev = pointer;
        pointer = null;
        size++;

    }

    public void addLast(T item) {
        if (size == 0) {
            addFirst(item);
            return;
        }

        sentinel.prev.next = new Node(sentinel.prev, item, sentinel);
        sentinel.prev = sentinel.prev.next;
        size++;
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        if (size == 0) {
            System.out.println("Null! List is empty.");
            return;
        }
        int i = 0;
        pointer = sentinel.next;
        while (i < size) {
            System.out.print(sentinel.next.first + " ");
            sentinel.next = sentinel.next.next;
            i++;
        }
        sentinel.next = pointer;
        pointer = null;
        System.out.println();
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T store = sentinel.next.first;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size--;
        return store;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T store = sentinel.prev.first;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size--;
        return store;
    }

    public T get(int index) {
        int i = 0;
        pointer = sentinel.next;
        T store = pointer.first;
        while (i < size) {
            if (i == index) {
                store = pointer.first;
                pointer = null;
                return store;
            }
            pointer = pointer.next;
            i++;
        }

        return store;
    }

    public T getRecursive(int index) {

        if (index >= size) {
            return null;
        }
        pointer = sentinel.next;

        return getRecursive(pointer, index - 1);
    }

    private T getRecursive(Node pointerRec, int index) {

        if (index == 0) {
            T returnValue = pointerRec.next.first;
            this.pointer = null;
            return returnValue;
        }
        pointerRec = pointerRec.next;
        return getRecursive(pointerRec, index - 1);
    }

    public static void main(String[] args) {
        LinkedListDeque<Integer> list = new LinkedListDeque<>();
        list.addLast(20);
        list.addFirst(10);
        list.addFirst(15);
        list.addLast(30);
        list.addFirst(40);
        list.addFirst(5);
        list.addFirst(3);
        list.printDeque();
        list.removeFirst();
        list.printDeque();
        list.removeLast();
        list.printDeque();
        System.out.println(list.getRecursive(1));
        System.out.println(list.get(1));

    }
}
