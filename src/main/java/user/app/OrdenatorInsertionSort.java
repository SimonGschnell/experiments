package user.app;

public class OrdenatorInsertionSort implements Ordenator<int[]> {

    @Override
    public int[] sort(final int[] vector) {
        int[] arr = (int[]) vector;
        int n = arr.length;
        for (int i = 1; i < n; ++i) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
        return arr;
    }

}
