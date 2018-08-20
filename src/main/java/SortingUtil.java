
public class SortingUtil {

    public static int[] insertionSort(final int[] arr) {
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

    public static int[] heapSort(final int[] arr) {
        buildMaxHeap(arr);
        int n = arr.length;
        for (int i = arr.length - 1; i > 0; i--) {
            swap(arr, i, 0);
            maxHeapify(arr, 0, --n);
        }
        return arr;
    }

    public static int[] quickSort(final int[] arr, final int begin, final int end) {
        if (begin < end) {
            int meio = partition(arr, begin, end);
            quickSort(arr, begin, meio);
            quickSort(arr, meio + 1, end);
        }
        return arr;
    }

    private static int partition(int arr[], int begin, int end) {
        int pivo, topo, i;
        pivo = arr[begin];
        topo = begin;
        for (i = begin + 1; i <= end; i++) {
            if (arr[i] < pivo) {
                arr[topo] = arr[i];
                arr[i] = arr[topo + 1];
                topo++;
            }
        }
        arr[topo] = pivo;
        return topo;
    }

    private static void buildMaxHeap(int[] v) {
        for (int i = v.length / 2 - 1; i >= 0; i--) {
            maxHeapify(v, i, v.length);
        }

    }

    private static void maxHeapify(int[] vetor, int pos, int tamanhoDoVetor) {

        int max = 2 * pos + 1, right = max + 1;
        if (max < tamanhoDoVetor) {

            if (right < tamanhoDoVetor && vetor[max] < vetor[right]) {
                max = right;
            }

            if (vetor[max] > vetor[pos]) {
                swap(vetor, max, pos);
                maxHeapify(vetor, max, tamanhoDoVetor);
            }
        }
    }

    public static void swap(int[] v, int j, int aposJ) {
        int aux = v[j];
        v[j] = v[aposJ];
        v[aposJ] = aux;
    }

}
