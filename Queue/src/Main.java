import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws Exception {
        var queue = new PriorityQueue();
        queue.add(5);
        queue.add(3);
        queue.add(6);
        queue.add(1);
        queue.add(4);
        while (!queue.isEmpty())
            System.out.println(queue.remove());
    }

    public static void reverse(Queue<Integer> queue) {
        if (!queue.isEmpty()) {
            var stack = new Stack<Integer>();
            while (!queue.isEmpty())
                stack.add(queue.remove());
            while (!stack.isEmpty())
                queue.add(stack.pop());
        }
    }
}
