package esfinge.experiments;

import java.lang.reflect.Method;

public class MemoryMetricsGenerator implements Metrics {

    private MetricRecorder metricRecorder;
    private long usedMemoryBefore;
    private Runtime runtime;

    @Override
    public MetricRecorder getMetricRecorder() {
        return metricRecorder;
    }

    @Override
    public void setMetricRecorder(MetricRecorder metricRecorder) {
        this.metricRecorder = metricRecorder;
    }

    @Override
    public void startCapture(Method method, Class selectorClass) throws Exception {
        runtime = Runtime.getRuntime();
        usedMemoryBefore = runtime.totalMemory() - runtime.freeMemory();
    }

    @Override
    public void finishCapture(Method method, Class selectorClass) throws Exception {
        long usedMemoryAfter = runtime.totalMemory() - runtime.freeMemory();
        String result = usedMemoryAfter - usedMemoryBefore + " bytes";
        MetricResult mr = extractMetricResult(method, selectorClass, result);
        metricRecorder.write(mr);
    }

}
