
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
        return SortingUtil.insertionSort(initialArray.clone());
    }

    @Override
    public int[] bTest() {
        return SortingUtil.heapSort(initialArray.clone());
    }

}
