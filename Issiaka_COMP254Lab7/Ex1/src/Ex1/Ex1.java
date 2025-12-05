package Ex1;

 class BinarySearchTree {

    // Node class
    class Node {
        int key;
        Node left, right;

        public Node(int k) {
            key = k;
            left = right = null;
        }
    }

    private Node root;

    public BinarySearchTree() {
        root = null;
    }

    // Insert method
    public void insert(int key) {
        root = insertRec(root, key);
    }

    private Node insertRec(Node root, int key) {
        if (root == null) return new Node(key);
        if (key < root.key) root.left = insertRec(root.left, key);
        else root.right = insertRec(root.right, key);
        return root;
    }


    // ITERATIVE treeSearch (Exercise 1)

    public Node treeSearchIterative(int key) {
        Node current = root;

        while (current != null) {
            if (key == current.key)
                return current;      // found
            else if (key < current.key)
                current = current.left;   // go left
            else
                current = current.right;  // go right
        }

        return null; // not found
    }

    // Test method
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        bst.insert(50);
        bst.insert(30);
        bst.insert(70);
        bst.insert(20);
        bst.insert(40);
        bst.insert(60);
        bst.insert(80);

        int searchKey = 40;
        Node result = bst.treeSearchIterative(searchKey);

        if (result != null)
            System.out.println("Key " + searchKey + " FOUND in the tree.");
        else
            System.out.println("Key " + searchKey + " NOT found.");
    }
}
