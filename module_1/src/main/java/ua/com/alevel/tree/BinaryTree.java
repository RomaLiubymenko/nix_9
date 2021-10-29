package ua.com.alevel.tree;

import javax.swing.*;

public class BinaryTree {
    Node rootNode = null;

    public Node getRoot() {
        return rootNode;
    }

    public void addNode(int content) {
        if (rootNode == null) {
            rootNode = new Node(content);
            return;
        }
        addTo(rootNode, content);
    }

    private Node addTo(Node node, int numberToAdd) {
        if (numberToAdd < node.getContent()) {
            Node leftNode = node.getLeft();
            if (leftNode == null)
                return node.setLeft(numberToAdd);
            else
                return addTo(leftNode, numberToAdd);
        } else {
            Node rightNode = node.getRight();
            if (rightNode == null)
                return node.setRight(numberToAdd);
            else
                return addTo(rightNode, numberToAdd);
        }
    }

    public Node searchNode(int numberInNode, Node node) {
        Node searchedNode = null;
        if (node != null) {
            if (numberInNode == node.getContent()) {
                return node;
            } else {
                if (numberInNode < node.getContent()) {
                    Node leftNode = node.getLeft();
                    searchedNode = searchNode(numberInNode, leftNode);
                } else {
                    Node rightNode = node.getRight();
                    searchedNode = searchNode(numberInNode, rightNode);
                }
            }
        }
        return searchedNode;
    }

    public Node deleteNode(int number, Node rootNode) {
        Node aux = searchNode(number, rootNode);
        if (aux == null)
            JOptionPane.showMessageDialog(BinaryTreePanel.BinaryTreeFrame, "The number " + number + " was not found");
        else {
            if (rootNode == null) {
                return rootNode;
            } else if (number < rootNode.getContent()) {
                rootNode.left = deleteNode(number, rootNode.left);
            } else if (number > rootNode.getContent()) {
                rootNode.right = deleteNode(number, rootNode.right);
            } else {
                if (rootNode.left == null && rootNode.right == null) {
                    rootNode = null;
                } else if (rootNode.left == null) {
                    Node temp = rootNode;
                    rootNode = rootNode.right;
                    temp = null;
                } else if (rootNode.right == null) {
                    Node temp = rootNode;
                    rootNode = rootNode.left;
                    temp = null;
                } else {
                    Node temp = findMin(rootNode.right);
                    rootNode.content = temp.content;
                    rootNode.right = deleteNode(temp.getContent(), rootNode.right);
                }
            }
            return rootNode;
        }
        return rootNode;
    }

    public Node findMin(Node rootNode) {
        while (rootNode.left != null)
            rootNode = rootNode.left;
        return rootNode;
    }

    public int getMaxTreeDepth() {
        return maxDepth(rootNode);
    }

    private int maxDepth(Node rootNode) {
        if (rootNode == null)
            return 0;
        int leftDepth = maxDepth(rootNode.left);
        int rightDepth = maxDepth(rootNode.right);
        if (leftDepth > rightDepth)
            return (leftDepth + 1);
        else
            return (rightDepth + 1);
    }
}
