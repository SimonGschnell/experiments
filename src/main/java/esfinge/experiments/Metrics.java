package esfinge.experiments;

import java.lang.reflect.Method;

public interface Metrics {

    Object getMetrics(ABTestUser userExperiment, Method method) throws Exception;

}
