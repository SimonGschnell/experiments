package esfinge.experiments;

import java.util.Random;

public class SelectorRandom implements Selector {

    @Override
    public String select(String aTest, String bTest) {
        if ((new Random()).nextInt(10) < 5) {
            return aTest;
        } else {
            return bTest;
        }
    }

}
