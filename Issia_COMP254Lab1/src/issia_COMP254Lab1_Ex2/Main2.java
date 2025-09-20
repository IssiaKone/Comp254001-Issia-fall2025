package issia_COMP254Lab1_Ex2;

public class Main2 {
    public static void main(String[] args) {
        SinglyLinked<Integer> list = new SinglyLinked<>();
        list.addLast(1); list.addLast(2); list.addLast(3); list.addLast(4); list.addLast(5);
        System.out.println("Original: " + list);

        SinglyLinked.Node<Integer> n1 = list.headNode();
        SinglyLinked.Node<Integer> n2 = n1.getNext();
        SinglyLinked.Node<Integer> n3 = n2.getNext();
        SinglyLinked.Node<Integer> n4 = n3.getNext();
        SinglyLinked.Node<Integer> n5 = n4.getNext();

        list.swapNodes(n2, n4);
        System.out.println("After swapping 2 and 4: " + list);

        list.swapNodes(n1, n5);
        System.out.println("After swapping 1 and 5: " + list);

        // swap adjacent first two
        SinglyLinked.Node<Integer> a = list.headNode();
        SinglyLinked.Node<Integer> b = a.getNext();
        list.swapNodes(a, b);
        System.out.println("After swapping first two (adjacent): " + list);
    }
}
