/*
Name: Connor Byers
Class: CS2050
Prof: Blanche Cohen
Binary Search Tree: Methods for creating SubChildNodes for a binary search tree like we learned in class
Variable "root" starts in Constructor as null and the first inserted node becomes the root
Every following inserted node goes left or right and follows the proper path
Most importantly I added search to compare until it is equal unlike insert that will just not insert if it is equal
I also added delete for some reason (for later) but mostly because it just needed the min value which I needed anyway
There's the methods for each traversal, but I mostly just need inorder
There's also just a counter for each node that is added at the end of insert that has a method

Each of these methods has one for just plugging in the data and another for calling the recursive method by because
the recursive ones need an input for parent and root, so they can keep track as they follow the tree (besides min value and traversing)

 */

public class BinarySearchTree<T extends Comparable<T>> {
    SubChildNode<T> root;
    int nodeCount;

    public BinarySearchTree() {
        root = null;
        nodeCount = 0;
    }

    public void insert(T data) {
        root = insertRec(root, null, data);
    }

    private SubChildNode<T> insertRec(SubChildNode<T> root, SubChildNode<T> parent, T data) {
        if (root == null) {
            nodeCount++;
            return new SubChildNode<>(data, parent);
        }

        int comparison = data.compareTo(root.data);

        if (comparison < 0) {
            root.left = insertRec(root.left, root, data);
        } else if (comparison > 0) {
            root.right = insertRec(root.right, root, data);
        }

        return root;
    }

    public SubChildNode<T> search(T data) {
        return searchRec(root, data);
    }

    private SubChildNode<T> searchRec(SubChildNode<T> root, T data) {
        if (root == null || root.data.compareTo(data) == 0) {
            return root;
        }

        int comparison = data.compareTo(root.data);

        if (comparison < 0) {
            return searchRec(root.left, data);
        } else {
            return searchRec(root.right, data);
        }
    }

    public void delete(T data) {
        root = deleteRec(root, data);
    }

    private SubChildNode<T> deleteRec(SubChildNode<T> root, T data) {
        if (root == null) {
            return root;
        }

        int comparison = data.compareTo(root.data);

        if (comparison < 0) {
            root.left = deleteRec(root.left, data);
        } else if (comparison > 0) {
            root.right = deleteRec(root.right, data);
        } else {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            root.data = minValue(root.right);
            root.right = deleteRec(root.right, root.data);
        }

        return root;
    }

    private T minValue(SubChildNode<T> root) {
        T minValue = root.data;
        while (root.left != null) {
            minValue = root.left.data;
            root = root.left;
        }
        return minValue;
    }

    public void inOrderTraversal() {
        inOrderRec(root);
        //System.out.println();
    }

    private void inOrderRec(SubChildNode<T> root) {
        if (root != null) {
            inOrderRec(root.left);
            System.out.println(root.data + " ");
            inOrderRec(root.right);
        }
    }

    public String postOrderTraversal() {
        return postOrderTraversal(root);
    }

    private String postOrderTraversal(SubChildNode<T> node) {
        StringBuilder result = new StringBuilder();
        if (node != null) {
            result.append(postOrderTraversal(node.getLeft()));
            result.append(postOrderTraversal(node.getRight()));
            result.append(node.getData()).append(" ");
        }
        return result.toString();
    }

    public void preOrderTraversal() {
        preOrderTraversal(root);
    }

    private void preOrderTraversal(SubChildNode<T> node) {
        if (node != null) {
            System.out.println(node.data + " ");
            preOrderTraversal(node.left);
            preOrderTraversal(node.right);
        }
    }

    public int getNodeCount() {
        return nodeCount;
    }

    public int getHeight() {
        return getHeight(root);
    }

    private int getHeight(SubChildNode<T> node) {
        if (node == null) {
            return -1; // Height of an empty tree is -1
        }

        int leftHeight = getHeight(node.left);
        int rightHeight = getHeight(node.right);

        return Math.max(leftHeight, rightHeight) + 1;
    }

    public int maxNodes() { //this is just (2^n)-1 where n is the height, but we already found the height above
        int height = getHeight(root);
        return (int) Math.pow(2, height) - 1;
    }


}