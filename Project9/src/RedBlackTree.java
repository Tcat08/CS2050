// Red Black Tree implementation in Java
// data structure that represents a node in the tree

    // class RedBlackTree implements the operations in Red Black Tree
    public class RedBlackTree {
        private Node root;
        private Node TNULL;
        public final int RED = 1;
        public final int BLACK = 0;
        int nodeCount;

        private void preOrderHelper(Node node) {
            if (node != TNULL) {
                System.out.print(node.data + " ");
                preOrderHelper(node.left);
                preOrderHelper(node.right);
            }
        }

        private void inOrderHelper(Node node) {
            if (node != TNULL) {
                inOrderHelper(node.left);
                System.out.print(node.data + " ");
                inOrderHelper(node.right);
            }
        }

        private String postOrderHelper(Node node) {
            StringBuilder result = new StringBuilder();
            if (node != TNULL) {
                if (node != null) {
                    result.append(postOrderHelper(node.getLeft()));
                    result.append(postOrderHelper(node.getRight()));
                    result.append(node.getData()).append(" ");
                }
            }
            return result.toString();
        }

        private Node searchTreeHelper(Node node, String key) {
// base case
            if (node == null || node.data.compareTo(key) == 0) {
                return root;
            }
// search left subtree
            if (node.data.compareTo(key) < 0) {
                return searchTreeHelper(node.left, key);
            }
// search right subtree
            return searchTreeHelper(node.right, key);
        }

        // fix the rb tree modified by the delete operation
        private void fixDelete(Node x) {
            Node s;
            while (x != root && x.color == BLACK) {
                if (x == x.parent.left) {
                    s = x.parent.right;
                    if (s.color == RED) {
// case 3.1
                        s.color = BLACK;
                        x.parent.color = RED;
                        leftRotate(x.parent);
                        s = x.parent.right;
                    }
                    if (s.left.color == BLACK && s.right.color == BLACK) {
// case 3.2
                        s.color = RED;
                        x = x.parent;
                    } else {
                        if (s.right.color == BLACK) {
// case 3.3
                            s.left.color = BLACK;
                            s.color = RED;
                            rightRotate(s);
                            s = x.parent.right;
                        }
// case 3.4
                        s.color = x.parent.color;
                        x.parent.color = BLACK;
                        s.right.color = BLACK;
                        leftRotate(x.parent);
                        x = root;
                    }
                } else {
                    s = x.parent.left;
                    if (s.color == RED) {
// case 3.1
                        s.color = BLACK;
                        x.parent.color = RED;
                        rightRotate(x.parent);
                        s = x.parent.left;
                    }
                    if (s.right.color == BLACK && s.right.color == BLACK) {
// case 3.2
                        s.color = RED;
                        x = x.parent;
                    } else {
                        if (s.left.color == BLACK) {
// case 3.3
                            s.right.color = BLACK;
                            s.color = RED;
                            leftRotate(s);
                            s = x.parent.left;
                        }
// case 3.4
                        s.color = x.parent.color;
                        x.parent.color = BLACK;
                        s.left.color = BLACK;
                        rightRotate(x.parent);
                        x = root;
                    }
                }
            }
            x.color = BLACK;
        }

        private void rbTransplant(Node u, Node v) {
            if (u.parent == null) {
                root = v;
            } else if (u == u.parent.left) {
                u.parent.left = v;
            } else {
                u.parent.right = v;
            }
            v.parent = u.parent;
        }

        private void deleteNodeHelper(Node node, String key) {
// find the node containing key
            Node z = TNULL;
            Node x, y;
            String data;

            int comparison = data.compareTo(key);

            while (node != TNULL) {
                if (comparison == 0) { //   int comparison = data.compareTo(key);
                    z = node;
                }
                if (comparison <= 0) {
                    node = node.right;
                } else {
                    node = node.left;
                }
            }
            if (z == TNULL) {
                System.out.println("Couldn't find key in the tree");
                return;
            }
            y = z;
            int yOriginalColor = y.color;
            if (z.left == TNULL) {
                x = z.right;
                rbTransplant(z, z.right);
            } else if (z.right == TNULL) {
                x = z.left;
                rbTransplant(z, z.left);
            } else {
                y = minimum(z.right);
                yOriginalColor = y.color;
                x = y.right;
                if (y.parent == z) {
                    x.parent = y;
                } else {
                    rbTransplant(y, y.right);
                    y.right = z.right;
                    y.right.parent = y;
                }
                rbTransplant(z, y);
                y.left = z.left;
                y.left.parent = y;
                y.color = z.color;
            }
            if (yOriginalColor == BLACK) {
                fixDelete(x);
            }
        }

        // fix the red-black tree
        private void fixInsert(Node k) {
            Node u;
            while (k.parent.color == RED) {
                if (k.parent == k.parent.parent.right) {
                    u = k.parent.parent.left; // uncle
                    if (u.color == RED) {
// case 3.1
                        u.color = BLACK;
                        k.parent.color = BLACK;
                        k.parent.parent.color = RED;
                        k = k.parent.parent;
                    } else {
                        if (k == k.parent.left) {
// case 3.2.2
                            k = k.parent;
                            rightRotate(k);
                        }
// case 3.2.1
                        k.parent.color = BLACK;
                        k.parent.parent.color = RED;
                        leftRotate(k.parent.parent);
                    }
                } else {
                    u = k.parent.parent.right; // uncle
                    if (u.color == RED) {
// mirror case 3.1
                        u.color = BLACK;
                        k.parent.color = BLACK;
                        k.parent.parent.color = RED;
                        k = k.parent.parent;
                    } else {
                        if (k == k.parent.right) {
// mirror case 3.2.2
                            k = k.parent;
                            leftRotate(k);
                        }
// mirror case 3.2.1
                        k.parent.color = BLACK;
                        k.parent.parent.color = RED;
                        rightRotate(k.parent.parent);
                    }
                }
                if (k == root) {
                    break;
                }
            }
            root.color = BLACK;
        }

        private void printHelper(Node root, String indent, boolean last) {
// print the tree structure on the screen
            if (root != TNULL) {
                System.out.print(indent);
                if (last) {
                    System.out.print("R----");
                    indent += " ";
                } else {
                    System.out.print("L----");
                    indent += "| ";
                }
                String sColor = root.color == 1 ? "RED" : "BLACK";
                System.out.println(root.data + "(" + sColor + ")");
                printHelper(root.left, indent, false);
                printHelper(root.right, indent, true);
            }
        }

        public RedBlackTree() {
            TNULL = new Node();
            TNULL.color = BLACK;
            TNULL.left = null;
            TNULL.right = null;
            root = TNULL;
        }

        // Pre-Order traversal
// Node.Left Subtree.Right Subtree
        public void preorder() {
            preOrderHelper(this.root);
        }

        // In-Order traversal
// Left Subtree . Node . Right Subtree
        public void inorder() {
            inOrderHelper(this.root);
        }

        // Post-Order traversal
// Left Subtree . Right Subtree . Node
        public String postorder() {
            return postOrderHelper(this.root);
        }

        // search the tree for the key k
// and return the corresponding node
        public Node searchTree(String k) {
            return searchTreeHelper(this.root, k);
        }

        // find the node with the minimum key
        public Node minimum(Node node) {
            while (node.left != TNULL) {
                node = node.left;
            }
            return node;
        }

        // find the node with the maximum key
        public Node maximum(Node node) {
            while (node.right != TNULL) {
                node = node.right;
            }
            return node;
        }

        // find the successor of a given node
        public Node successor(Node x) {
// if the right subtree is not null,
// the successor is the leftmost node in the
// right subtree
            if (x.right != TNULL) {
                return minimum(x.right);
            }
// else it is the lowest ancestor of x whose
// left child is also an ancestor of x.
            Node y = x.parent;
            while (y != TNULL && x == y.right) {
                x = y;
                y = y.parent;
            }
            return y;
        }

        // find the predecessor of a given node
        public Node predecessor(Node x) {
// if the left subtree is not null,
// the predecessor is the rightmost node in the
// left subtree
            if (x.left != TNULL) {
                return maximum(x.left);
            }
            Node y = x.parent;
            while (y != TNULL && x == y.left) {
                x = y;
                y = y.parent;
            }
            return y;
        }

        // rotate left at node x
        public void leftRotate(Node x) {
            Node y = x.right;
            x.right = y.left;
            if (y.left != TNULL) {
                y.left.parent = x;
            }
            y.parent = x.parent;
            if (x.parent == null) {
                this.root = y;
            } else if (x == x.parent.left) {
                x.parent.left = y;
            } else {
                x.parent.right = y;
            }
            y.left = x;
            x.parent = y;
        }

        // rotate right at node x
        public void rightRotate(Node x) {
            Node y = x.left;
            x.left = y.right;
            if (y.right != TNULL) {
                y.right.parent = x;
            }
            y.parent = x.parent;
            if (x.parent == null) {
                this.root = y;
            } else if (x == x.parent.right) {
                x.parent.right = y;
            } else {
                x.parent.left = y;
            }
            y.right = x;
            x.parent = y;
        }

        // insert the key to the tree in its appropriate position
// and fix the tree
        public void insert(String key) {
// Ordinary Binary Search Insertion
            Node node = new Node();
            node.parent = null;
            node.data = key;
            node.left = TNULL;
            node.right = TNULL;
            node.color = RED; // new node must be red
            Node y = null;
            Node x = this.root;
            if (x == null) {
                nodeCount++;
                new Node();
            }
            while (x != TNULL) {
                y = x;
                if (node.data.compareTo(x.data) < 0) {
                    x = x.left;
                } else {
                    x = x.right;
                }
            }
// y is parent of x
            node.parent = y;
            if (y == null) {
                root = node;
            } else if (node.data.compareTo(y.data) < 0) {
                y.left = node;
            } else {
                y.right = node;
            }
// if new node is a root node, simply return
            if (node.parent == null) {
                node.color = BLACK;
                return;
            }
// if the grandparent is null, simply return
            if (node.parent.parent == null) {
                return;
            }
// Fix the tree
            fixInsert(node);
        }

        public Node getRoot() {
            return this.root;
        }
        public int getNodeCount() {
            return nodeCount;
        }

        public int getHeight() {
            return getHeight(root);
        }

        private int getHeight(Node node) {
            if (node == null) {
                return -1; // Height of an empty tree is -1
            }

            int leftHeight = getHeight(node.left);
            int rightHeight = getHeight(node.right);

            return Math.max(leftHeight, rightHeight) + 1;
        }

        public int maxNodes() { //this is just (2^n)-1 where n is the height, but we already found the height above
            int height = getHeight(root); // for this red black tree, the max height should match the actual height
            return (int) Math.pow(2, height) - 1;
        }

        // delete the node from the tree
        public void deleteNode(String data) {
            deleteNodeHelper(this.root, data);
        }

        // print the tree structure on the screen
        public void prettyPrint() {
            printHelper(this.root, "", true);
        }

    }
