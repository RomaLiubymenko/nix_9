package ua.nure.cs;


import ua.com.alevel.tree.BinaryTree;
import ua.nure.cs.tree.AVLTree;
import ua.nure.cs.util.TreePrinter;
import ua.nure.cs.util.TreeUtils;

import java.util.HashMap;
import java.util.Map;

class Main {

    public static void main(String[] args) {
//        AVLTree avlTree = TreeUtils.generateRandomAVLTree(1000_000, 0, 100);
//        BinaryTree binaryTree = TreeUtils.generateRandomBinaryTree(1000_000, 0, 100);
//
//        System.out.println("avlTreeHeight = " + avlTree.getHeight());
//        System.out.println("binaryTreeHeight = " + (binaryTree.getMaxTreeDepth() - 1));

//        Map<String, Float> avlTreeMap = TreeUtils.testAVLTree(avlTree, 200_000);
//        Map<String, Float> binaryTreeMap = TreeUtils.testBinaryTree(binaryTree, 200_000);

//        TreePrinter.print(avlTree.getRoot());
//        TreePrinter.print(binaryTree.getRoot());

        Map<String, Float> avlTreeMap = new HashMap<>();
        Map<String, Float> binaryTreeMap = new HashMap<>();

        AVLTree avlTree;
        BinaryTree binaryTree;

        for (int i = 0; i < 10; i++) {
            avlTree = TreeUtils.generateRandomAVLTree(1000_000, 0, 100);
            TreeUtils.testAVLTree(avlTree, 200_000).forEach((key, value) -> avlTreeMap.merge(key, value, Float::sum));
            System.out.println("avlTreeHeight = " + i + " " + avlTree.getHeight());
        }

        for (int i = 0; i < 10; i++) {
            binaryTree = TreeUtils.generateRandomBinaryTree(1000_000, 0, 100);
            TreeUtils.testBinaryTree(binaryTree, 200_000).forEach((key, value) -> binaryTreeMap.merge(key, value, Float::sum));
            System.out.println("binaryTreeHeight = " + i + " " + (binaryTree.getMaxTreeDepth() - 1));
        }

        System.out.println("avlTreeMap = " + avlTreeMap);
        System.out.println("binaryTreeMap = " + binaryTreeMap);

        avlTreeMap.forEach((key, value) -> avlTreeMap.computeIfPresent(key, (k, v) -> v / 10));
        binaryTreeMap.forEach((key, value) -> binaryTreeMap.computeIfPresent(key, (k, v) -> v / 10));

        System.out.println("avlTreeMap = " + avlTreeMap);
        System.out.println("binaryTreeMap = " + binaryTreeMap);


    }
}
