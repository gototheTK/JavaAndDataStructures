import java.sql.Blob;

/**
 * RedBlackTree
 */

class Node {
    int key;
    Node parent, left, right;
    int color = 1;
}

public class RedBlackTree {

    private static final int BLACK = 0;
    private static final int RED = 1;

    Node root;

    public void add(int key) {

        Node newNode = new Node();
        newNode.key = key;

        if (null == root) {
            root = newNode;
        } else {
            insertNode(root, newNode);
        }
        root.color = BLACK;

    }

    private void insertNode(Node node, Node newNode) {

        if (node.key > newNode.key && !isExist(node.left)) {
            node.left = newNode;
            newNode.parent = node;
        } else if (node.key <= newNode.key && !isExist(node.right)) {
            node.right = newNode;
            newNode.parent = node;
        } else if (node.key > newNode.key) {
            insertNode(node.left, newNode);
        } else {
            insertNode(node.right, newNode);
        }

        insertFixUp(node);

    }

    private void insertFixUp(Node G) {

        if (isRed(G.right) && isRed(G.right.left) && isBlack(G.left)) {
            LL_rotate(G.right);
            swapColor(G, G.right);
            RR_rotate(G);
        } else if (isRed(G.right) && isRed(G.right.right) && isBlack(G.left)) {
            swapColor(G, G.right);
            RR_rotate(G);
        } else if (isRed(G.right) && (isRed(G.right.right) || isRed(G.right.left))) {
            G.color = RED;
            G.right.color = BLACK;
            G.left.color = BLACK;
        } else if (isRed(G.left) && isRed(G.left.right) && isBlack(G.right)) {
            RR_rotate(G.left);
            swapColor(G, G.left);
            LL_rotate(G);
        } else if (isRed(G.left) && isRed(G.left.left) && isBlack(G.right)) {
            swapColor(G, G.left);
            LL_rotate(G);
        } else if (isRed(G.left) && (isRed(G.left.left) || isRed(G.left.right))) {
            G.color = RED;
            G.left.color = BLACK;
            G.right.color = BLACK;
        }

        root.color = BLACK;

    }

    private void swapColor(Node nodeA, Node nodeB) {
        int color = nodeA.color;
        nodeA.color = nodeB.color;
        nodeB.color = color;
    }

    private Node LL_rotate(Node P) {
        Node GP = P;
        Node L = P.left;
        Node LR = L.right;
        L.right = P;
        P.left = LR;

        P.parent = L;
        if (isExist(LR)) {
            LR.parent = L;
        }

        L.parent = GP;

        if (!isExist(GP)) {
            root = L;
        } else {

            if (GP.left == P) {
                GP.left = L;
            } else if (GP.right == P) {
                GP.right = L;
            }

        }

        return L;

    }

    private Node RR_rotate(Node P) {

        Node GP = P.parent;
        Node R = P.right;
        Node RL = R.left;
        R.left = P;
        P.right = RL;

        P.parent = R;
        if (isExist(RL)) {
            RL.parent = P;
        }

        R.parent = GP;

        if (!isExist(GP)) {
            root = R;
        } else {
            if (GP.left == P) {
                GP.left = R;
            } else if (GP.right == P) {
                GP.right = R;
            }
        }

        return R;

    }

    private boolean isBlack(Node node) {
        return !isExist(node) || BLACK == node.color;
    }

    private boolean isRed(Node node) {
        return isExist(node) && RED == node.color;
    }

    private boolean isExist(Node node) {
        return null != node;
    }

    public void remove(int key) {
        removeNode(root, key);
    }

    private void removeNode(Node node, int key) {

        if (null == node) {
            throw new RuntimeException("키를 찾을 수 없습니다.");
        } else if (node.key > key) {
            removeNode(node.left, key);
        } else if (node.key < key) {
            removeNode(node.right, key);
        } else {

            if (null != node.left) {

                Node predecessor = getLargetstNode(node.left);
                int removeKey = node.key;
                node.key = predecessor.key;
                predecessor.key = removeKey;
                removeNode(node.left, key);

            } else if (null != node.right) {

                Node successor = getSmallestNode(node.right);
                int removeKey = node.key;
                node.key = successor.key;
                successor.key = removeKey;
                removeNode(node.right, key);

            } else {
                if (root == node) {
                    root = null;
                } else {
                    if (isBlack(node)) {
                        removeFixup(node);
                    }
                    if (node.parent.left == node) {
                        node.parent.left = null;
                    } else if (node.parent.right == node) {
                        node.parent.right = null;
                    }
                    node.parent = null;
                }
            }

        }

    }

    private void removeFixup(Node x) {

        while (root != x && isBlack(x)) {

            if (x == x.parent.left) {
                Node w = x.parent.right;

                if (isRed(w)) {
                    w.color = BLACK;
                    x.parent.color = RED;
                    RR_rotate(w.parent);
                    w = x.parent.right;
                }
                if (isBlack(w.left) && isBlack(w.right)) {
                    w.color = RED;
                    x = x.parent;
                } else {

                    if (isBlack(w.right)) {

                        w.left.color = BLACK;
                        w.color = RED;
                        LL_rotate(w);
                        w = x.parent.right;
                    }

                    w.color = BLACK;
                    w.right.color = BLACK;
                    RR_rotate(x.parent);
                    break;
                }
            } else if (x == x.parent.right) {

                Node w = x.parent.left;

                if (isRed(w)) {
                    w.color = BLACK;
                    x.parent.color = RED;
                    LL_rotate(x.parent);
                    w = x.parent.left;
                }
                if (isBlack(w.left) && isBlack(w.right)) {
                    w.color = RED;
                    x = x.parent;
                } else {

                    if (isBlack(w.left)) {
                        w.left.color = BLACK;
                        w.color = RED;
                        RR_rotate(w);
                        w = x.parent.left;
                    }

                    w.color = x.parent.color;
                    x.parent.color = BLACK;
                    w.left.color = BLACK;
                    LL_rotate(x.parent);
                    break;
                }

            }

        }
        x.color = BLACK;

    }

    private Node getLargetstNode(Node node) {
        if (null == node.right) {
            return node;
        }
        return getLargetstNode(node.right);
    }

    private Node getSmallestNode(Node node) {
        if (null == node.left) {
            return node;
        }
        return getSmallestNode(node.left);
    }

    private void printHelper(Node root, String indent, boolean last) {
        if (root != null) {
            System.out.print(indent);
            if (last) {
                System.out.print("R----");
                indent += "   ";
            } else {
                System.out.print("L----");
                indent += "|  ";
            }

            String sColor = root.color == RED ? "RED" : "BLACK";
            System.out.println(root.key + "(" + sColor + ")");
            printHelper(root.left, indent, false);
            printHelper(root.right, indent, true);
        }
    }

    public void printTree() {
        printHelper(this.root, "", true);
    }

    public void traversal() {
        inorderTraversal(root);
    }

    private void inorderTraversal(Node node) {

        if (null == node) {

            return;
        }

        inorderTraversal(node.left);
        System.out.print(" " + node.key);
        inorderTraversal(node.right);

    }

    public static void main(String[] args) {
        RedBlackTree redBlackTree = new RedBlackTree();
        redBlackTree.add(5);
        redBlackTree.add(2);
        redBlackTree.add(3);
        redBlackTree.add(7);
        redBlackTree.add(4);
        redBlackTree.add(8);
        redBlackTree.add(1);
        redBlackTree.add(6);
        redBlackTree.add(9);
        redBlackTree.printTree();
        redBlackTree.remove(6);
        redBlackTree.printTree();
    }

}