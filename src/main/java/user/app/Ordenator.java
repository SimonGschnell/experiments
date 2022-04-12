package user.app;


import esfinge.cnext.annotations.MemoryMetrics;
import esfinge.cnext.annotations.TimeMetrics;

@TimeMetrics
@MemoryMetrics
public interface Ordenator<T> {

    T sort(T vetor);

}
