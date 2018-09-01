package esfinge.experiments;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MetricRecorderFile implements MetricRecorder {

    private final String path;

    public MetricRecorderFile(String path) {
        this.path = path;
    }

    @Override
    public void write(MetricResult metricResult) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneId.systemDefault());
        String outDate = dtf.format(Instant.now());
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path, true), 8192 * 4)) {
            writer.write(outDate + " - " + metricResult + "\n");
        } catch (IOException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }
}
