package esfinge.cnext.metric;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MemoryMetricsGenerator implements Metrics {

    private MetricRecorder metricRecorder;
    private long usedMemoryBefore;

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
        try {
            usedMemoryBefore = getSettledUsedMemory();
        } catch (InterruptedException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void finishRecording(Method method, String selectorName, String implementation) throws Exception {
        long usedMemoryAfter = 0;
        try {
            usedMemoryAfter = getSettledUsedMemory();
        } catch (InterruptedException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        }
        String result = usedMemoryAfter - usedMemoryBefore + " bytes";
        MetricResult mr = extractMetricResult(method, selectorName, implementation, result);
        metricRecorder.save(mr);
    }

    private long getGcCount() {
        long sum = 0;
        for (GarbageCollectorMXBean b : ManagementFactory.getGarbageCollectorMXBeans()) {
            long count = b.getCollectionCount();
            if (count != -1) {
                sum += count;
            }
        }
        return sum;
    }

    private long getReallyUsedMemory() {
        long before = getGcCount();
        do {
            System.gc();
        } while (getGcCount() == before);
        return getCurrentlyUsedMemory();
    }

    private long getSettledUsedMemory() throws InterruptedException {
        long m;
        long m2 = getReallyUsedMemory();
        do {
            Thread.sleep(567);
            m = m2;
            m2 = getReallyUsedMemory();
        } while (m2 < getReallyUsedMemory());
        return m;
    }

    private long getCurrentlyUsedMemory() {
        return ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().getUsed() + ManagementFactory.getMemoryMXBean().getNonHeapMemoryUsage().getUsed();
    }

}
