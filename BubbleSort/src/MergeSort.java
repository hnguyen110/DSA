public class MergeSort {
    public static void sort(int[] array) {
        if (array.length < 2) return;
        var middle = array.length / 2;

        int[] left = new int[middle];
        System.arraycopy(array, 0, left, 0, middle);

        int[] right = new int[array.length - middle];
        if (array.length - middle >= 0)
            System.arraycopy(array, middle, right, 0, array.length - middle);

        sort(left);
        sort(right);

        merge(left, right, array);
    }

    private static void merge(int[] left, int[] right, int[] result) {
        int x = 0, y = 0, z = 0;
        while (x != left.length && y != right.length)
            if (left[x] < right[y])
                result[z++] = left[x++];
            else
                result[z++] = right[y++];

        while (x != left.length)
            result[z++] = left[x++];

        while (y != right.length)
            result[z++] = right[y++];
    }
}
