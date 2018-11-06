package esfinge.cnext;

public class ABTest<T, R> {

    private T proxy;

    public T getProxy() {
        return proxy;
    }

    protected void setProxy(T proxy) {
        this.proxy = proxy;
    }

}
