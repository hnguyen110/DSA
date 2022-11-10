public class BubbleSort {
    public static void sort(int[] array) {
        for (var x = 0; x < array.length; ++x)
            for (var y = 1; y < array.length - x; ++y)
                if (array[y - 1] > array[y])
                    swap(array, y - 1, y);
    }

    private static void swap(int[] array, int x, int y) {
        var temp = array[x];
        array[x] = array[y];
        array[y] = temp;
    }
}
