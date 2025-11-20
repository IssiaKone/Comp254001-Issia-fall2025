package ex2;

import java.util.Stack;

 class TransferStack {

    // move all elements from S to T
    public static <E> void transfer(Stack<E> S, Stack<E> T) {
        while (!S.isEmpty()) {
            T.push(S.pop());
        }
    }

    public static void main(String[] args) {
        Stack<Integer> s = new Stack<>();
        Stack<Integer> t = new Stack<>();

        s.push(1);
        s.push(2);
        s.push(3);
        s.push(4);

        transfer(s, t);

        System.out.println("S empty? " + s.isEmpty()); // true
        System.out.println("T top = " + t.peek());      // 1

        System.out.print("T content: ");
        while (!t.isEmpty()) {
            System.out.print(t.pop() + " ");
        }
        System.out.println();
    }
}
