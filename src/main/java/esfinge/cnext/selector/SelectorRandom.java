package esfinge.cnext.selector;

import java.util.Random;

public class SelectorRandom implements Selector {

    @Override
    public Class select(Class[] implementations) {
        int random = (new Random()).nextInt(implementations.length * 100_000);
        return implementations[random / 100_000];
    }

}
