package esfinge.experiments;

public class MetricResult {

    private String metricName;
    private String selector;
    private String userClass;
    private String userMethod;
    private String result;

    public MetricResult(String metricName, String selector, String userClass, String userMethod, String result) {
        this.metricName = metricName;
        this.selector = selector;
        this.userClass = userClass;
        this.userMethod = userMethod;
        this.result = result;
    }

    public String getMetricName() {
        return metricName;
    }

    public void setMetricName(String metricName) {
        this.metricName = metricName;
    }

    public String getSelector() {
        return selector;
    }

    public void setSelector(String selector) {
        this.selector = selector;
    }

    public String getUserClass() {
        return userClass;
    }

    public void setUserClass(String userClass) {
        this.userClass = userClass;
    }

    public String getUserMethod() {
        return userMethod;
    }

    public void setUserMethod(String userMethod) {
        this.userMethod = userMethod;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "MetricResult{" + "metricName=" + metricName + ", selector=" + selector + ", userClass=" + userClass + ", userMethod=" + userMethod + ", result=" + result + '}';
    }

    public String plottingResult() {
        return userMethod + " " + result;
    }

}
