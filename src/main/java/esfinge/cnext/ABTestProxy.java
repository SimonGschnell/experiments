package esfinge.cnext;

import esfinge.cnext.metric.Metrics;
import esfinge.cnext.selector.Selector;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ABTestProxy<R> implements InvocationHandler {

    private List<Metrics> metrics;
    private Selector selector;
    private Class[] implementations;

    public ABTestProxy() {
    }

    protected List<Metrics> getMetrics() {
        return metrics;
    }

    protected void setMetrics(List<Metrics> metrics) {
        this.metrics = metrics;
    }

    protected Selector getSelector() {
        return selector;
    }

    protected void setSelector(Selector selector) {
        this.selector = selector;
    }

    protected Class[] getImplementations() {
        return implementations.clone();
    }

    protected void setImplementations(Class[] implementations) {
        this.implementations = implementations;
    }

    @Override
    public R invoke(Object proxy, Method method, Object[] args) throws Throwable {

        Class implementation = selector.select(implementations);

        try {
            for (Metrics metric : metrics) {
                metric.startRecording(method);
            }
        } catch (Exception ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        }
        R methodResult = (R) (method.invoke(implementation.newInstance(), args));
        try {
            for (Metrics metric : metrics) {
                metric.finishRecording(method, selector.getClass().getSimpleName(), implementation.getSimpleName());
            }
        } catch (Exception ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        }
        return methodResult;
    }
}
