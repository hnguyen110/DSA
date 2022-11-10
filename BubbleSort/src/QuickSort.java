public class QuickSort {
    public static void sort(int[] array) {
        sort(array, 0, array.length - 1);
    }

    public static void sort(int[] array, int start, int end) {
        if (start >= end) return;
        var boundary = partition(array, start, end);
        sort(array, start, boundary - 1);
        sort(array, boundary + 1, end);
    }

    public static int partition(int[] array, int start, int end) {
        var pivot = array[end];
        var boundary = start - 1;
        for (var index = start; index <= end; ++index)
            if (array[index] <= pivot)
                swap(array, index, ++boundary);
        return boundary;
    }

    private static void swap(int[] array, int x, int y) {
        var temp = array[x];
        array[x] = array[y];
        array[y] = temp;
    }
}