public class SelectionSort {
    public static void sort(int[] array) {
        for (var x = 0; x < array.length; ++x) {
            var index = x;
            for (var y = x + 1; y < array.length; ++y)
                if (array[y - 1] > array[y])
                    index = y;
            swap(array, x, index);
        }
    }

    private static void swap(int[] array, int x, int y) {
        var temp = array[x];
        array[x] = array[y];
        array[y] = temp;
    }
}
