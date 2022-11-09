import java.util.ArrayList;

public class BinaryTree {
    private Node root;

    public BinaryTree() {
        root = null;
    }

    public ArrayList<Integer> getNodesAtDistance(int distance) {
        var list = new ArrayList<Integer>();
        getNodesAtDistance(distance, root, list);
        return list;
    }

    private void getNodesAtDistance(int distance, Node root, ArrayList<Integer> list) {
        if (root == null) return;
        if (distance == 0) list.add(root.value);
        getNodesAtDistance(distance - 1, root.leftChild, list);
        getNodesAtDistance(distance - 1, root.rightChild, list);
    }

    public boolean isBST() {
        return isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean isBST(Node root, int min, int max) {
        if (root == null) return true;
        var value = root.value;
        return value > min && value < max
                && isBST(root.leftChild, min, value)
                && isBST(root.rightChild, value, max);
    }

    public boolean equals(BinaryTree tree) {
        if (tree == null)
            throw new IllegalStateException();
        return equals(root, tree.root);
    }

    private boolean equals(Node first, Node second) {
        if (first == null && second == null)
            return true;
        if (first != null && second != null)
            return first.value == second.value
                    && equals(first.leftChild, second.leftChild)
                    && equals(first.rightChild, second.rightChild);
        return false;
    }

    public int min() {
        return min(root);
    }

    private int min(Node root) {
        if (root == null)
            throw new IllegalStateException();
        var left = root.leftChild == null ? root.value : min(root.leftChild);
        var right = root.rightChild == null ? root.value : min(root.rightChild);
        return Math.min(Math.min(left, right), root.value);
    }


    public int BSTMin() {
        return BSTMin(root);
    }

    private int BSTMin(Node root) {
        if (root == null)
            throw new IllegalStateException();
        return root.leftChild == null ? root.value : BSTMin(root.leftChild);
    }

    public int height() {
        return height(root);
    }

    private int height(Node root) {
        if (root == null || root.leftChild == null && root.rightChild == null)
            return 0;
        return 1 + Math.max(height(root.leftChild), height(root.rightChild));
    }

    public void traverseLevelOrder() {
        for (var index = 0; index < height() + 1; ++index)
            for (var item : getNodesAtDistance(index))
                System.out.print(item + " ");
    }

    public void traversePreOrder() {
        traversePreOrder(root);
    }

    private void traversePreOrder(Node root) {
        if (root == null) return;
        System.out.print(root.value + " ");
        traversePreOrder(root.leftChild);
        traversePreOrder(root.rightChild);
    }

    public void traverseInOrder() {
        traverseInOrder(root);
    }

    private void traverseInOrder(Node root) {
        if (root == null) return;
        traverseInOrder(root.leftChild);
        System.out.print(root.value + " ");
        traverseInOrder(root.rightChild);
    }

    public void traversePostOrder() {
        traversePostOrder(root);
    }

    private void traversePostOrder(Node root) {
        if (root == null) return;
        traversePostOrder(root.leftChild);
        traversePostOrder(root.rightChild);
        System.out.print(root.value + " ");
    }

    public boolean find(int value) {
        var node = root;
        while (node != null)
            if (value > node.value)
                node = node.rightChild;
            else if (value < node.value)
                node = node.leftChild;
            else
                return true;
        return false;
    }

    public void insert(int value) {
        var node = new Node(value);
        if (root == null) {
            root = node;
        } else {
            var next = root;
            do
                if (value > next.value)
                    next = insertValueOrReturnNextNode(node, next);
                else if (value < next.value)
                    next = insertValueOrReturnLeftNode(node, next);
                else
                    next = null;
            while (next != null);
        }
    }

    private Node insertValueOrReturnLeftNode(Node node, Node root) {
        if (root.leftChild == null) {
            root.leftChild = node;
            return null;
        } else {
            root = root.leftChild;
        }
        return root;
    }

    private Node insertValueOrReturnNextNode(Node node, Node root) {
        if (root.rightChild == null) {
            root.rightChild = node;
            return null;
        } else {
            root = root.rightChild;
        }
        return root;
    }

    private static class Node {
        private final int value;
        private Node leftChild;
        private Node rightChild;

        public Node(int value) {
            this.value = value;
            leftChild = null;
            rightChild = null;
        }
    }
}
