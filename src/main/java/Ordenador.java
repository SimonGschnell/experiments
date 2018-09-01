
import esfinge.experiments.MemoryMetrics;
import esfinge.experiments.PerformanceMetrics;
import java.util.Random;

@PerformanceMetrics
@MemoryMetrics
public class Ordenador {

    private final int[] arrayInteger;

    public Ordenador() {
        this.arrayInteger = inputData();
    }

    public int[] insertionSort() {
        int[] arr = (int[]) this.arrayInteger.clone();
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

    public int[] heapSort() {
        int[] arr = (int[]) this.arrayInteger.clone();
        buildMaxHeap(arr);
        int n = arr.length;
        for (int i = arr.length - 1; i > 0; i--) {
            swap(arr, i, 0);
            maxHeapify(arr, 0, --n);
        }
        return arr;
    }

    private void buildMaxHeap(int[] v) {
        for (int i = v.length / 2 - 1; i >= 0; i--) {
            maxHeapify(v, i, v.length);
        }
    }

    private void maxHeapify(int[] vetor, int pos, int tamanhoDoVetor) {
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

    private void swap(int[] v, int j, int aposJ) {
        int aux = v[j];
        v[j] = v[aposJ];
        v[aposJ] = aux;
    }

    private int[] inputData() {
        int[] initialArray = new int[200_000];
        for (int i = 0; i < initialArray.length; i++) {
            initialArray[i] = (new Random()).nextInt();
        }
        return initialArray;
    }

}
