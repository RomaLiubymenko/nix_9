package ua.nure.cs.util;

import ua.nure.cs.tree.BPlusTree;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class BTreeUtils {

    private static final Random random = new Random();

    public static int viewedNodesCounterForInsert = 0;
    public static int viewedNodesCounterForSearch = 0;
    public static int viewedNodesCounterForDelete = 0;

    private BTreeUtils() {
    }

    public static BPlusTree<Integer, Integer> generateRandomBPlusTree(int length, int minElement, int maxElement) {
        BPlusTree<Integer, Integer> tree = new BPlusTree<>();
        for (int i = 0; i < length; i++) {
            Integer key = random.nextInt((maxElement - minElement) + minElement);
            Integer value = random.nextInt((maxElement - minElement) + minElement);
            tree.insert(key, value);
        }
        return tree;
    }

    public static Map<String, Double> testBPlusTree(BPlusTree<Integer, Integer> tree, int operationsCount) {
        long totalInsertionTime = 0;
        long totalDeletionTime = 0;
        long totalSearchingTime = 0;

        int counterForSearch = 0;
        int counterForInsertion = 0;
        int counterForDeletion = 0;
        for (int i = 0; i < operationsCount; i++) {
            int randomNumberOperation = random.nextInt((3 - 1) + 1);
            if (randomNumberOperation == 0) {
                long startInsert = System.nanoTime();
                tree.insert(50, 50);
                totalInsertionTime += System.nanoTime() - startInsert;
                counterForInsertion++;
            } else if (randomNumberOperation == 1) {
                long startDeletion = System.nanoTime();
                tree.delete(50);
                totalDeletionTime += System.nanoTime() - startDeletion;
                counterForDeletion++;
            } else {
                long startSearching = System.nanoTime();
                tree.search(50);
                totalSearchingTime += System.nanoTime() - startSearching;
                counterForSearch++;
            }
        }

        Map<String, Double> map = new HashMap<>();

        map.put("Normed viewedNodesCounterForInsert", (double) viewedNodesCounterForInsert / counterForInsertion);
        map.put("Normed viewedNodesCounterForDelete", (double) viewedNodesCounterForDelete / counterForDeletion);
        map.put("Normed viewedNodesCounterForSearch", (double) viewedNodesCounterForSearch / counterForSearch);

        map.put("Normed time insertion", (double) totalInsertionTime / counterForInsertion);
        map.put("Normed time deletion", (double) totalDeletionTime / counterForDeletion);
        map.put("Normed time searching", (double) totalSearchingTime / counterForSearch);

        return map;
    }


}
