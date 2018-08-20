package esfinge.experiments;

import java.lang.reflect.Method;

public interface Metrics {

    void startCapture(Method method);

    void finishCapture(Method method);

}
