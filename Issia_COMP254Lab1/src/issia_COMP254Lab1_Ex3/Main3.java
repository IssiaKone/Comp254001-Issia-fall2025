package issia_COMP254Lab1_Ex3;

public class Main3 {
    public static void main(String[] args) {
        CircleLL<String> list = new CircleLL<>();
        list.addLast("A"); list.addLast("B"); list.addLast("C");
        System.out.println("Original: " + list + " size=" + list.size());

        CircleLL<String> cloned = list.clone();
        System.out.println("Cloned:   " + cloned + " size=" + cloned.size());

        list.rotate();
        list.removeFirst();
        System.out.println("After mutating original:");
        System.out.println("Original: " + list + " size=" + list.size());
        System.out.println("Cloned:   " + cloned + " size=" + cloned.size());
    }
}
