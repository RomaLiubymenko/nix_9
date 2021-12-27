package ua.nure.cs.tree;


import ua.nure.cs.util.BTreeUtils;

abstract class Node<TKey extends Comparable<TKey>> {
    protected Object[] keys;
    protected int keyCount;
    protected Node<TKey> parentNode;
    protected Node<TKey> leftSibling;
    protected Node<TKey> rightSibling;


    protected Node() {
        this.keyCount = 0;
        this.parentNode = null;
        this.leftSibling = null;
        this.rightSibling = null;
    }

    public int getKeyCount() {
        return this.keyCount;
    }

    @SuppressWarnings("unchecked")
    public TKey getKey(int index) {
        return (TKey) this.keys[index];
    }

    public void setKey(int index, TKey key) {
        this.keys[index] = key;
    }

    public Node<TKey> getParent() {
        return this.parentNode;
    }

    public void setParent(Node<TKey> parent) {
        this.parentNode = parent;
    }

    public abstract NodeType getNodeType();

    public abstract int search(TKey key);

    public boolean isOverflow() {
        return this.getKeyCount() == this.keys.length;
    }

    public Node<TKey> dealOverflow() {
        int midIndex = this.getKeyCount() / 2;
        TKey upKey = this.getKey(midIndex);
        Node<TKey> newRNode = this.split();
        if (this.getParent() == null) {
            this.setParent(new InnerNode<>());
        }
        newRNode.setParent(this.getParent());
        BTreeUtils.viewedNodesCounterForInsert++;
        newRNode.setLeftSibling(this);
        newRNode.setRightSibling(this.rightSibling);
        if (this.getRightSibling() != null)
            this.getRightSibling().setLeftSibling(newRNode);
        this.setRightSibling(newRNode);
        BTreeUtils.viewedNodesCounterForInsert += 2;
        return this.getParent().pushUpKey(upKey, this, newRNode);
    }

    protected abstract Node<TKey> split();

    protected abstract Node<TKey> pushUpKey(TKey key, Node<TKey> leftChild, Node<TKey> rightNode);

    public boolean isUnderflow() {
        return this.getKeyCount() < (this.keys.length / 2);
    }

    public boolean canLendAKey() {
        return this.getKeyCount() > (this.keys.length / 2);
    }

    public Node<TKey> getLeftSibling() {
        if (this.leftSibling != null && this.leftSibling.getParent() == this.getParent())
            return this.leftSibling;
        return null;
    }

    public void setLeftSibling(Node<TKey> sibling) {
        this.leftSibling = sibling;
    }

    public Node<TKey> getRightSibling() {
        if (this.rightSibling != null && this.rightSibling.getParent() == this.getParent())
            return this.rightSibling;
        return null;
    }

    public void setRightSibling(Node<TKey> silbling) {
        this.rightSibling = silbling;
    }

    public Node<TKey> dealUnderflow() {
        if (this.getParent() == null)
            return null;

        Node<TKey> leftSibling = this.getLeftSibling();
        BTreeUtils.viewedNodesCounterForDelete++;
        if (leftSibling != null && leftSibling.canLendAKey()) {
            this.getParent().processChildrenTransfer(this, leftSibling, leftSibling.getKeyCount() - 1);
            BTreeUtils.viewedNodesCounterForDelete++;
            return null;
        }

        Node<TKey> rightSibling = this.getRightSibling();
        BTreeUtils.viewedNodesCounterForDelete++;
        if (rightSibling != null && rightSibling.canLendAKey()) {
            this.getParent().processChildrenTransfer(this, rightSibling, 0);
            BTreeUtils.viewedNodesCounterForDelete++;
            return null;
        }

        if (leftSibling != null) {
            return this.getParent().processChildrenFusion(leftSibling, this);
        } else {
            return this.getParent().processChildrenFusion(this, rightSibling);
        }
    }

    protected abstract void processChildrenTransfer(Node<TKey> borrower, Node<TKey> lender, int borrowIndex);

    protected abstract Node<TKey> processChildrenFusion(Node<TKey> leftChild, Node<TKey> rightChild);

    protected abstract void fusionWithSibling(TKey sinkKey, Node<TKey> rightSibling);

    protected abstract TKey transferFromSibling(TKey sinkKey, Node<TKey> sibling, int borrowIndex);
}


