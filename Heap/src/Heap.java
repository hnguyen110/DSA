import java.util.Arrays;

public class Heap {
    private final int[] items = new int[10];
    private int size;

    public static int getKthLargest(int[] array, int k) {
        if (k < 1 || k > array.length)
            throw new IllegalStateException();

        var heap = new Heap();
        for (var item : array)
            heap.insert(item);
        for (var index = 0; index < k - 1; ++index) {
            heap.remove();
        }
        return heap.max();
    }

    public static void heapify(int[] array) {
        for (var index = array.length / 2 - 1; index >= 0; --index)
            heapify(array, index);
    }

    private static void heapify(int[] array, int index) {
        var largestIndex = index;

        var leftIndex = index * 2 + 1;
        if (leftIndex < array.length && array[index] < array[leftIndex])
            largestIndex = leftIndex;

        var rightIndex = index * 2 + 2;
        if (rightIndex < array.length && array[index] < array[rightIndex])
            largestIndex = rightIndex;

        if (index == largestIndex) return;

        swap(array, index, largestIndex);
        heapify(array, largestIndex);
    }

    private static void swap(int[] array, int index, int largestIndex) {
        var temp = array[index];
        array[index] = array[largestIndex];
        array[largestIndex] = temp;
    }

    public int max() {
        if (isEmpty())
            throw new IllegalStateException();
        return items[0];
    }

    public int remove() {
        if (isEmpty())
            throw new IllegalStateException();
        var root = items[0];
        swapRootWithLastNode();
        bubbleDown();
        return root;
    }

    private void bubbleDown() {
        int index = 0;
        var root = items[index];
        while (!isValidRoot(root, index)) {
            var largerNodeIndex = getLargerChildNodeIndex(index);
            swap(index, largerNodeIndex);
            index = largerNodeIndex;
        }
    }

    private boolean isValidRoot(int root, int index) {
        return root >= getLeftNode(index) && root >= getRightNode(index);
    }

    private void swap(int first, int second) {
        var temp = items[first];
        items[first] = items[second];
        items[second] = temp;
    }

    private int getLargerChildNodeIndex(int index) {
        return getLeftNode(index) > getRightNode(index)
                ? getLeftNodeIndex(index)
                : getRightNodeIndex(index);
    }

    private int getRightNode(int index) {
        return items[getRightNodeIndex(index)];
    }

    private static int getRightNodeIndex(int index) {
        return index * 2 + 2;
    }

    private int getLeftNode(int index) {
        return items[getLeftNodeIndex(index)];
    }

    private static int getLeftNodeIndex(int index) {
        return index * 2 + 1;
    }

    private void swapRootWithLastNode() {
        items[0] = items[--size];
        items[size] = 0;
    }

    private boolean isEmpty() {
        return items.length == 0;
    }

    public void insert(int value) {
        if (isFull())
            throw new IllegalStateException();
        items[size++] = value;
        bubbleUp();
    }

    private boolean isFull() {
        return size == items.length;
    }

    private void bubbleUp() {
        var index = size - 1;
        while (index > 0 && items[index] > items[getRootIndex(index)]) {
            swap(index, getRootIndex(index));
            index = getRootIndex(index);
        }
    }

    private int getRootIndex(int index) {
        return (index - 1) / 2;
    }

    @Override
    public String toString() {
        return Arrays.toString(items);
    }
}
