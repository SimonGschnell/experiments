
import esfinge.experiments.ABTestUser;
import esfinge.experiments.MemoryMetrics;
import esfinge.experiments.PerformanceMetrics;

@PerformanceMetrics
@MemoryMetrics
public class SortingExperiment implements ABTestUser<int[]> {

    private final int[] initialArray;

    public SortingExperiment(final int[] initialArray) {
        this.initialArray = initialArray;
    }

    @Override
    public int[] aTest() {
        int[] array = this.initialArray.clone();
        return SortingUtil.insertionSort(array);
    }

    @Override
    public int[] bTest() {
        int[] array = this.initialArray.clone();
        return SortingUtil.heapSort(array);
    }

}
