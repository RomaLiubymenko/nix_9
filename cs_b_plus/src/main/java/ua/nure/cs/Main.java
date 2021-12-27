package ua.nure.cs;


import ua.nure.cs.heap.BinomialHeap;
import ua.nure.cs.tree.BPlusTree;
import ua.nure.cs.util.BTreeUtils;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
//        Map<String, Double> treeMap = new HashMap<>();
//        BPlusTree<Integer, Integer> tree;
//
//        for (int i = 0; i < 10; i++) {
//            tree = BTreeUtils.generateRandomBPlusTree(1000_000, 0, 100);
//            BTreeUtils.testBPlusTree(tree, 200000).forEach((key, value) -> treeMap.merge(key, value, Double::sum));
//            System.out.println("avlTreeHeight = " + i + " " + tree.getHeight());
//        }
//
//        System.out.println("avlTreeMap = " + treeMap);
//        treeMap.forEach((key, value) -> treeMap.computeIfPresent(key, (k, v) -> v / 10));
//        System.out.println("avlTreeMap = " + treeMap);

        BinomialHeap<Integer> heap = new BinomialHeap<>();
        heap.insert(55);
        heap.insert(56);
        heap.insert(577);
        heap.insert(545);
        heap.insert(5);

        heap.print();

    }
}
