package esfinge.cnext.factories;

import esfinge.cnext.metric.MetricResult;

public interface MetricRecorder {

    void save(MetricResult metricResult);
}
