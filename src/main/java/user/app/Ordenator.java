package user.app;


import esfinge.cnext.metric.MemoryMetrics;
import esfinge.cnext.metric.TimeMetrics;

@TimeMetrics
@MemoryMetrics
public interface Ordenator<T> {

    T sort(T vetor);

}
