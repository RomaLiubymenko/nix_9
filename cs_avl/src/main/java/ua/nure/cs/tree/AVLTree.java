package ua.nure.cs.tree;


public class AVLTree {

    public int viewedNodesCounterForSearch = 0;
    public int viewedNodesCounterForInsert = 0;
    public int viewedNodesCounterForDelete = 0;

    public int rotationCounterForInsert = 0;
    public int rotationCounterForDelete = 0;

    private AVLNode root;

    public AVLNode search(int key) {
        AVLNode current = root;
        viewedNodesCounterForSearch++;
        while (current != null) {
            if (current.key == key) {
                break;
            }
            current = (current.key < key ? current.right : current.left);
            viewedNodesCounterForSearch++;
        }
        return current;
    }

    public AVLNode getRoot() {
        return root;
    }

    public int getHeight() {
        return root == null ? -1 : root.height;
    }

    private int getHeight(AVLNode node) {
        return node == null ? -1 : node.height;
    }

    public void insert(int key) {
        root = insert(root, key);
    }

    private AVLNode insert(AVLNode node, int key) {
        if (node == null) {
            viewedNodesCounterForInsert++;
            return new AVLNode(key);
        } else if (node.key > key) {
            viewedNodesCounterForInsert++;
            node.left = insert(node.left, key);
        } else {
            viewedNodesCounterForInsert++;
            node.right = insert(node.right, key);
        }
        return rebalance(node, true);
    }

    public void delete(int key) {
        root = delete(root, key);
    }

    private AVLNode delete(AVLNode node, int key) {
        if (node == null) {
            return node;
        } else if (node.key > key) {
            node.left = delete(node.left, key);
        } else if (node.key < key) {
            node.right = delete(node.right, key);
        } else {
            if (node.left == null || node.right == null) {
                node = ((node.left == null) ? node.right : node.left);
            } else {
                AVLNode mostLeftChild = mostLeftChild(node.right);
                node.key = mostLeftChild.key;
                node.right = delete(node.right, node.key);
            }
        }
        if (node != null) {
            node = rebalance(node, false);
        }
        return node;
    }

