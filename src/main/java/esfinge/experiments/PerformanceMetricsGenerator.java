package esfinge.experiments;

import java.time.Duration;
import java.time.Instant;

public class PerformanceMetricsGenerator implements Metrics {

    private Instant start;

    @Override
    public void preInvoke(String methodName) {
        start = Instant.now();
    }

    @Override
    public void postInvoke(String methodName) {
        Instant end = Instant.now();
        Duration duration = Duration.between(start, end);
        double time = duration.toMillis() / 1000.0d;
        String metrics = methodName + " => finalized in " + time + " seconds.";
        System.out.println(metrics);
    }
}
