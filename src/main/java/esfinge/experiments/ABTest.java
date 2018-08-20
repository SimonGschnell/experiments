package esfinge.experiments;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public interface ABTest<T> {

    ABTestUser getUserExperiment();

    T execute() throws Exception;

    public default T aTest() throws Exception {
        return executeWithMetrics("aTest");
    }

    public default T bTest() throws Exception {
        return executeWithMetrics("bTest");
    }

    public default T executeWithMetrics(String methodName) throws Exception {
        ABTestUser userExperiment = getUserExperiment();
        Class<? extends ABTestUser> clazz = userExperiment.getClass();
        Method method = clazz.getMethod(methodName);
        List<Metrics> metrics = new ArrayList<>();

        for (Annotation an : clazz.getAnnotations()) {
            Class<?> anType = an.annotationType();
            if (anType.isAnnotationPresent(MetricsGenerator.class)) {
                MetricsGenerator fi = anType.getAnnotation(MetricsGenerator.class);
                Class<? extends Metrics> c = fi.value();
                metrics.add(c.newInstance());
            }
        }

        metrics.forEach(metric -> metric.startCapture(method));
        T methodResult = (T) (method.invoke(userExperiment));
        metrics.forEach(metric -> metric.finishCapture(method));

        return methodResult;

    }

}
