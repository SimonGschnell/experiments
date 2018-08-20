package esfinge.experiments;

import java.lang.reflect.Method;

public class MemoryMetricsGenerator implements Metrics {

    private long usedMemoryBefore;
    private Runtime runtime;

    @Override
    public void startCapture(Method method, Class abTestSelectorClass) {
        runtime = Runtime.getRuntime();
        usedMemoryBefore = runtime.totalMemory() - runtime.freeMemory();
    }

    @Override
    public void finishCapture(Method method, Class abTestSelectorClass) {
        long usedMemoryAfter = runtime.totalMemory() - runtime.freeMemory();
        String result = usedMemoryAfter - usedMemoryBefore + " bytes";
        MetricResult mr = extractMetricResult(method, abTestSelectorClass, result);
        MetricRecording.getInstance().write(mr);
    }

}
