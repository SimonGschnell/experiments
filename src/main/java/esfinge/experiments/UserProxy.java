package esfinge.experiments;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class UserProxy<T> implements MethodInterceptor {

    private final T object;
    private final List<String> interceptedMethods = new ArrayList<>();
    private List<Metrics> metrics;
    private Class selector;

    protected UserProxy(T object) {
        this.object = object;
    }

    public void setMetrics(List<Metrics> metrics) {
        this.metrics = metrics;
    }

    public void setSelector(Class selector) {
        this.selector = selector;
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        for (String m : interceptedMethods) {
            if (method.getName().equals(m)) {
                try {
                    for (Metrics metric : metrics) {
                        metric.startCapture(method, selector);
                    }
                } catch (Exception ex) {
                    Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
                }
                T methodResult = (T) (proxy.invoke(object, args));
                try {
                    for (Metrics metric : metrics) {
                        metric.finishCapture(method, selector);
                    }
                } catch (Exception ex) {
                    Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
                }
                return methodResult;
            }
        }
        return proxy.invoke(object, args);
    }

    protected void addInterceptedMethod(String interceptedMethod) {
        interceptedMethods.add(interceptedMethod);
    }
}
