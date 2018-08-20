package esfinge.experiments;

public interface Metrics {

    void preInvoke(String methodName);

    void postInvoke(String methodName);

}
