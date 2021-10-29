package ua.com.alevel.tree;

public class Node {
    int content;
    Node left;
    Node right;

    public Node(int c) {
        this.content = c;
    }

    public int getContent() {
        return content;
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
