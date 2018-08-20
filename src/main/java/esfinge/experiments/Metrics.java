package esfinge.experiments;

import java.lang.reflect.Method;

public interface Metrics {

    void startCapture(Method method, Class abTestSelectorClass);

    void finishCapture(Method method, Class abTestSelectorClass);

    default MetricResult extractMetricResult(Method method, Class abTestSelectorClass, String result) {
        String metricName = getClass().getSimpleName();
        String selector = abTestSelectorClass.getSimpleName();
        String userClass = method.getDeclaringClass().getName();
        String userMethod = method.getName();
        return new MetricResult(metricName, selector, userClass, userMethod, result);
    }

}
