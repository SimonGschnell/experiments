
import esfinge.experiments.ABTestSelectPercentage;
import esfinge.experiments.ABTestSelectRandom;
import java.util.Random;

public class Main {

    public static void main(String[] args) throws Exception {
        simulateCalls();
    }

    public static void simulateCalls() throws Exception {

        SortingExperiment userExperiment = new SortingExperiment(createSimulateInput());

        System.out.println("=> ABTestSelectRandom");
        ABTestSelectRandom<int[]> abTestSelectRandom = new ABTestSelectRandom(userExperiment);
        for (int i = 0; i < 10; i++) {
            int[] result = abTestSelectRandom.execute();
        }

        System.out.println("\n=> ABTestSelectPercentage");
        ABTestSelectPercentage<int[]> abTestSelectPercentage = new ABTestSelectPercentage(userExperiment, 20, 10);
        for (int i = 0; i < 10; i++) {
            int[] result = abTestSelectPercentage.execute();
        }
    }

    private static int[] createSimulateInput() {
        int[] initialArray = new int[200_000];
        for (int i = 0; i < initialArray.length; i++) {
            initialArray[i] = (new Random()).nextInt();
        }
        return initialArray;
    }

}
