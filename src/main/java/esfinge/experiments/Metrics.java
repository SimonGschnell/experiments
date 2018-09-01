package esfinge.experiments;

import java.lang.reflect.Method;

public interface Metrics {

    MetricRecorder getMetricRecorder();

    void setMetricRecorder(MetricRecorder metricRecorder);

    void startCapture(Method method, Class selectorClas) throws Exception;

    void finishCapture(Method method, Class selectorClass) throws Exception;

    default MetricResult extractMetricResult(Method method, Class selectorClass, String result) {
        String metricName = getClass().getSimpleName().replace("Generator", "");
        String selector = selectorClass.getSimpleName();
        String userClass = method.getDeclaringClass().getName();
        String userMethod = method.getName();
        return new MetricResult(metricName, selector, userClass, userMethod, result);
    }

}
