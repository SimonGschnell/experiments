package esfinge.experiments;

public class ABTestSelectPercentage<T> implements ABTest<T> {

    private final ABTestUser userExperiment;
    private final int percentageA;
    private final int totalNumOfUsers;
    private int currentNumOfUsers;

    public ABTestSelectPercentage(ABTestUser userExperiment, int percentageA, int totalNumOfUsers) {
        this.userExperiment = userExperiment;
        this.percentageA = percentageA;
        this.totalNumOfUsers = totalNumOfUsers;
        this.currentNumOfUsers = 0;
    }

    @Override
    public ABTestUser getUserExperiment() {
        return userExperiment;
    }

    @Override
    public T execute() throws Exception {
        if (currentNumOfUsers < ((float) percentageA / 100) * totalNumOfUsers) {
            currentNumOfUsers++;
            return aTest();
        } else {
            currentNumOfUsers++;
            return bTest();
        }
    }

}
