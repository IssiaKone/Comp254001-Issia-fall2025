package issia_COMP254Lab1_Ex1;

public class Main1 {
    public static void main(String[] args) {
        DLList<Integer> L = new DLList<>();
        DLList<Integer> M = new DLList<>();
        for (int i = 1; i <= 3; i++) L.addLast(i);
        for (int i = 4; i <= 7; i++) M.addLast(i);

        System.out.println("L before: " + L);
        System.out.println("M before: " + M);
        DLList<Integer> result = DLList.concatenate(L, M);
        System.out.println("After concatenate:");
        System.out.println("L now: " + result + " (size=" + result.size() + ")");
        System.out.println("M now (should be empty): " + M + " (size=" + M.size() + ")");
    }
}
