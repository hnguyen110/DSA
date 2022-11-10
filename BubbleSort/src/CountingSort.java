public class CountingSort {
    public static void sort(int[] array, int max) {
        int[] counts = new int[max + 1];
        for (var item : array)
            counts[item]++;
        var z = 0;
        for (var x = 0; x < counts.length; ++x)
            for (var y = 0; y < counts[x]; ++y)
                array[z++] = x;
    }
}
