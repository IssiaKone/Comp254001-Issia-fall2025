package issia_COMP254Lab1_Ex1;

public class DLList<E> {
    private static class Node<E> {
        E element;
        Node<E> prev;
        Node<E> next;
        Node(E e, Node<E> p, Node<E> n) {
            element = e; prev = p; next = n;
        }
    }

    private Node<E> header;
    private Node<E> trailer;
    private int size;

    public DLList() {
        header = new Node<>(null, null, null);
        trailer = new Node<>(null, header, null);
        header.next = trailer;
        size = 0;
    }

    public int size() { return size; }
    public boolean isEmpty() { return size == 0; }

    public void addFirst(E e) { insertBetween(e, header, header.next); }
    public void addLast(E e)  { insertBetween(e, trailer.prev, trailer); }

    private void insertBetween(E e, Node<E> predecessor, Node<E> successor) {
        Node<E> newest = new Node<>(e, predecessor, successor);
        predecessor.next = newest;
        successor.prev = newest;
        size++;
    }

    public E removeFirst() { return isEmpty() ? null : remove(header.next); }
    public E removeLast()  { return isEmpty() ? null : remove(trailer.prev); }

    private E remove(Node<E> node) {
        Node<E> pred = node.prev;
        Node<E> succ = node.next;
        pred.next = succ;
        succ.prev = pred;
        size--;
        E val = node.element;
        node.element = null; node.prev = null; node.next = null;
        return val;
    }

    /** Concatenate list M onto the end of list L. L is returned; M becomes empty. */
    public static <E> DLList<E> concatenate(DLList<E> L, DLList<E> M) {
        if (L == null) L = new DLList<>();
        if (M == null || M.isEmpty()) return L;

        Node<E> LLast = L.trailer.prev;
        Node<E> MFirst = M.header.next;
        Node<E> MLast = M.trailer.prev;

        if (L.isEmpty()) {
            L.header.next = MFirst;
            MFirst.prev = L.header;
        } else {
            LLast.next = MFirst;
            MFirst.prev = LLast;
        }

        L.trailer.prev = MLast;
        MLast.next = L.trailer;

        L.size += M.size;

        // empty M
        M.header.next = M.trailer;
        M.trailer.prev = M.header;
        M.size = 0;

        return L;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> walk = header.next;
        while (walk != trailer) {
            sb.append(walk.element);
            walk = walk.next;
            if (walk != trailer) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}
