package esfinge.experiments;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Random;

public class ABTestSelectRandom implements ABTest {

    private final ABTestUser userExperiment;

    public ABTestSelectRandom(ABTestUser userExperiment) {
        this.userExperiment = userExperiment;
    }

    @Override
    public Object execute() throws Exception {
        //TODO: criar um ponto de extensão para selecionar usuários
        if ((new Random()).nextInt(10) < 5) {
            return aTest();
        } else {
            return bTest();
        }
    }

    @Override
    public Object aTest() throws Exception {
        return executeWithMetrics("aTest");
    }

    @Override
    public Object bTest() throws Exception {
        return executeWithMetrics("bTest");
    }

    private Object executeWithMetrics(String methodName) throws Exception {
        Class<? extends ABTestUser> clazz = userExperiment.getClass();
        Method method = clazz.getMethod(methodName);
        for (Annotation an : clazz.getAnnotations()) {
            Class<?> anType = an.annotationType();
            if (anType.isAnnotationPresent(MetricsGenerator.class)) {
                MetricsGenerator fi = anType.getAnnotation(MetricsGenerator.class);
                Class<? extends Metrics> c = fi.value();
                Metrics m = c.newInstance();
                return m.getMetrics(userExperiment, method);

            }
        }
        System.out.println("The " + clazz.getName() + " class has no metrics generator.");
        return method.invoke(userExperiment);

    }

}
