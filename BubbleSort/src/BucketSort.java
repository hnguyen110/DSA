import java.util.ArrayList;
import java.util.Collections;

public class BucketSort {
    public static void sort(int[] array, int size) {
        var buckets = createBuckets(array, size);
        sort(array, buckets);
    }

    private static void sort(int[] array, ArrayList<ArrayList<Integer>> buckets) {
        var index = 0;
        for (var bucket : buckets) {
            Collections.sort(bucket);
            for (var item : bucket)
                array[index++] = item;
        }
    }

    private static ArrayList<ArrayList<Integer>> createBuckets(int[] array, int size) {
        var buckets = new ArrayList<ArrayList<Integer>>();
        for (var index = 0; index < size; ++index)
            buckets.add(new ArrayList<>());

        for (var item : array)
            buckets.get(item / size).add(item);
        return buckets;
    }
}
