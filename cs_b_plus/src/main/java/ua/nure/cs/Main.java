package ua.nure.cs;


import ua.nure.cs.heap.BinomialHeap;
import ua.nure.cs.tree.BPlusTree;
import ua.nure.cs.util.BTreeUtils;
import ua.nure.cs.util.HeapUtils;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        Map<String, Double> treeMap = new HashMap<>();
        BPlusTree<Integer, Integer> tree;

        for (int i = 0; i < 10; i++) {
            tree = BTreeUtils.generateRandomBPlusTree(1000_000, 0, 100);
            BTreeUtils.viewedNodesCounterForInsert = 0;
            BTreeUtils.testBPlusTree(tree, 200000).forEach((key, value) -> treeMap.merge(key, value, Double::sum));
            System.out.println("treeMap = " + i + " " + tree.getHeight());
        }

        treeMap.forEach((key, value) -> treeMap.computeIfPresent(key, (k, v) -> v / 10));
        System.out.println("treeMap = " + treeMap);

        Map<String, Double> heapMap = new HashMap<>();
        BinomialHeap<Integer> heap;
        for (int i = 0; i < 10; i++) {
            heap = HeapUtils.generateRandomBinomialHeap(10, 0, 100);
            heap.countOfUnionForInsertion = 0;
            HeapUtils.testBinomialHeap(heap, 5).forEach((key, value) -> heapMap.merge(key, value, Double::sum));
        }

        heapMap.forEach((key, value) -> heapMap.computeIfPresent(key, (k, v) -> v / 10));
        System.out.println("heapMap = " + heapMap);
    }
}
