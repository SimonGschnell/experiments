package esfinge.cnext.metric;

import java.lang.reflect.Method;
import java.time.Duration;
import java.time.Instant;

public class TimeMetricsGenerator implements Metrics {

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
    public void startRecording(Method method) throws Exception {
        startExecution = Instant.now();
    }

    @Override
    public void finishRecording(Method method, String selectorName, String implementation) throws Exception {
        Duration duration = Duration.between(startExecution, Instant.now());
        String result = duration.toMillis() / 1000.0d + " seconds";
        MetricResult mr = extractMetricResult(method, selectorName, implementation, result);
        metricRecorder.save(mr);
    }

}
