package deque;

public class LinkedListDeque<Generic> {

    public class IntNode {

        private IntNode prev;
        private Generic first;
        private IntNode next;

        public IntNode(IntNode prev, Generic first, IntNode next) {
            this.prev = prev;
            this.first = first;
            this.next = next;
        }

    }


    private IntNode sentinel;
    private int size;
    private IntNode last;

    public LinkedListDeque() {
        sentinel = new IntNode(null, null, null);
        last = null;
        this.size = 0;
    }

    public LinkedListDeque(LinkedListDeque other) {
        sentinel = new IntNode(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = other.size;
        int i = 0;
        while (i < other.size) {
            addFirst((Generic) other.get(i));
            i++;
        }
    }

    public void addFirst(Generic x) {
        if (sentinel.next == null) {
            sentinel.next = new IntNode(sentinel, x, sentinel);
            last = sentinel.next;
            sentinel.prev = last;

        } else {
            sentinel.prev = null;
            sentinel.next = new IntNode(sentinel, x, sentinel.next);
            sentinel.next.next.prev = sentinel.next;
            sentinel.prev = last;
        }
        this.size++;
    }

    public void addLast(Generic x) {
        if (sentinel.next == null) {
            sentinel = new IntNode(null, null, null);
            addFirst(x);
        } else {
            sentinel.prev.next = new IntNode(sentinel.prev, x, sentinel);
            sentinel.prev = sentinel.prev.next;
            last = sentinel.prev;
            this.size++;
        }
    }

    public boolean isEmpty() {
        return (this.size == 0);
    }

    public int size() {
        return this.size;
    }


    public void printDeque() {
        last = sentinel.next;
        while (sentinel.next != sentinel) {
            System.out.print(sentinel.next.first + " ");
            sentinel.next = sentinel.next.next;
        }
        sentinel.next = last;
        last = sentinel.prev;
        System.out.println();
    }


    public Generic removeFirst() {
        if (isEmpty()) {
            sentinel = null;
            last = null;
            return null;
        }
        Generic store = sentinel.next.first;

        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        this.size--;

        return store;
    }

    public Generic removeLast() {
        if (isEmpty()) {
            sentinel = null;
            last = null;
            return null;
        }

        Generic store = sentinel.prev.first;

        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        last = sentinel.prev;
        this.size--;
        return store;
    }

    public Generic get(int index) {
        int i = 0;
        last = sentinel.next;
        while (i < this.size && i >= 0) {
            if (i == index) {
                Generic store = sentinel.next.first;
                sentinel.next = last;
                last = sentinel.prev;
                return store;
            }
            sentinel.next = sentinel.next.next;
            i++;
        }
        sentinel.next = last;
        last = sentinel.prev;

        return null;
    }

    public Generic getRecursive(int index) {
        last = last.next;

        if (index == 0) {
            Generic store = last.next.first;
            last = sentinel.prev;
            return store;
        }
        // sentinel.next = sentinel.next.next;


        return getRecursive(--index);
    }

    public static void main(String[] args) {
        /*LinkedListDeque<Integer> list = new LinkedListDeque<>();
        list.addFirst(20);
        list.addFirst(15);
        list.addFirst(13);
        list.addFirst(10);
        list.addFirst(7);
        list.addFirst(5);
        list.addLast(23);
        list.addLast(25);
        list.addLast(30);
        list.addFirst(3);
        System.out.println(list.isEmpty());
        System.out.println(list.size());
        list.printDeque();
        System.out.println(list.removeFirst());
        System.out.println(list.removeLast());
        list.printDeque();
        System.out.println(list.get(4));
        System.out.println(list.getRecursive(4));
        System.out.println(list.get(4));
        list.printDeque();

        LinkedListDeque<Integer> listCopy = new LinkedListDeque<>(list);
        System.out.println("Printing New Copy!");
        list.printDeque();*/
        /*LinkedListDeque<Integer> list = new LinkedListDeque<>();
        list.addLast(10);
        list.addLast(12);
        list.addLast(15);*/

        LinkedListDeque<Integer> lld1 = new LinkedListDeque<>();
        lld1.addFirst(3);

        lld1.removeLast();
        lld1.removeFirst();
        lld1.removeLast();
        lld1.removeFirst();
        System.out.println(lld1.size());
    }
}