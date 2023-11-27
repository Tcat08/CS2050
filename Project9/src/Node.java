/*
Connor
CS2050
Cohen
Node has more setters and getters and even a constructor just in case.
 */
public class Node {
    String data; // holds the key
    Node parent; // pointer to the parent
    Node left; // pointer to left child
    Node right; // pointer to right child
    int color; // 1 . Red, 0 . Black
    int count;

    public Node() { // constructor
        this.data="";
        this.parent = null;
        this.left = null;
        this.right = null;
        this.color = 0; // 0 is default Black
    }

    public Node(String data){
        this.data = data;
        this.parent = null;
        this.left = null;
        this.right = null;
        this.color = 1; // 1 is inputs are red to not match root and swap later
        this.count = 1;

    }

    public String getData() { //get methods for each
        return data;
    }

    public Node getParent() {
        return parent;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public int getColor() {
        return color;

    }
}