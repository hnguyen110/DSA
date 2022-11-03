import java.util.Arrays;

public class PriorityQueue {
    private int counter = 0;
    private int[] items = new int[5];

    public void add(int item) throws Exception {
        if (isFull())
            throw new Exception("The queue is out of space");
        int index = shiftItemsToInsert(item);
        items[index] = item;
        counter += 1;
    }

    private boolean isFull() {
        return counter == items.length;
    }

    private int shiftItemsToInsert(int item) {
        int index;
        for (index = counter - 1; index >= 0; --index)
            if (items[index] >= item)
                items[index + 1] = items[index];
            else break;
        return index + 1;
    }

    public int remove() throws Exception {
        if (isEmpty())
            throw new Exception("The queue is empty");
        return items[--counter];
    }

    public boolean isEmpty() {
        return counter == 0;
    }

    @Override
    public String toString() {
        return "PriorityQueue{" +
                "items=" + Arrays.toString(items) +
                '}';
    }
}
