package esfinge.cnext.metric;

public class MetricResult {

    private String metric;
    private String selector;
    private String implementation;
    private String method;
    private String result;

    public MetricResult(String metric, String selector, String implementation, String method, String result) {
        this.metric = metric;
        this.selector = selector;
        this.implementation = implementation;
        this.method = method;
        this.result = result;
    }

    public String getMetric() {
        return metric;
    }

    public void setMetric(String metric) {
        this.metric = metric;
    }

    public String getSelector() {
        return selector;
    }

    public void setSelector(String selector) {
        this.selector = selector;
    }

    public String getImplementation() {
        return implementation;
    }

    public void setImplementation(String implementation) {
        this.implementation = implementation;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "MetricResult{" + "metric=" + metric + ", selector=" + selector + ", implementation=" + implementation + ", method=" + method + ", result=" + result + '}';
    }

}
