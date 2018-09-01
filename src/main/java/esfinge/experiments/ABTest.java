package esfinge.experiments;

import java.lang.reflect.Method;

public class ABTest<T, R> {

    private T userProxy;
    private Selector selector;
    String aTestMethodName;
    String bTestMethodName;
    private Object[] aTestArguments;
    private Object[] bTestArguments;

    public T getUserProxy() {
        return userProxy;
    }

    public void setUserProxy(T userProxy) {
        this.userProxy = userProxy;
    }

    public Selector getSelector() {
        return selector;
    }

    public void setSelector(Selector selector) {
        this.selector = selector;
    }

    public String getaTestMethodName() {
        return aTestMethodName;
    }

    public void setaTestMethodName(String aTestMethodName) {
        this.aTestMethodName = aTestMethodName;
    }

    public String getbTestMethodName() {
        return bTestMethodName;
    }

    public void setbTestMethodName(String bTestMethodName) {
        this.bTestMethodName = bTestMethodName;
    }

    public Object[] getaTestArguments() {
        return aTestArguments;
    }

    public void setaTestArguments(Object[] aTestArguments) {
        this.aTestArguments = aTestArguments;
    }

    public Object[] getbTestArguments() {
        return bTestArguments;
    }

    public void setbTestArguments(Object[] bTestArguments) {
        this.bTestArguments = bTestArguments;
    }

    public R run() throws Exception {
        R result = null;

        String selectedMethod = selector.select(aTestMethodName, bTestMethodName);

        if (selectedMethod != null) {
            Object[] arguments = selectedMethod.equals(aTestMethodName) ? aTestArguments : bTestArguments;
            if (userProxy != null) {
                Method[] methods = userProxy.getClass().getMethods();
                for (Method m : methods) {
                    if (m.getName().equals(selectedMethod)) {
                        result = (R) m.invoke(userProxy, arguments);
                        break;
                    }
                }
            }
        }
        return result;
    }
}
