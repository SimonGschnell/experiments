package esfinge.cnext.annotations;

import esfinge.cnext.factories.Metrics;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
public @interface MetricsGenerator {

    Class<? extends Metrics> value();
}
