package esfinge.experiments;

import java.lang.reflect.Method;
import java.time.Duration;
import java.time.Instant;

public class PerformanceMetricsGenerator implements Metrics {

    private Instant start;

    @Override
    public void startCapture(Method method) {
        start = Instant.now();
    }

    @Override
    public void finishCapture(Method method) {
        Instant end = Instant.now();
        Duration duration = Duration.between(start, end);
        double time = duration.toMillis() / 1000.0d;
        String metrics = method.getName() + " => finalized in " + time + " seconds.";
    }
}
