package ex1;

/**
 * Minimal positional list with indexOf(p).
 */
 class LinkedPositionalList<E> {

    // ----- nested node -----
    private static class Node<E> implements Position<E> {
        private E element;
        private Node<E> prev;
        private Node<E> next;

        public Node(E e, Node<E> p, Node<E> n) {
            element = e;
            prev = p;
            next = n;
        }

        public E getElement() {
            return element;
        }

        public Node<E> getPrev() {
            return prev;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setPrev(Node<E> p) {
            prev = p;
        }

        public void setNext(Node<E> n) {
            next = n;
        }

        public void setElement(E e) {
            element = e;
        }
    }

    // ----- position interface -----
    public static interface Position<E> {
        E getElement();
    }

    private Node<E> header;
    private Node<E> trailer;
    private int size = 0;

    public LinkedPositionalList() {
        header = new Node<>(null, null, null);
        trailer = new Node<>(null, header, null);
        header.setNext(trailer);
    }

    private Node<E> validate(Position<E> p) {
        if (!(p instanceof Node))
            throw new IllegalArgumentException("invalid position");
        Node<E> node = (Node<E>) p;
        if (node.getNext() == null)
            throw new IllegalArgumentException("position not in list");
        return node;
    }

    private Position<E> makePosition(Node<E> node) {
        if (node == header || node == trailer) return null;
        return node;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Position<E> first() {
        return makePosition(header.getNext());
    }

    public Position<E> last() {
        return makePosition(trailer.getPrev());
    }

    public Position<E> before(Position<E> p) {
        Node<E> node = validate(p);
        return makePosition(node.getPrev());
    }

    public Position<E> after(Position<E> p) {
        Node<E> node = validate(p);
        return makePosition(node.getNext());
    }

    private Position<E> addBetween(E e, Node<E> pred, Node<E> succ) {
        Node<E> newest = new Node<>(e, pred, succ);
        pred.setNext(newest);
        succ.setPrev(newest);
        size++;
        return newest;
    }

    public Position<E> addFirst(E e) {
        return addBetween(e, header, header.getNext());
    }

    public Position<E> addLast(E e) {
        return addBetween(e, trailer.getPrev(), trailer);
    }

    // --- the method required by the lab ---
    public int indexOf(Position<E> p) {
        Position<E> walk = first();
        int index = 0;
        while (walk != null) {
            if (walk == p) {
                return index;
            }
            walk = after(walk);
            index++;
        }
        throw new IllegalArgumentException("position not found");
    }

    // simple test
    public static void main(String[] args) {
        LinkedPositionalList<String> list = new LinkedPositionalList<>();
        Position<String> p1 = list.addLast("A");
        Position<String> p2 = list.addLast("B");
        Position<String> p3 = list.addLast("C");
        Position<String> p4 = list.addLast("D");

        System.out.println("index of B = " + list.indexOf(p2)); // 1
        System.out.println("index of D = " + list.indexOf(p4)); // 3
    }
}
