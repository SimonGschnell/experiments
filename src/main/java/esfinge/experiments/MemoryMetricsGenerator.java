package esfinge.experiments;

public class MemoryMetricsGenerator implements Metrics {

    private long usedMemoryBefore;
    private String resultStr;
    private Runtime runtime;

    @Override
    public void preInvoke(String methodName) {
        runtime = Runtime.getRuntime();
        usedMemoryBefore = runtime.totalMemory() - runtime.freeMemory();
        resultStr = methodName + " => Used memory before: " + usedMemoryBefore / 1000.0d + " KB. ";
    }

    @Override
    public void postInvoke(String methodName) {
        long usedMemoryAfter = runtime.totalMemory() - runtime.freeMemory();
        resultStr += "Memory increased: " + (usedMemoryAfter - usedMemoryBefore) / 1000.0d + " KB.";
        System.out.println(resultStr);
    }

}
