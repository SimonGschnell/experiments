package esfinge.experiments;

import java.util.Random;

public class ABTestSelectRandom<T> implements ABTest<T> {

    private final ABTestUser userExperiment;

    public ABTestSelectRandom(ABTestUser userExperiment) {
        this.userExperiment = userExperiment;
    }

    @Override
    public ABTestUser getUserExperiment() {
        return userExperiment;
    }

    @Override
    public T execute() throws Exception {
        if ((new Random()).nextInt(10) < 5) {
            return aTest();
        } else {
            return bTest();
        }
    }

}
