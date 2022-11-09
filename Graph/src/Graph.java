import java.util.*;

public class Graph {
    private class Node {
        private String label;

        public Node(String label) {
            this.label = label;
        }

        @Override
        public String toString() {
            return label;
        }
    }

    private Map<String, Node> nodes = new HashMap<>();
    private Map<Node, List<Node>> adjacencyList = new HashMap<>();

    public void addNode(String label) {
        var node = new Node(label);
        nodes.putIfAbsent(label, node);
        adjacencyList.putIfAbsent(node, new ArrayList<>());
    }

    public void addEdge(String from, String to) {
        var fromNode = nodes.get(from);
        if (fromNode == null)
            throw new IllegalArgumentException();

        var toNode = nodes.get(to);
        if (toNode == null)
            throw new IllegalArgumentException();

        adjacencyList
                .get(fromNode)
                .add(toNode);
    }

    public void print() {
        for (var source : adjacencyList.keySet()) {
            var targets = adjacencyList.get(source);
            if (!targets.isEmpty())
                System.out.println(source + " is connected to " + targets);
        }
    }

    public void removeNode(String label) {
        var node = nodes.get(label);
        if (node == null) return;
        for (var item : adjacencyList.keySet())
            adjacencyList.get(item).remove(node);
        adjacencyList.remove(node);
        nodes.remove(label);
    }

    public void removeEdge(String from, String to) {
        var fromNode = nodes.get(from);
        if (fromNode == null)
            throw new IllegalArgumentException();

        var toNode = nodes.get(to);
        if (toNode == null)
            throw new IllegalArgumentException();

        adjacencyList.get(fromNode).remove(toNode);
    }

    public void traverseDepthFirst(String root) {
        traverseDepthFirst(nodes.get(root), new HashSet<>());
    }

    private void traverseDepthFirst(Node root, Set<Node> set) {
        if (root == null) return;
        System.out.println(root.label);
        set.add(root);
        for (var node : adjacencyList.get(root))
            if (!set.contains(node))
                traverseDepthFirst(node, set);
    }

    public void traverseDepthFirstIteratively(String root) {
        traverseDepthFirstIteratively(nodes.get(root), new Stack<>());
    }

    public void traverseDepthFirstIteratively(Node root,  Stack<Node> stack) {
        if (root == null) return;
        var set = new HashSet<Node>();
        stack.add(root);
        while (!stack.isEmpty()) {
            var node = stack.pop();
            if (set.contains(node)) continue;
            System.out.println(node);
            set.add(node);
            for (var item : adjacencyList.get(node))
                if (!set.contains(item))
                    stack.add(item);
        }
    }

    public void traverseBreadthFirst(String label) {
        var root = nodes.get(label);
        if (root == null) return;

        var set = new HashSet<Node>();
        var queue = new ArrayDeque<Node>();
        queue.add(root);
        while (!queue.isEmpty()) {
            var item = queue.remove();
            if (set.contains(item)) continue;
            System.out.println(item);
            set.add(item);
            queue.addAll(adjacencyList.get(item));
        }
    }

    public List<String> topologicalSort() {
        var set = new HashSet<Node>();
        var stack = new Stack<Node>();
        for (var item : nodes.values())
            topologicalSort(item, set, stack);
        var result = new ArrayList<String>();
        while (!stack.isEmpty())
            result.add(stack.pop().label);
        return result;
    }

    private void topologicalSort(Node node, Set<Node> set, Stack<Node> stack) {
        if (set.contains(node)) return;
        set.add(node);
        for (var item : adjacencyList.get(node))
            topologicalSort(item, set, stack);
        stack.push(node);
    }

    public boolean hasCycle() {
        var allNodes = new Stack<Node>();
        allNodes.addAll(nodes.values());
        var currentNodes = new HashSet<Node>();
        var finishedNodes = new HashSet<Node>();
        while (!allNodes.isEmpty()) {
            var node = allNodes.pop();
            if (hasCycle(node, allNodes, currentNodes, finishedNodes))
                return true;
        }
        return false;
    }

    private boolean hasCycle(Node node, Stack<Node> allNodes, Set<Node> currentNodes, Set<Node> finishedNode) {
        allNodes.remove(node);
        currentNodes.add(node);
        for (var item : adjacencyList.get(node)) {
            if (finishedNode.contains(item)) continue;
            if (currentNodes.contains(item))
                return true;
            if (hasCycle(item, allNodes, currentNodes, finishedNode))
                return true;
        }
        currentNodes.remove(node);
        finishedNode.add(node);
        return false;
    }
}
