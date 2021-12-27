package ua.nure.cs.util;

import ua.nure.cs.heap.BinomialHeap;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class HeapUtils {

    private static final Random random = new Random();

    private HeapUtils() {
    }

    public static BinomialHeap<Integer> generateRandomBinomialHeap(int length, int minElement, int maxElement) {
        BinomialHeap<Integer> heap = new BinomialHeap<>();
        for (int i = 0; i < length; i++) {
            heap.insert(random.nextInt((maxElement - minElement) + minElement));
        }
        return heap;
    }

    public static Map<String, Double> testBinomialHeap(BinomialHeap<Integer> heap, int operationsCount) {
        long totalInsertionTime = 0;
        long totalExtractionTime = 0;

        int counterForInsertion = 0;
        int counterForExtraction = 0;

        for (int i = 0; i < operationsCount; i++) {
            int randomNumberOperation = random.nextInt((2 - 1) + 1);
            if (randomNumberOperation == 0) {
                long startInsert = System.nanoTime();
                heap.insert(50);
                totalInsertionTime += System.nanoTime() - startInsert;
                counterForInsertion++;
            } else {
                long startSearching = System.nanoTime();
                heap.extractMin();
                totalExtractionTime += System.nanoTime() - startSearching;
                counterForExtraction++;
            }
        }

        Map<String, Double> map = new HashMap<>();

        map.put("Normed countOfUnionForInsertion", (double) heap.countOfUnionForInsertion / counterForInsertion);
        map.put("Normed countOfUnionForExtract", (double) heap.countOfUnionForExtract / counterForExtraction);

        map.put("Normed time insertion", (double) totalInsertionTime / counterForInsertion);
        map.put("Normed time extraction", (double) totalExtractionTime / counterForExtraction);

        return map;
    }


}
