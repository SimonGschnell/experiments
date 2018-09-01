package esfinge.experiments;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import net.sf.cglib.proxy.Enhancer;

public class ABTestBuilder<T, R> {

    private final ABTest<T, R> abTest = new ABTest<>();
    private Class<T> userClass;
    private MetricRecorder metricRecorder;

    public ABTestBuilder() {
    }

    public ABTestBuilder createFor(Class<T> clazz) {
        this.userClass = clazz;
        return this;
    }

    public ABTestBuilder withSelector(Selector selector) {
        abTest.setSelector(selector);
        return this;
    }

    public ABTestBuilder withMetricRecorder(MetricRecorder metricRecorder) {
        this.metricRecorder = metricRecorder;
        return this;
    }

    public ABTestBuilder withATest(String methodName, Object... args) {
        abTest.setaTestMethodName(methodName);
        abTest.setaTestArguments(args);
        return this;
    }

    public ABTestBuilder withBTest(String methodName, Object... args) {
        abTest.setbTestMethodName(methodName);
        abTest.setbTestArguments(args);
        return this;
    }

    public ABTest<T, R> build() throws Exception {

        if (abTest.getSelector() == null) {
            abTest.setSelector(new SelectorRandom()); //default
        }

        if (metricRecorder == null) {
            metricRecorder = new MetricRecorderLogger(); //default
        }

        if (userClass != null) {
            List<Metrics> metrics = new ArrayList<>();
            for (Annotation an : userClass.getAnnotations()) {
                Class<?> anType = an.annotationType();
                if (anType.isAnnotationPresent(MetricsGenerator.class)) {
                    MetricsGenerator fi = anType.getAnnotation(MetricsGenerator.class);
                    Class<? extends Metrics> c = fi.value();
                    Metrics metric = c.newInstance();
                    metric.setMetricRecorder(metricRecorder);
                    metrics.add(metric);
                }
            }
            UserProxy<T> userProxy = new UserProxy(userClass.newInstance());
            userProxy.setMetrics(metrics);
            userProxy.setSelector(abTest.getSelector().getClass());

            if (abTest.getaTestMethodName() != null) {
                userProxy.addInterceptedMethod(abTest.getaTestMethodName());
            }
            if (abTest.getbTestMethodName() != null) {
                userProxy.addInterceptedMethod(abTest.getbTestMethodName());
            }
            T proxy = (T) Enhancer.create(userClass, userProxy);
            abTest.setUserProxy(proxy);
        }
        return abTest;
    }

}
