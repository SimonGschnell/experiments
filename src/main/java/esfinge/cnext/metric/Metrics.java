package esfinge.cnext.metric;

import java.lang.reflect.Method;

public interface Metrics {

    MetricRecorder getMetricRecorder();

    void setMetricRecorder(MetricRecorder metricRecorder);

    void startRecording(Method method) throws Exception;

    void finishRecording(Method method, String selectorName, String implementation) throws Exception;

    default MetricResult extractMetricResult(Method method, String selectorName, String implementation, String result) {
        String metric = getClass().getSimpleName().replace("Generator", "");
        return new MetricResult(metric, selectorName, implementation, method.getName(), result);
    }

}
