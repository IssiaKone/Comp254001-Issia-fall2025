class LinkedBinaryTree<E> {

    // ----- Position interface -----
    public static interface Position<E> {
        E getElement();
    }

    // ----- Node class -----
    protected static class Node<E> implements Position<E> {
        private E element;
        private Node<E> parent;
        private Node<E> left;
        private Node<E> right;

        public Node(E e, Node<E> p, Node<E> l, Node<E> r) {
            element = e;
            parent = p;
            left = l;
            right = r;
        }

        public E getElement() { return element; }
        public Node<E> getParent() { return parent; }
        public Node<E> getLeft() { return left; }
        public Node<E> getRight() { return right; }

        public void setElement(E e) { element = e; }
        public void setParent(Node<E> p) { parent = p; }
        public void setLeft(Node<E> l) { left = l; }
        public void setRight(Node<E> r) { right = r; }
    }

    // ----- Tree fields -----
    protected Node<E> root = null;
    private int size = 0;

    public LinkedBinaryTree() { }

    // ----- Utility: validate position -----
    protected Node<E> validate(Position<E> p) {
        if (!(p instanceof Node<?>))
            throw new IllegalArgumentException("Not a valid position type");
        Node<E> node = (Node<E>) p;
        if (node.getParent() == node)  // convention for deprecated node
            throw new IllegalArgumentException("Position is no longer in the tree");
        return node;
    }

    // ----- Basic accessors -----
    public int size() { return size; }
    public boolean isEmpty() { return size == 0; }
    public Position<E> root() { return root; }

    public Position<E> parent(Position<E> p) {
        Node<E> node = validate(p);
        return node.getParent();
    }

    public Position<E> left(Position<E> p) {
        Node<E> node = validate(p);
        return node.getLeft();
    }

    public Position<E> right(Position<E> p) {
        Node<E> node = validate(p);
        return node.getRight();
    }

    // ----- Update methods -----
    public Position<E> addRoot(E e) {
        if (root != null) throw new IllegalStateException("Tree is not empty");
        root = new Node<>(e, null, null, null);
        size = 1;
        return root;
    }

    public Position<E> addLeft(Position<E> p, E e) {
        Node<E> parent = validate(p);
        if (parent.getLeft() != null)
            throw new IllegalArgumentException("Left child already exists");
        Node<E> child = new Node<>(e, parent, null, null);
        parent.setLeft(child);
        size++;
        return child;
    }

    public Position<E> addRight(Position<E> p, E e) {
        Node<E> parent = validate(p);
        if (parent.getRight() != null)
            throw new IllegalArgumentException("Right child already exists");
        Node<E> child = new Node<>(e, parent, null, null);
        parent.setRight(child);
        size++;
        return child;
    }

    // ----- Inorder traversal for debugging -----
    public void printInorder() {
        System.out.print("Inorder: ");
        inorderSubtree(root);
        System.out.println();
    }

    private void inorderSubtree(Node<E> p) {
        if (p == null) return;
        inorderSubtree(p.getLeft());
        System.out.print(p.getElement() + " ");
        inorderSubtree(p.getRight());
    }


    // EXERCISE 1: inorderNext(p)

    public Position<E> inorderNext(Position<E> p) {
        Node<E> node = validate(p);

        // Case 1: if right child exists → next is leftmost in right subtree
        if (node.getRight() != null) {
            Node<E> curr = node.getRight();
            while (curr.getLeft() != null) {
                curr = curr.getLeft();
            }
            return curr;
        }

        // Case 2: no right child → go up until coming from left child
        Node<E> curr = node;
        Node<E> parent = curr.getParent();

        while (parent != null && curr == parent.getRight()) {
            curr = parent;
            parent = parent.getParent();
        }

        // parent may be null (p was last in inorder)
        return parent;
    }


    // Public wrapper: for each node, print element + height of its subtree
    public void printSubtreeHeights() {
        System.out.println("Node elements with subtree heights:");
        computeHeightAndPrint(root);
    }

    // Returns height of subtree rooted at p
    private int computeHeightAndPrint(Node<E> p) {
        if (p == null) {
            return -1;      // height of empty subtree
        }

        int leftH = computeHeightAndPrint(p.getLeft());
        int rightH = computeHeightAndPrint(p.getRight());

        int height = 1 + Math.max(leftH, rightH);

        System.out.println("Element " + p.getElement() + " → height = " + height);

        return height;
    }
}


class Ex1 {
    public static void main(String[] args) {

        LinkedBinaryTree<Integer> tree = new LinkedBinaryTree<>();

        LinkedBinaryTree.Position<Integer> r  = tree.addRoot(10);
        LinkedBinaryTree.Position<Integer> n5 = tree.addLeft(r, 5);
        LinkedBinaryTree.Position<Integer> n15 = tree.addRight(r, 15);
        LinkedBinaryTree.Position<Integer> n2 = tree.addLeft(n5, 2);
        LinkedBinaryTree.Position<Integer> n7 = tree.addRight(n5, 7);
        LinkedBinaryTree.Position<Integer> n12 = tree.addLeft(n15, 12);

        tree.printInorder();

        // Now test inorderNext for a few positions
        testNext(tree, n2);
        testNext(tree, n5);
        testNext(tree, n7);
        testNext(tree, r);
        testNext(tree, n12);
        testNext(tree, n15);
    }

    private static void testNext(LinkedBinaryTree<Integer> tree,
                                 LinkedBinaryTree.Position<Integer> p) {
        LinkedBinaryTree.Position<Integer> next = tree.inorderNext(p);

        System.out.print("inorderNext(" + p.getElement() + ") = ");
        if (next == null) {
            System.out.println("null (this was the last node in inorder)");
        } else {
            System.out.println(next.getElement());
        }
    }
}
