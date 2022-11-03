import java.util.Stack;

public class StackQueue {
    private Stack<Integer> firstStack = new Stack<>();
    private Stack<Integer> secondStack = new Stack<>();

    public void enqueue(int item) {
        firstStack.add(item);
    }

    public int dequeue() throws Exception {
        if (isEmpty()) throw new Exception("Empty queue");
        transferFirstStackToSecondStack();
        return secondStack.pop();
    }

    public int peek() throws Exception {
        if (isEmpty()) throw new Exception("Empty queue");
        transferFirstStackToSecondStack();
        return secondStack.peek();
    }

    private void transferFirstStackToSecondStack() {
        if (secondStack.isEmpty())
            while (!firstStack.isEmpty())
                secondStack.add(firstStack.pop());
    }

    public boolean isEmpty() {
        return firstStack.isEmpty() && secondStack.isEmpty();
    }

    @Override
    public String toString() {
        return "StackQueue{" +
                "firstStack=" + firstStack +
                ", secondStack=" + secondStack +
                '}';
    }
}
