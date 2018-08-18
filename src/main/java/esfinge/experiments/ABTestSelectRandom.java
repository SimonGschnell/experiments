package esfinge.experiments;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Random;

public class ABTestSelectRandom implements ABTestMetrics {

    private final ABTest userExperiment;

    public ABTestSelectRandom(ABTest userExperiment) {
        this.userExperiment = userExperiment;
    }

    @Override
    public Object selectUsers() throws Exception {
        if ((new Random()).nextInt(10) < 5) {
            return aTestMetrics();
        } else {
            return bTestMetrics();
        }
    }

    @Override
    public Object aTestMetrics() throws Exception {
        return testMetrics("aTest");
    }

    @Override
    public Object bTestMetrics() throws Exception {
        return testMetrics("bTest");
    }

    private Object testMetrics(String methodName) throws Exception {
        Class<? extends ABTest> clazz = userExperiment.getClass();
        for (Annotation an : clazz.getAnnotations()) {
            Class<?> anType = an.annotationType();
            if (anType.isAnnotationPresent(MetricsGenerator.class)) {
                MetricsGenerator fi = anType.getAnnotation(MetricsGenerator.class);
                Class<? extends Metrics> c = fi.value();
                Metrics m = c.newInstance();
                Method method = clazz.getMethod(methodName);
                return m.getMetrics(userExperiment, method);
            }
        }
        return "This experiment has no metrics.";
    }

}