    private AVLNode mostLeftChild(AVLNode node) {
        AVLNode current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    private AVLNode rebalance(AVLNode startNode, boolean isAdded) {
        updateHeight(startNode);
        int balance = getBalance(startNode);
        if (balance > 1) {
            if (getHeight(startNode.right.right) > getHeight(startNode.right.left)) {
                startNode = rotateLeft(startNode, isAdded);
            } else {
                startNode.right = rotateRight(startNode.right, isAdded);
                startNode = rotateLeft(startNode, isAdded);
            }
        } else if (balance < -1) {
            if (getHeight(startNode.left.left) > getHeight(startNode.left.right)) {
                startNode = rotateRight(startNode, isAdded);
            } else {
                startNode.left = rotateLeft(startNode.left, isAdded);
                startNode = rotateRight(startNode, isAdded);
            }
        }
        if (isAdded)
            viewedNodesCounterForInsert += 3;
        else
            viewedNodesCounterForDelete += 3;
        return startNode;
    }

    private AVLNode rotateRight(AVLNode rotatedNode, boolean isAdded) {
        if (isAdded) {
            viewedNodesCounterForInsert += 4;
            rotationCounterForInsert++;
        } else {
            viewedNodesCounterForDelete += 4;
            rotationCounterForDelete++;
        }
        AVLNode left = rotatedNode.left;
        AVLNode right = left.right;
        left.right = rotatedNode;
        rotatedNode.left = right;
        updateHeight(rotatedNode);
        updateHeight(left);
        return left;
    }

    private AVLNode rotateLeft(AVLNode rotatedNode, boolean isAdded) {
        if (isAdded) {
            viewedNodesCounterForInsert += 4;
            rotationCounterForInsert++;
        } else {
            viewedNodesCounterForDelete += 4;
            rotationCounterForDelete++;
        }
        AVLNode right = rotatedNode.right;
        AVLNode left = right.left;
        right.left = rotatedNode;
        rotatedNode.right = left;
        updateHeight(rotatedNode);
        updateHeight(right);
        return right;
    }

    private void updateHeight(AVLNode node) {
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
    }

    public int getBalance(AVLNode node) {
        return (node == null) ? 0 : getHeight(node.right) - getHeight(node.left);
    }

    public void join(AVLTree treeToJoin, int key) {
        if (root == null || treeToJoin.getRoot() == null) {
            return;
        }
        if (getHeight() > treeToJoin.getHeight() + 1) {
            root = joinRightAVL(root, treeToJoin.getRoot(), key);
        } else if (treeToJoin.getHeight() > getHeight() + 1) {
            root = joinLeftAVL(root, treeToJoin.getRoot(), key);
        } else {
            root = new AVLNode(root, treeToJoin.getRoot(), key);
        }
    }

    private AVLNode joinRightAVL(AVLNode leftNode, AVLNode rightNode, int key) {
        AVLNode leftOfLeftNode = leftNode.getLeft();
        AVLNode rightOfLeftNode = leftNode.getRight();
        if (getHeight(rightOfLeftNode) <= getHeight(rightNode) + 1) {
            AVLNode node = new AVLNode(rightOfLeftNode, rightNode, key);
            if (getHeight(node) <= getHeight(leftOfLeftNode) + 1) {
                return new AVLNode(leftOfLeftNode, node, leftNode.getKey());
            }
            return rotateLeft(new AVLNode(leftOfLeftNode, rotateRight(node, true), leftNode.getKey()), true);
        } else {
            AVLNode node = joinRightAVL(rightOfLeftNode, rightNode, key);
            AVLNode newRightNode = new AVLNode(leftOfLeftNode, node, leftNode.getKey());
            if (getHeight(node) <= getHeight(leftOfLeftNode) + 1) {
                return newRightNode;
            }
            return rotateLeft(newRightNode, true);
        }
    }

    private AVLNode joinLeftAVL(AVLNode leftNode, AVLNode rightNode, int key) {
        AVLNode leftOfRightNode = rightNode.getLeft();
        AVLNode rightOfRightNode = rightNode.getRight();
        if (getHeight(leftOfRightNode) <= getHeight(leftNode) + 1) {
            AVLNode node = new AVLNode(leftNode, leftOfRightNode, key);
            if (getHeight(node) <= getHeight(rightOfRightNode) + 1) {
                return new AVLNode(node, rightOfRightNode, rightNode.getKey());
            }
            return rotateRight(new AVLNode(rotateLeft(node, true), rightOfRightNode, rightNode.getKey()), true);
        } else {
            AVLNode node = joinLeftAVL(leftNode, leftOfRightNode, key);
            AVLNode newLeftNode = new AVLNode(node, rightOfRightNode, rightNode.getKey());
            if (getHeight(node) <= getHeight(rightOfRightNode) + 1) {
                return newLeftNode;
            }
            return rotateRight(newLeftNode, true);
        }
    }

    public AVLNode findSuccessor(AVLNode node) {
        AVLNode successor = null;
        AVLNode current = root;
        if (root == null)
            return null;
        while (current != node) {
            if (current.key > node.key) {
                successor = current;
                current = current.left;
            } else {
                current = current.right;
            }
        }
        if (current != null && current.right != null) {
            current = current.right;
            while (current.left != null)
                current = current.left;
            successor = current;
        }
        return successor;
    }

    public AVLNode findPredecessor(AVLNode node) {
        AVLNode predecessor = null;
        AVLNode current = root;
        if (root == null)
            return null;
        while (current != node) {
            if (current.key > node.key) {
                current = current.left;
            } else {
                predecessor = current;
                current = current.right;
            }
        }
        if (current != null && current.left != null) {
            current = current.left;
            while (current.right != null)
                current = current.right;
            predecessor = current;
        }
        return predecessor;
    }


    public void inorder() {
        inorder(root);
    }

    private void inorder(AVLNode node) {
        if (node != null) {
            inorder(node.left);
            System.out.print(node.key + " ");
            inorder(node.right);
        }
    }
}
