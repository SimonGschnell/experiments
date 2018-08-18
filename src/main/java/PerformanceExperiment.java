
import esfinge.experiments.PerformanceMetrics;
import java.util.Random;
import esfinge.experiments.ABTestUser;

@PerformanceMetrics
public class PerformanceExperiment implements ABTestUser<String> {

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
