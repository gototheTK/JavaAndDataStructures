
class Node {

    Node parent, left, right;
    int key;
    int color = 1;

    public Node(int key) {
        this.key = key;
    }

}

public class RedBlackTree1 {

    private final int RED = 1;
    private final int BLACK = 0;

    private Node root;

    public void add(int key) {

        Node node = new Node(key);

        if (null == root) {
            root = node;
        } else {
            insertNode(root, node);
        }

        root.color = BLACK;

    }

    private void insertNode(Node x, Node newNode) {

        if (x.key > newNode.key && !isExist(x.left)) {
            x.left = newNode;
            newNode.parent = x;
        } else if (x.key < newNode.key && !isExist(x.right)) {
            x.right = newNode;
            newNode.parent = x;
        } else if (x.key < newNode.key) {
            insertNode(x.left, newNode);
        } else {
            insertNode(x.right, newNode);
        }

        insertFixUp(x);

    }

    private void insertFixUp(Node g) {

        if (isRed(g.right) && isRed(g.right.left) && isBlack(g.left)) {
            LLrotateNode(g.right);
            swapColor(g, g.right);
            RRrotateNode(g);
        } else if (isRed(g.right) && isRed(g.right.right) && isBlack(g.left)) {
            swapColor(g, g.right);
            RRrotateNode(g);
        } else if (isRed(g.right) && (isRed(g.left.left) || isRed(g.right.right))) {
            g.color = RED;
            g.left.color = BLACK;
            g.right.color = BLACK;
        } else if (isRed(g.left) && isRed(g.left.left) && isBlack(g.right)) {
            swapColor(g, g.left);
            LLrotateNode(g);
        } else if (isRed(g.left) && isBlack(g.left.right) && isBlack(g.left)) {
            RRrotateNode(g.left);
            swapColor(g, g.left);
            LLrotateNode(g);
        } else if (isRed(g.left) && (isRed(g.left.left) || isRed(g.right))) {
            g.color = RED;
            g.left.color = BLACK;
            g.right.color = BLACK;
        }

    }

    private void swapColor(Node nodeA, Node nodeB) {
        int color = nodeA.color;
        nodeA.color = nodeB.color;
        nodeB.color = color;
    }

    private boolean isRed(Node node) {
        return null != node && node.color == RED;
    }

    private boolean isBlack(Node node) {
        return null == node || node.color == BLACK;
    }

    private boolean isExist(Node node) {
        return null != node;
    }

    private Node RRrotateNode(Node P) {

        Node G = P.parent;
        Node R = P.right;
        Node RL = R.left;
        R.left = P;
        P.right = RL;

        P.parent = R;
        if (isExist(RL)) {
            RL.parent = P;
        }

        if (!isExist(G)) {
            root = R;
        } else {

            if (G.left == P) {
                G.left = R;
            } else {
                G.right = R;
            }

        }

        return R;

    }

    private Node LLrotateNode(Node P) {
        Node G = P.parent;
        Node L = P.left;
        Node LR = L.right;
        L.right = P;
        P.left = LR;

        P.parent = L;
        if (isExist(LR)) {
            LR.parent = P;
        }

        if (!isExist(G)) {
            root = L;
        } else {

            if (G.left == P) {
                G.left = L;
            } else {
                G.right = L;
            }

        }

        return L;

    }

}
