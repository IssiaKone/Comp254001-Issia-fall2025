package ex3;

 class LinkedQueue<E> {

    private static class Node<E> {
        E element;
        Node<E> next;
        Node(E e, Node<E> n) {
            element = e;
            next = n;
        }
    }

    private Node<E> head;
    private Node<E> tail;
    private int size;

    public LinkedQueue() {
        head = null;
        tail = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void enqueue(E e) {
        Node<E> newest = new Node<>(e, null);
        if (isEmpty()) {
            head = newest;
        } else {
            tail.next = newest;
        }
        tail = newest;
        size++;
    }

    public E first() {
        if (isEmpty()) return null;
        return head.element;
    }

    public E dequeue() {
        if (isEmpty()) return null;
        E answer = head.element;
        head = head.next;
        size--;
        if (isEmpty()) {
            tail = null;
        }
        return answer;
    }

    // required method
    public void concatenate(LinkedQueue<E> q2) {
        if (q2.isEmpty()) {
            return;
        }
        if (this.isEmpty()) {
            this.head = q2.head;
            this.tail = q2.tail;
            this.size = q2.size;
        } else {
            this.tail.next = q2.head;
            this.tail = q2.tail;
            this.size += q2.size;
        }
        q2.head = null;
        q2.tail = null;
        q2.size = 0;
    }

    // test
    public static void main(String[] args) {
        LinkedQueue<String> q1 = new LinkedQueue<>();
        LinkedQueue<String> q2 = new LinkedQueue<>();

        q1.enqueue("A");
        q1.enqueue("B");
        q1.enqueue("C");

        q2.enqueue("D");
        q2.enqueue("E");

        q1.concatenate(q2);

        System.out.println("q2 empty? " + q2.isEmpty()); // true
        System.out.println("q1 size = " + q1.size());    // 5

        System.out.print("q1 items: ");
        while (!q1.isEmpty()) {
            System.out.print(q1.dequeue() + " ");
        }
        System.out.println();
    }
}
