public class InsertionSort {
    public static void sort(int[] array) {
        for (var x = 1; x < array.length; ++x) {
            var current = array[x];
            var index = x - 1;
            for (var y = x - 1; y >= 0; --y) {
                if (current < array[y]) {
                    array[y + 1] = array[y];
                    index--;
                }
            }
            array[index + 1] = current;
        }
    }
}
