import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] numbers = {5, 3, 10, 1, 4, 2};
        Heap.heapify(numbers);
        System.out.println(Heap.getKthLargest(numbers,7));
    }
}