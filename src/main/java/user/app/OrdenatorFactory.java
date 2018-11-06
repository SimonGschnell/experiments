package user.app;

import esfinge.cnext.ABTest;
import esfinge.cnext.ABTestBuilder;
import esfinge.cnext.metric.MetricRecorderLogger;
import esfinge.cnext.selector.SelectorRandom;

public class OrdenatorFactory {

    public static Ordenator create(Class... implementations) throws Exception {
        ABTestBuilder<Ordenator, int[]> abTestBuilder = new ABTestBuilder<>();
        ABTest<Ordenator, int[]> abTest = abTestBuilder.createFor(Ordenator.class).
                withSelector(new SelectorRandom()).
                withMetricRecorder(new MetricRecorderLogger()).
                withImplementations(implementations).
                build();
        return abTest.getProxy();
    }

}
