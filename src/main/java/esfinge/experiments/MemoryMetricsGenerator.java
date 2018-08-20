package esfinge.experiments;

import java.lang.reflect.Method;

public class MemoryMetricsGenerator implements Metrics {

    private long usedMemoryBefore;
    private Runtime runtime;

    @Override
    public void startCapture(Method method) {
        runtime = Runtime.getRuntime();
        usedMemoryBefore = runtime.totalMemory() - runtime.freeMemory();
        //result = method.getName() + " => Used memory before: " + usedMemoryBefore / 1024.0d + " KB. ";
    }

    @Override
    public void finishCapture(Method method) {
        long usedMemoryAfter = runtime.totalMemory() - runtime.freeMemory();
        //result += "Memory increased: " + (usedMemoryAfter - usedMemoryBefore) / 1024.0d + " KB.";
    }

}
