package ua.nure.cs;


import ua.com.alevel.tree.BinaryTree;
import ua.nure.cs.tree.AVLTree;
import ua.nure.cs.util.TreePrinter;
import ua.nure.cs.util.TreeUtils;

import java.util.HashMap;
import java.util.Map;

class Main {

    public static void main(String[] args) {
        AVLTree avlTree = TreeUtils.generateRandomAVLTree(1000, 0, 100);
        BinaryTree binaryTree = TreeUtils.generateRandomBinaryTree(1000, 0, 100);

        Integer avlTreeHeight = avlTree.getHeight();
        Integer binaryTreeHeight = binaryTree.getMaxTreeDepth() - 1;

        System.out.println("avlTreeHeight = " + avlTreeHeight);
        System.out.println("binaryTreeHeight = " + binaryTreeHeight);

//        TreePrinter.print(avlTree.getRoot());
//        TreePrinter.print(binaryTree.getRoot());

        Map<String, Float> avlTreeMap = new HashMap<>();  
        Map<String, Float> binaryTreeMap = new HashMap<>();
        
        for (int i = 0; i < 9; i++) {
            avlTree = TreeUtils.generateRandomAVLTree(1000, 0, 100);
            TreeUtils.testAVLTree(avlTree, 200).forEach((key, value) -> avlTreeMap.merge( key, value, Float::sum));
        }
        
        for (int i = 0; i < 9; i++) {
            binaryTree = TreeUtils.generateRandomBinaryTree(1000, 0, 100);
            TreeUtils.testBinaryTree(binaryTree, 200).forEach((key, value) -> binaryTreeMap.merge( key, value, Float::sum));
        }

        System.out.println("avlTreeMap = " + avlTreeMap);
        System.out.println("binaryTreeMap = " + binaryTreeMap);


    }
}
