
import esfinge.experiments.ABTest;
import esfinge.experiments.PerformanceMetrics;
import java.util.Random;

@PerformanceMetrics
public class PerformanceExperiment implements ABTest {

    private final int array_size = 200_000;
    private final int[] array = new int[array_size];

    public PerformanceExperiment() {
        for (int i = 0; i < array.length; i++) {
            array[i] = (new Random()).nextInt();
        }
    }

    @Override
    public String aTest() {
        int[] arr = this.array.clone();
        return SortMethods.insertionSort(arr);
    }

    @Override
    public String bTest() {
        int[] arr = this.array.clone();
        //return SortMethods.quickSort(arr, 0, arr.length - 1);
        return SortMethods.heapSort(arr);
    }

}
