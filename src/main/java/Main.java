
import esfinge.experiments.ABTest;
import esfinge.experiments.ABTestBuilder;
import esfinge.experiments.MetricRecorderLogger;
import esfinge.experiments.SelectorWithPersistence;

public class Main {

    public static void main(String[] args) throws Exception {

        ABTestBuilder<Ordenador, int[]> abTestBuilder = new ABTestBuilder<>();

        ABTest<Ordenador, int[]> abTest = abTestBuilder.createFor(Ordenador.class).
                withSelector(new SelectorWithPersistence()).
                withMetricRecorder(new MetricRecorderLogger()).
                withATest("insertionSort").
                withBTest("heapSort").
                build();

        for (int i = 0; i < 10; i++) {
            int[] orderlyArray = abTest.run();
        }

    }
}
