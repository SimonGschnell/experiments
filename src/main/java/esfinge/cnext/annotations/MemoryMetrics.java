package esfinge.cnext.annotations;

import esfinge.cnext.metric.MemoryMetricsGenerator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@MetricsGenerator(MemoryMetricsGenerator.class)
public @interface MemoryMetrics {

}
