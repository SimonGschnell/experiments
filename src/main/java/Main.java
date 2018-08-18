
import esfinge.experiments.ABTestSelectRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) {

        PerformanceExperiment userExperiment = new PerformanceExperiment();

        ABTestSelectRandom abTest = new ABTestSelectRandom(userExperiment);

        simulateCalls(abTest);
    }

    public static void simulateCalls(ABTestSelectRandom abTest) {
        for (int i = 0; i < 10; i++) {
            try {
                System.out.println(abTest.selectUsers());
            } catch (Exception ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
