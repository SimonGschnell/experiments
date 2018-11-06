package user.app;

public class OrdenatorHeapSort implements Ordenator<int[]> {

    @Override
    public int[] sort(final int[] vector) {
        int[] arr = (int[]) vector;
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
}
