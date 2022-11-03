import java.util.Arrays;

public class ArrayQueue {
    private final int[] items;
    private int front;
    private int rear;
    private int counter;

    public ArrayQueue(int capacity) {
        items = new int[capacity];
        front = 0;
        rear = 0;
        counter = 0;
    }

    public void enqueue(int item) throws Exception {
        if (counter == items.length)
            throw new Exception("The queue is full");
        items[rear] = item;
        rear = (rear + 1) % items.length;
        ++counter;
    }

    public int dequeue() {
        var item = items[front];
        items[front] = 0;
        front = (front + 1) % items.length;
        counter--;
        return item;
    }

    @Override
    public String toString() {
        return Arrays.toString(items);
    }
}
