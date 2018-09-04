package esfinge.experiments;

public class SelectorPercentage implements Selector {

    private final int percentageA;
    private final int totalNumOfUsers;
    private int currentNumOfUsers;

    public SelectorPercentage(int percentageA, int totalNumOfUsers) {
        this.percentageA = percentageA;
        this.totalNumOfUsers = totalNumOfUsers;
    }

    @Override
    public String select(String aTest, String bTest) {
        if (currentNumOfUsers < ((float) percentageA / 100) * totalNumOfUsers) {
            currentNumOfUsers++;
            return aTest;
        } else {
            currentNumOfUsers++;
            return bTest;
        }
    }

}
