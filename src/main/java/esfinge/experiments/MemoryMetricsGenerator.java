package esfinge.experiments;

import java.lang.reflect.Method;

public class MemoryMetricsGenerator<T> implements Metrics<T> {

    @Override
    public T executeWithMetrics(ABTestUser userExperiment, Method method) throws Exception {
        Runtime runtime = Runtime.getRuntime();
        long usedMemoryBefore = runtime.totalMemory() - runtime.freeMemory();
        String mem = method.getName() + " => Used memory before: " + usedMemoryBefore / 1000.0d + " KB. ";

        T methodResult = (T) (method.invoke(userExperiment));

        long usedMemoryAfter = runtime.totalMemory() - runtime.freeMemory();
        mem += "Memory increased: " + (usedMemoryAfter - usedMemoryBefore) / 1000.0d + " KB.";
        System.out.println(mem);

        return methodResult;
    }

}
