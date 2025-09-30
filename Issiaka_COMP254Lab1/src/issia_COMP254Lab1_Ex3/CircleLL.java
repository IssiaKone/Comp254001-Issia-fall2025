package issia_COMP254Lab1_Ex3;

public class CircleLL<E> implements Cloneable {
    private static class Node<E> {
        E element;
        Node<E> next;
        Node(E e, Node<E> n) { element = e; next = n; }
    }

    private Node<E> tail = null; // tail.next is head
    private int size = 0;

    public int size() { return size; }
    public boolean isEmpty() { return size == 0; }

    public E first() { return isEmpty() ? null : tail.next.element; }
    public E last()  { return isEmpty() ? null : tail.element; }

    public void rotate() { if (tail != null) tail = tail.next; }

    public void addFirst(E e) {
        if (isEmpty()) {
            tail = new Node<>(e, null);
            tail.next = tail;
        } else {
            Node<E> newest = new Node<>(e, tail.next);
            tail.next = newest;
        }
        size++;
    }

    public void addLast(E e) { addFirst(e); tail = tail.next; }

    public E removeFirst() {
        if (isEmpty()) return null;
        Node<E> head = tail.next;
        if (head == tail) tail = null;
        else tail.next = head.next;
        size--;
        E ans = head.element;
        head.element = null; head.next = null;
        return ans;
    }

    @Override
    public CircleLL<E> clone() {
        CircleLL<E> copy = new CircleLL<>();
        if (this.isEmpty()) return copy;
        Node<E> curr = this.tail.next; // head
        do {
            copy.addLast(curr.element);
            curr = curr.next;
        } while (curr != this.tail.next);
        return copy;
    }

    @Override
    public String toString() {
        if (isEmpty()) return "[]";
        StringBuilder sb = new StringBuilder("[");
        Node<E> head = tail.next;
        Node<E> curr = head;
        do {
            sb.append(curr.element);
            curr = curr.next;
            if (curr != head) sb.append(", ");
        } while (curr != head);
        sb.append("]");
        return sb.toString();
    }
}
