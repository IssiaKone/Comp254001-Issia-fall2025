package issia_COMP254Lab1_Ex2;

public class SinglyLinked<E> {
    public static class Node<E> {
        E element;
        Node<E> next;
        Node(E e, Node<E> n) { element = e; next = n; }
        public E getElement() { return element; }
        public Node<E> getNext() { return next; }
    }

    private Node<E> head = null;
    private Node<E> tail = null;
    private int size = 0;

    public int size() { return size; }
    public boolean isEmpty() { return size == 0; }
    public Node<E> headNode() { return head; }
    public Node<E> tailNode() { return tail; }

    public void addFirst(E e) {
        head = new Node<>(e, head);
        if (tail == null) tail = head;
        size++;
    }

    public void addLast(E e) {
        Node<E> newest = new Node<>(e, null);
        if (isEmpty()) {
            head = newest; tail = newest;
        } else {
            tail.next = newest;
            tail = newest;
        }
        size++;
    }

    /** Swap two nodes by references (not just data). */
    public void swapNodes(Node<E> node1, Node<E> node2) {
        if (node1 == null || node2 == null || node1 == node2) return;

        Node<E> prev1 = null, prev2 = null, curr = head;
        while (curr != null && (prev1 == null || prev2 == null)) {
            if (curr.next == node1) prev1 = curr;
            if (curr.next == node2) prev2 = curr;
            curr = curr.next;
        }
        boolean found1 = (node1 == head) || (prev1 != null);
        boolean found2 = (node2 == head) || (prev2 != null);
        if (!found1 || !found2) return;

        if (node1 == head) prev1 = null;
        if (node2 == head) prev2 = null;

        if (prev1 != null) prev1.next = node2; else head = node2;
        if (prev2 != null) prev2.next = node1; else head = node1;

        Node<E> temp = node1.next;
        node1.next = node2.next;
        node2.next = temp;

        if (node1.next == null) tail = node1;
        else if (node2.next == null) tail = node2;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> walk = head;
        while (walk != null) {
            sb.append(walk.element);
            walk = walk.next;
            if (walk != null) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}
