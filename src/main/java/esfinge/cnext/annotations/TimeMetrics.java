package esfinge.cnext.annotations;

import esfinge.cnext.annotations.MetricsGenerator;
import esfinge.cnext.metric.TimeMetricsGenerator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@MetricsGenerator(TimeMetricsGenerator.class)
public @interface TimeMetrics {

}
