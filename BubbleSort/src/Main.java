import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] array = {9, 8, 7, 3, 1, 2, 0, 5, 4};
//        BubbleSort.sort(array);
//        SelectionSort.sort(array);
//        InsertionSort.sort(array);
//        MergeSort.sort(array);
//        QuickSort.sort(array);
        BucketSort.sort(array, 5);
        System.out.println(Arrays.toString(array));
    }
}