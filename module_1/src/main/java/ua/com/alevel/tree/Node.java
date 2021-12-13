package ua.com.alevel.tree;

public class Node implements TreeNode {
    public int key;
    public Node left;
    public Node right;

    public Node(int c) {
        this.key = c;
    }

    public int getKey() {
        return key;
    }

    public Node getLeft() {
        return left;
    }

    public Node setLeft(int content) {
        left = new Node(content);
        return left;
    }

    public Node getRight() {
        return right;
    }

    public Node setRight(int content) {
        right = new Node(content);
        return right;
    }
}
