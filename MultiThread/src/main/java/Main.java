
import java.util.ArrayList;
import java.util.Random;

public class Main {

    public static int[] createRandomArray(int size, int min, int max) {
        if (size <= 0) {
            throw new IllegalArgumentException("Size must be positive");
        }
        Random random = new Random();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt(max - min + 1) + min;
        }
        return arr;
    }

    public static void main(String[] args) throws InterruptedException {
        int arrsize = (int) 1e8;
        int min = 0;
        int max = 10;
        int num_threads = 4;
        int chunksize = arrsize / num_threads;
        int datarange = max - min + 1;
        int[] arr = createRandomArray(arrsize, min, max);
        int[] histogram = new int[datarange];

        ArrayList<Thread> created_threads = new ArrayList<>();
        ArrayList<HistogramCalculator> created_runnables = new ArrayList<>();

        for (int i = 0; i < num_threads; i++) {
            int start = i * chunksize;
            int end = (i == num_threads - 1) ? arrsize : (i + 1) * chunksize;

            HistogramCalculator calculator = new HistogramCalculator(arr, start, end, datarange);
            created_runnables.add(calculator);

            Thread thread = new Thread(calculator);
            created_threads.add(thread);
        }

        long startTime = System.nanoTime();

        for (Thread t : created_threads) {
            t.start();
        }

        for (Thread t : created_threads) {
            t.join();
        }

        long endTime = System.nanoTime();
        long durationNs = endTime - startTime;
        double durationMs = durationNs / 1_000_000.0;

        for (HistogramCalculator r : created_runnables) {
            for (int i = min; i <= max; i++) {
                histogram[i] += r.getHistogram()[i];
            }
        }

        System.out.println("Histogram: ");
        long total = 0;
        for (int i = min; i <= max; i++) {
            System.out.print(histogram[i] + " ");
            total += histogram[i];
        }

        System.out.println();
        System.out.println("Total: " + total + " Data Size:" + arrsize);
        System.out.println("Thread execution time:" + durationMs + " ms");
    }
}