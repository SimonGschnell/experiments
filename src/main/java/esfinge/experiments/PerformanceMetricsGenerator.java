package esfinge.experiments;

import java.lang.reflect.Method;
import java.time.Duration;
import java.time.Instant;

public class PerformanceMetricsGenerator implements Metrics {

    private Instant startExecution;

    @Override
    public void startCapture(Method method, Class abTestSelectorClass) {
        startExecution = Instant.now();
    }

    @Override
    public void finishCapture(Method method, Class abTestSelectorClass) {
        Duration duration = Duration.between(startExecution, Instant.now());
        String result = duration.toMillis() / 1000.0d + " seconds";
        MetricResult mr = extractMetricResult(method, abTestSelectorClass, result);
        MetricRecording.getInstance().write(mr);
    }

}
