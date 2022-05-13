package deque;

/*public class LinkedListDeque<T> {

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
    }*/

public class LinkedListDeque<T> {

    public class Node{
        public Node prev;
        public T first;
        public Node next;

        public Node(Node prev, T first, Node next)
        {
            this.prev = prev;
            this.first = first;
            this.next = next;
        }
    }

    private Node head;
    private int size;
    private Node tail;

    public LinkedListDeque()
    {
        head = new Node(null, null, null);
        size = 0;
    }

    public LinkedListDeque(T item)
    {
        head = new Node(null, item, null);
        head.next = head;
        head.prev = head;
        tail = head;
        size++;
    }

    public void addFirst(T item)
    {
        if (size == 0)
        {
            head = new Node(head, item, head);
            head.next = head;
            head.prev = head;
            tail = head;
            size++;
            return;
        }
        head = new Node(head, item, head);
        head.prev = tail;
        tail.next = head;
        head.next.prev = head;
        size++;
    }

    public void addLast(T item)
    {
        if (size == 0)
        {
            head = new Node(head, item, head);
            head.next = head;
            head.prev = head;
            tail = head;
            size++;
            return;
        }
        tail.next = new Node(tail, item, head);
        //tail.prev.next = tail;
        tail = tail.next;
        head.prev = tail; // tail
        size++;
    }

    public boolean isEmpty()
    {
        return size == 0;
    }

    public int size()
    {
        return size;
    }



    public void printDeque()
    {
        Node pointer = head;
        for (int i = 0; i < size; i++)
        {
            if (i == size - 1)
            {
                System.out.println(pointer.first);
                return;
            }
            System.out.print(pointer.first + " -> ");
            pointer = pointer.next;
        }
    }


    public T removeFirst()
    {
        if (size == 0)
        {
            return null;
        }

        else if (size == 1)
        {
            T storeFirst = head.first;
            head = null;
            tail = null;
            size--;
            return storeFirst;
        }

        T storeFirst = head.first;
        head = head.next;
        head.prev = tail;
        tail.next = head.next;
        size--;
        return storeFirst;
    }

    public T removeLast()
    {
        if (size == 0)
        {
            return null;
        }

        else if (size == 1)
        {
            T storeLast = tail.first;
            head = null;
            tail = null;
            size--;
            return storeLast;
        }

        T storeLast = tail.first;
        tail = tail.prev;
        head.prev = tail;
        tail.next = head;
        size--;
        return storeLast;
    }
    public T get(int index)
    {
        Node pointer = head;
        for (int i = 0; i < index; i++)
        {
            pointer = pointer.next;
        }

        return pointer.first;
    }

    public T getRecursive(int index)
    {
        Node pointer = head;
        return getRec(pointer, index);
    }

    public T getRec(Node pointer, int index)
    {
        if (index == 0)
        {
            return pointer.first;
        }

        return getRec(pointer.next, index - 1);
    }

    /*public static void main(String[] args) {
        LinkedListDeque<Integer> list = new LinkedListDeque<>();
        list.addLast(12);
        list.addFirst(5);
        list.addFirst(3);
        list.addFirst(1);
        list.addLast(15);
        System.out.println(list.getRecursive(4));
        list.removeLast();
        list.printDeque();
    }
}*/

    public static void main(String[] args) {
        LinkedListDeque<Integer> list = new LinkedListDeque<>();
        System.out.println(list.isEmpty());
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
        list.removeFirst();
        list.removeFirst();
        list.removeFirst();
        list.removeFirst();
        list.removeFirst();
        list.removeFirst();
        list.removeFirst();
        list.addLast(20);
        list.addFirst(10);
        list.addFirst(15);
        list.addLast(30);
        list.addFirst(40);
        list.addFirst(5);
        list.addFirst(3);
        list.printDeque();


    }
}
