
import esfinge.experiments.ABTest;
import esfinge.experiments.ABTestBuilder;
import esfinge.experiments.MetricRecorderLogger;
import esfinge.experiments.SelectorWithPersistence;
import esfinge.experiments.plot.PlotMaker;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

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

        //Plot metrics
        String path = "plots.txt";
        try {
            new PlotMaker("Memory", path).showInFrame();
            new PlotMaker("Time", path).showInFrame();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, "O arquivo de m\u00e9tricas para plotagem ({0}) n\u00e3o est\u00e1 dispon\u00edvel.", path);

        }
    }
}
