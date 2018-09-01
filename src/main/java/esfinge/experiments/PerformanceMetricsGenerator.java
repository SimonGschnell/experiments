package esfinge.experiments;

import java.lang.reflect.Method;
import java.time.Duration;
import java.time.Instant;

public class PerformanceMetricsGenerator implements Metrics {

    private MetricRecorder metricRecorder;
    private Instant startExecution;

    @Override
    public MetricRecorder getMetricRecorder() {
        return metricRecorder;
    }

    @Override
    public void setMetricRecorder(MetricRecorder metricRecorder) {
        this.metricRecorder = metricRecorder;
    }

    @Override
    public void startCapture(Method method, Class selector) throws Exception {
        startExecution = Instant.now();
    }

    @Override
    public void finishCapture(Method method, Class selector) throws Exception {
        Duration duration = Duration.between(startExecution, Instant.now());
        String result = duration.toMillis() / 1000.0d + " seconds";
        MetricResult mr = extractMetricResult(method, selector, result);
        metricRecorder.write(mr);
    }

}
