package esfinge.experiments;

import java.lang.reflect.Method;
import java.time.Duration;
import java.time.Instant;

public class PerformanceMetricsGenerator implements Metrics {

    @Override
    public Object getMetrics(ABTest userExperiment, Method method) throws Exception {
        Instant start = Instant.now();
        Object methodResult = method.invoke(userExperiment);
        Instant end = Instant.now();
        Duration duration = Duration.between(start, end);
        double time = duration.toMillis() / 1000.0d;
        String metrics = method.getName() + " finalized in " + time + " seconds. Resultado: " + methodResult;
        return metrics;
    }

}
