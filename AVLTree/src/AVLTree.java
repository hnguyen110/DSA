public class AVLTree {
    AVLNode root;

    public void insert(int value) {
        root = insert(root, value);
    }

    private AVLNode insert(AVLNode root, int value) {
        if (root == null)
            return new AVLNode(value);

        if (value > root.value)
            root.rightChild = insert(root.rightChild, value);
        else
            root.leftChild = insert(root.leftChild, value);
        root.height = getHeight(root);
        return balance(root);
    }

    private AVLNode rotateLeft(AVLNode root) {
        var master = root.rightChild;
        root.rightChild = master.leftChild;
        master.leftChild = root;
        root.height = getHeight(root);
        master.height = getHeight(master);
        return master;
    }

    private AVLNode rotateRight(AVLNode root) {
        var master = root.leftChild;
        root.leftChild = master.rightChild;
        master.rightChild = root;
        root.height = getHeight(root);
        master.height = getHeight(master);
        return master;
    }

    private int getHeight(AVLNode master) {
        return Math.max(height(master.leftChild), height(master.rightChild)) + 1;
    }

    private AVLNode balance(AVLNode root) {
        if (isLeftHeavy(root)) {
            if (getBalanceFactor(root.leftChild) < 0)
                root.leftChild = rotateLeft(root.leftChild);
            return rotateRight(root);
        } else if (isRightHeavy(root)) {
            if (getBalanceFactor(root.rightChild) > 0)
                root.rightChild = rotateRight(root.rightChild);
            return rotateLeft(root);
        }
        return root;
    }

    private int getBalanceFactor(AVLNode root) {
        return root == null ? 0 : height(root.leftChild) - height(root.rightChild);
    }

    private boolean isLeftHeavy(AVLNode root) {
        return getBalanceFactor(root) > 1;
    }

    private boolean isRightHeavy(AVLNode root) {
        return getBalanceFactor(root) < -1;
    }

    public void traverseInOrder() {
        traverseInOrder(root);
    }

    private void traverseInOrder(AVLNode root) {
        if (root == null) return;
        traverseInOrder(root.leftChild);
        System.out.print(root.value + " ");
        traverseInOrder(root.rightChild);
    }

    private int height(AVLNode node) {
        return node == null ? -1 : node.height;
    }

    private static class AVLNode {
        int value;
        int height;
        AVLNode leftChild;
        AVLNode rightChild;

        public AVLNode(int value) {
            this.value = value;
        }
    }
}
