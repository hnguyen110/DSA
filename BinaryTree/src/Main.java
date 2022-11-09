public class Main {
    public static void main(String[] args) throws Exception {
        var tree = new BinaryTree();
        tree.insert(7);
//        tree.insert(4);
//        tree.insert(9);
//        tree.insert(1);
//        tree.insert(6);
//        tree.insert(8);
//        tree.insert(10);
        tree.traverseLevelOrder();
    }
}