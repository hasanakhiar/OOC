
public class HistogramCalculator implements Runnable {
    private int[] sharedData;
    private int startIndex;
    private int endIndex;
    private int[] partialHistogram;

    public HistogramCalculator(int[] sharedData, int startIndex, int endIndex, int dataRange) {
        this.sharedData = sharedData;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.partialHistogram = new int[dataRange];
    }

    @Override
    public void run() {
        for (int i = startIndex; i < endIndex; i++) {
            int value = sharedData[i];
            partialHistogram[value]++;
        }
    }

    public int[] getHistogram() {
        return partialHistogram;
    }
}