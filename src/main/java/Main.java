
import esfinge.experiments.ABTestSelectRandom;

public class Main {

    public static void main(String[] args) throws Exception {
        PerformanceExperiment experiment = new PerformanceExperiment();
        ABTestSelectRandom abTest = new ABTestSelectRandom(experiment);
        simulateCalls(abTest);
    }

    public static void simulateCalls(ABTestSelectRandom abTest) throws Exception {
        for (int i = 0; i < 10; i++) {
            //Resultado da execução randômica dos métodos aTest e bTest
            Object result = abTest.execute();
            System.out.println(result);
        }
    }

}
