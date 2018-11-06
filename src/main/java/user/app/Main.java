package user.app;

import java.util.Random;

public class Main {

    public static void main(String[] args) throws Exception {

        Ordenator<int[]> ordenator = OrdenatorFactory.create(OrdenatorInsertionSort.class, OrdenatorHeapSort.class);

        int[] data = inputData();

        for (int i = 0; i < 10; i++) {
            int[] dataSorted = ordenator.sort(data.clone());
        }
    }

    private static int[] inputData() {
        Random random = new Random();
        int[] initialArray = new int[200_000];
        for (int i = 0; i < initialArray.length; i++) {
            initialArray[i] = random.nextInt();
        }
        return initialArray;
    }
}
