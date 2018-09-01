package esfinge.experiments;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class MetricRecorderLogger implements MetricRecorder {

    public MetricRecorderLogger() {
    }

    @Override
    public void write(MetricResult metricResult) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneId.systemDefault());
        String outDate = dtf.format(Instant.now());
        System.out.println(outDate + " - " + metricResult);
    }
}
