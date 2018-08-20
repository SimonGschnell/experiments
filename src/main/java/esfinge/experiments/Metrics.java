package esfinge.experiments;

import java.lang.reflect.Method;

public interface Metrics<T> {

    T executeWithMetrics(ABTestUser userExperiment, Method method) throws Exception;

}
