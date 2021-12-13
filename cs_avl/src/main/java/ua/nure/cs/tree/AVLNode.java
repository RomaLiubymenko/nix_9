package ua.nure.cs.tree;

import ua.com.alevel.tree.TreeNode;

public class AVLNode implements TreeNode {
    int key;
    int height;
    AVLNode left;
    AVLNode right;

    public AVLNode(int data) {
        this.key = data;
    }

    public AVLNode(AVLNode left, AVLNode right, int data) {
        this.key = data;
        this.left = left;
        this.right = right;
        this.height = Math.max(left.height, right.height) + 1;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public AVLNode getLeft() {
        return left;
    }

    public void setLeft(AVLNode left) {
        this.left = left;
    }

    public AVLNode getRight() {
        return right;
    }

    public void setRight(AVLNode right) {
        this.right = right;
    }
}
