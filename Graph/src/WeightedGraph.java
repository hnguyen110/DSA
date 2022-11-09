import java.util.*;

public class WeightedGraph {
    private class Node {
        private String label;
        private List<Edge> edges = new ArrayList<>();

        public Node(String label) {
            this.label = label;
        }

        public void addEdge(Node node, int weight) {
            edges.add(new Edge(this, node, weight));
        }

        public List<Edge> getEdges() {
            return edges;
        }

        @Override
        public String toString() {
            return label;
        }
    }

    private class Edge {
        private Node from;
        private Node to;
        private int weight;

        public Edge(Node from, Node to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return from + "->" + to;
        }
    }

    private class NodeEntry {
        private Node node;
        private int priority;

        public NodeEntry(Node node, int priority) {
            this.node = node;
            this.priority = priority;
        }
    }

    private Map<String, Node> nodes = new HashMap<>();
//    private Map<Node, List<Edge>> adjacencyList = new HashMap<>();

    public void addNode(String label) {
        var node = new Node(label);
        nodes.putIfAbsent(label, node);
    }

    public void addEdge(String from, String to, int weight) {
        var fromNode = nodes.get(from);
        if (fromNode == null)
            throw new IllegalArgumentException();

        var toNode = nodes.get(to);
        if (toNode == null)
            throw new IllegalArgumentException();

        fromNode.addEdge(toNode, weight);
        toNode.addEdge(fromNode, weight);
    }

    public void print() {
        for (var node : nodes.values()) {
            var edges = node.getEdges();
            if (!edges.isEmpty())
                System.out.println(node + " is connected to " + edges);
        }
    }

    public Path getShortestPath(String from, String to) {
        var fromNode = nodes.get(from);
        var toNode = nodes.get(to);

        if (fromNode == null)
            throw new IllegalArgumentException();
        if (toNode == null)
            throw new IllegalArgumentException();

        var previousNodes = new HashMap<Node, Node>();
        var distances = new HashMap<Node, Integer>();
        for (var node : nodes.values())
            distances.put(node, Integer.MAX_VALUE);
        distances.replace(fromNode, 0);
        var visitedNodes = new HashSet<Node>();
        var queue = new PriorityQueue<NodeEntry>(Comparator.comparingInt(e -> e.priority));
        queue.add(new NodeEntry(fromNode, 0));
        while (!queue.isEmpty()) {
            var current = queue.remove().node;
            visitedNodes.add(current);
            for (var edge : current.getEdges()) {
                if (visitedNodes.contains(edge.to)) continue;
                var distance = distances.get(current) + edge.weight;
                if (distance < distances.get(edge.to)) {
                    distances.replace(edge.to, distance);
                    previousNodes.put(edge.to, current);
                    queue.add(new NodeEntry(edge.to, distance));
                }
            }
        }

        return buildPath(toNode, previousNodes);
    }

    private static Path buildPath(Node node, HashMap<Node, Node> previousNodes) {
        var stack = new Stack<Node>();
        stack.push(node);
        var previousNode = previousNodes.get(node);
        while (previousNode != null) {
            stack.push(previousNode);
            previousNode = previousNodes.get(previousNode);
        }

        var path = new Path();
        while (!stack.isEmpty())
            path.add(stack.pop().label);
        return path;
    }

    public boolean hasCycle() {
        var visitedNodes = new HashSet<Node>();
        for (var node : nodes.values())
            if (!visitedNodes.contains(node) && hasCycle(node, null, visitedNodes))
                return true;
        return false;
    }

    private boolean hasCycle(Node node, Node root, Set<Node> visitedNodes) {
        visitedNodes.add(node);
        for (var edge : node.getEdges()) {
            if (edge.to == root)
                continue;
            if (visitedNodes.contains(edge.to) || hasCycle(edge.to, node, visitedNodes))
                return true;
        }

        return false;
    }

    public WeightedGraph getMinimumSpanningTree() {
        // Initialize a minimum spanning tree
        var tree = new WeightedGraph();
        if (nodes.isEmpty()) return tree;

        // Initialize the a priority queue to hold the edges and put the smallest edge at front
        var edges = new PriorityQueue<Edge>(Comparator.comparingInt(e -> e.weight));
        var startNode = nodes.values().iterator().next();
        edges.addAll(startNode.getEdges());
        tree.addNode(startNode.label);

        if (edges.isEmpty())
            return tree;

        // We keep looping until we connect all the node together
        while (tree.nodes.size() < nodes.size()) {
            // Get the smallest edge from the queue
            var minimumEdge = edges.remove();
            // Get the next node that connect via the smallest edge
            var nextNode = minimumEdge.to;
            // Check if the next node is already visited
            // If the node is already visited then we move on to the next one in the queue
            if (tree.nodes.containsKey(nextNode.label))
                continue;

            // If the node is not visited then we add it to the tree and connect the edges between them
            // because it is the next smallest node
            tree.addNode(nextNode.label);
            tree.addEdge(minimumEdge.from.label, nextNode.label, minimumEdge.weight);

            // Check the edge of the next node
            // If the next edge location point to a node that is already added in the tree then we stop
            for (var edge : nextNode.getEdges())
                if (!tree.nodes.containsKey(edge.to.label))
                    edges.add(edge);
        }

        return tree;
    }
}
