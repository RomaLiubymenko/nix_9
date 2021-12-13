package ua.nure.cs.util;

import ua.com.alevel.tree.BinaryTree;
import ua.nure.cs.tree.AVLTree;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class TreeUtils {

    private static final Random random = new Random();

    private TreeUtils() {
    }

    public static AVLTree generateRandomAVLTree(int length, int minElement, int maxElement) {
        AVLTree avlTree = new AVLTree();
        for (int i = 0; i < length; i++) {
            avlTree.insert(random.nextInt((maxElement - minElement) + minElement));
        }
        return avlTree;
    }

    public static BinaryTree generateRandomBinaryTree(int length, int minElement, int maxElement) {
        BinaryTree binaryTree = new BinaryTree();
        for (int i = 0; i < length; i++) {
            binaryTree.addNode(random.nextInt((maxElement - minElement) + minElement));
        }
        return binaryTree;
    }

    public static Map<String, Float> testAVLTree(AVLTree avlTree, int operationsCount) {
        int counterForSearch = 0;
        int counterForInsertion = 0;
        int counterForDeletion = 0;
        for (int i = 0; i < operationsCount; i++) {
            int randomNumberOperation = random.nextInt((3 - 1) + 1);
            if (randomNumberOperation == 0) {
                avlTree.insert(50);
                counterForInsertion++;
            } else if (randomNumberOperation == 1) {
                avlTree.delete(50);
                counterForDeletion++;
            } else {
                avlTree.search(50);
                counterForSearch++;
            }
        }
        Map<String, Float> map = new HashMap<>();
        map.put("Normed viewedNodesCounterForSearch", (float) avlTree.viewedNodesCounterForSearch / counterForSearch);
        map.put("Normed viewedNodesCounterForInsert", (float) avlTree.viewedNodesCounterForInsert / counterForInsertion);
        map.put("Normed viewedNodesCounterForDelete", (float) avlTree.viewedNodesCounterForDelete / counterForDeletion);
        map.put("Normed rotationCounterForInsert", (float) avlTree.rotationCounterForInsert / counterForInsertion);
        map.put("Normed rotationCounterForDelete", (float) avlTree.rotationCounterForDelete / counterForDeletion);
        return map;
    }

    public static Map<String, Float> testBinaryTree(BinaryTree binaryTree, int operationsCount) {
        int counterForSearch = 0;
        int counterForInsertion = 0;
        int counterForDeletion = 0;
        for (int i = 0; i < operationsCount; i++) {
            int randomNumberOperation = random.nextInt((3 - 1) + 1);
            if (randomNumberOperation == 0) {
                binaryTree.addNode(50);
                counterForInsertion++;
            } else if (randomNumberOperation == 1) {
                binaryTree.deleteNode(50, binaryTree.getRoot());
                counterForDeletion++;
            } else {
                binaryTree.searchNode(50, binaryTree.getRoot());
                counterForSearch++;
            }
        }
        Map<String, Float> map = new HashMap<>();
        map.put("Normed viewedNodesCounterForSearch", (float) binaryTree.viewedNodesCounterForSearch / counterForSearch);
        map.put("Normed viewedNodesCounterForInsert", (float) binaryTree.viewedNodesCounterForInsert / counterForInsertion);
        map.put("Normed viewedNodesCounterForDelete", (float) binaryTree.viewedNodesCounterForDelete / counterForDeletion);
        return map;
    }
}
