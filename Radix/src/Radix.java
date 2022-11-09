import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class Radix {
    private static final int ALPHABET_SIZE = 26;
    private static final char FIRST_CHARACTER = 'a';

    private class Node {
        private char value;
        private Hashtable<Character, Node> children = new Hashtable<>();
        private boolean isEndOfWord;

        public Node(char value) {
            this.value = value;
        }

        public boolean hasChild(char item) {
            return children.containsKey(item);
        }

        public boolean hasChildren() {
            return !children.isEmpty();
        }

        public void remove(char item) {
            children.remove(item);
        }

        public void add(char item) {
            children.put(item, new Node(item));
        }

        public Node get(char item) {
            return children.get(item);
        }

        public Node[] getChildren() {
            return children
                    .values()
                    .toArray(new Node[0]);
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }
    }

    private Node root = new Node(' ');

    public void insert(String word) {
        var node = root;
        for (var item : word.toCharArray()) {
            if (!node.hasChild(item))
                node.add(item);
            node = node.get(item);
        }
        node.isEndOfWord = true;
    }

    public boolean contains(String word) {
        if (word == null) return false;
        var node = root;
        for (var item : word.toCharArray()) {
            if (!node.hasChild(item))
                return false;
            node = node.get(item);
        }
        return node.isEndOfWord;
    }

    public void traverse() {
        traverse(root);
    }

    private void traverse(Node root) {
        System.out.print(root.value);
        for (var item : root.getChildren())
            traverse(item);
    }

    public void remove(String word) {
        remove(root, word, 0);
    }

    private void remove(Node root, String word, int index) {
        if (word == null) return;
        if (index == word.length()) {
            root.isEndOfWord = false;
            return;
        }
        var character = word.charAt(index);
        var item = root.get(character);
        if (item == null) return;
        remove(item, word, index + 1);
        if (!item.isEndOfWord && !item.hasChildren())
            root.remove(character);
    }

    public List<String> findWords(String prefix) {
        if (prefix == null) return null;
        var wordList = new ArrayList<String>();
        var node = findLastNodeOf(prefix);
        if (node != null)
            findWords(node, prefix, wordList);
        return wordList;
    }

    private void findWords(Node root, String prefix, List<String> words) {
        if (root.isEndOfWord)
            words.add(prefix);

        for (var item : root.getChildren())
            findWords(item, prefix + item.value, words);
    }

    private Node findLastNodeOf(String prefix) {
        var node = root;
        for (var item : prefix.toCharArray()) {
            var child = node.get(item);
            if (child == null) return null;
            node = child;
        }
        return node;
    }
}
