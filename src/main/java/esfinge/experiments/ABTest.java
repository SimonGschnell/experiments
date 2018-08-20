package esfinge.experiments;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

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
        for (Annotation an : clazz.getAnnotations()) {
            Class<?> anType = an.annotationType();
            if (anType.isAnnotationPresent(MetricsGenerator.class)) {
                MetricsGenerator fi = anType.getAnnotation(MetricsGenerator.class);
                Class<? extends Metrics> c = fi.value();
                Metrics<T> m = c.newInstance();
                return m.executeWithMetrics(userExperiment, method);
            }
        }
        System.out.println("The " + clazz.getName() + " class has no metrics generator.");
        return (T) (method.invoke(userExperiment));
    }

}
