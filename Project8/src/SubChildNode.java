/*
Name: Connor Byers
Class: CS2050
Prof: Blanche Cohen
SubChildNode: Class for Nodes that belong in a binary search tree
 */

public class SubChildNode<T extends Comparable <T>> {
    public T data;
    public SubChildNode<T> left;
    public SubChildNode<T> right;
    public SubChildNode<T> parent;

    public SubChildNode(T data, SubChildNode<T> parent) {
        this.data = data;
        this.left = null;
        this.right = null;
        this.parent = parent;
    }

    public T getData() {
        return data;
    }
    public SubChildNode<T> getLeft() {
        return left;
    }

    public SubChildNode<T> getRight() {
        return right;
    }
    public SubChildNode<T> getParent() {
        return parent;
    }

}


