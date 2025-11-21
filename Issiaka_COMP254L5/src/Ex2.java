
public class Ex2 {
    public static void main(String[] args) {
        // Reuse the same tree structure and class LinkedBinaryTree

        LinkedBinaryTree<Integer> tree = new LinkedBinaryTree<>();


        LinkedBinaryTree.Position<Integer> r  = tree.addRoot(1);
        LinkedBinaryTree.Position<Integer> n2 = tree.addLeft(r, 2);
        LinkedBinaryTree.Position<Integer> n3 = tree.addRight(r, 3);
        LinkedBinaryTree.Position<Integer> n4 = tree.addLeft(n2, 4);
        LinkedBinaryTree.Position<Integer> n5 = tree.addRight(n2, 5);
        LinkedBinaryTree.Position<Integer> n6 = tree.addRight(n3, 6);
        LinkedBinaryTree.Position<Integer> n7 = tree.addRight(n6, 7);

        tree.printInorder();
        tree.printSubtreeHeights();
    }
}
