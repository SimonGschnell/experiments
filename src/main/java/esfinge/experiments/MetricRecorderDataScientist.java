package esfinge.experiments;

import esfinge.experiments.MetricRecorder;
import esfinge.experiments.MetricResult;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MetricRecorderDataScientist implements MetricRecorder {

    private final String path;

    public MetricRecorderDataScientist(String path) {
        this.path = path;
    }

    @Override
    public void write(MetricResult metricResult) {
        try (BufferedWriter writer2 = new BufferedWriter(new FileWriter(path, true), 8192 * 4)) {
            writer2.write(metricResult.plottingResult() + "\n");
        } catch (IOException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        }

    }

}
