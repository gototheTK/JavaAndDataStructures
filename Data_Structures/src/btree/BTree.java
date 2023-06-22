import java.util.*;

class Node {

    List<Integer> keys;
    List<Node> nodes;

    public Node(int t) {

        keys = new ArrayList<>(2 * t);
        nodes = new ArrayList<>(2 * t);

    }

}

/**
 * BTree
 */
public class BTree {

    private final int t;
    private Node root;

    public BTree(int t) {
        this.t = t;
    }

    public void add(int key) {

        if (null == root) {
            root = new Node(t);
            root.keys.add(key);
        } else {
            root = insertKey(root, key);
        }

    }

    private Node insertKey(Node x, int key) {

        int keyIndex = findKeyIndex(x, key);

        if (keyIndex < x.keys.size() && x.keys.get(keyIndex) == key) {
            throw new RuntimeException("동일한 키가 존재함");
        }

        if (isLeafNode(x)) {
            x.keys.add(key);
        } else {

            Node y = insertKey(x.nodes.get(keyIndex), key);

            if (y.keys.size() == 2 * t) {
                x = splitNode(x, y, keyIndex);
            }

        }

        if (root == x && x.keys.size() == 2 * t) {
            Node newRoot = new Node(2 * t);
            x = splitNode(newRoot, x, 0);
        }

        return x;
    }

    private Node splitNode(Node x, Node y, int keyIndex) {

        Node z = new Node(2 * t);
        int medianKey = t;
        int yKeysSize = y.keys.size();
        int yChildrenSize = y.nodes.size();

        z.keys.addAll(y.keys.subList(t + 1, yKeysSize));
        y.keys.subList(t, yKeysSize);

        if (!y.nodes.isEmpty()) {
            z.nodes.addAll(y.nodes.subList(t + 1, yChildrenSize));
            y.nodes.subList(t + 1, yChildrenSize);
        }

        if (root == x) {
            x.keys.add(medianKey);
            x.nodes.add(y);
            x.nodes.add(z);
        } else {

            x.keys.add(keyIndex, medianKey);
            x.nodes.add(keyIndex + 1, z);
        }

        return x;
    }

    private int findKeyIndex(Node x, int key) {
        int i = 0;
        while (i < x.keys.size() && i < key) {
            i++;
        }
        return i;
    }

    private boolean isLeafNode(Node x) {
        return x.nodes.isEmpty();
    }

}

// import java.util.*;

// class Node {

// List<Integer> keys;
// List<Node> nodes;

// Node(int t) {
// keys = new ArrayList<>(2 * t);
// nodes = new ArrayList<>(2 * t);
// }

// }

// /**
// * BTree
// */
// public class BTree {

// private final int t;
// private Node root;

// public BTree(int t) {
// this.t = t;
// }

// public void add(int key) {

// if (null == this.root) {

// root = new Node(t);
// root.keys.add(key);
// } else {
// root = insertKey(root, key);
// }
// }

// private Node insertKey(Node x, int key) {
// int keyIndex = findKeyIndex(x, key);
// if (keyIndex < x.nodes.size() && key == x.keys.get(keyIndex)) {
// throw new RuntimeException("동일한키가 존재합니다.");
// }

// if (isLeafNode(x)) {
// x.keys.add(keyIndex, key);
// } else {

// Node y = insertKey(x.nodes.get(keyIndex), key);
// if (y.keys.size() == 2 * t) {
// x = splitNode(x, y, keyIndex);
// }
// }

// if (root == x && x.keys.size() == 2 * t) {
// Node newRoot = new Node(t);
// x = splitNode(newRoot, x, 0);
// }

// return x;
// }

// private Node splitNode(Node x, Node y, int yNodeIndex) {

// Node z = new Node(t);
// int medianKey = y.keys.get(t);
// int yKeysSize = y.keys.size();
// int yChildrenSize = y.nodes.size();

// z.keys.addAll(y.keys.subList(t + 1, yKeysSize));
// y.keys.subList(t, yKeysSize).clear();

// if (!y.nodes.isEmpty()) {

// z.nodes.addAll(y.nodes.subList(t + 1, yChildrenSize));
// y.nodes.subList(t + 1, yChildrenSize).clear();
// }

// if (root == y) {
// x.keys.add(medianKey);
// x.nodes.add(y);
// x.nodes.add(z);
// } else {
// x.keys.add(yNodeIndex, medianKey);
// x.nodes.add(yNodeIndex + 1, z);
// }

// return x;
// }

// private boolean isLeafNode(Node x) {
// return x.nodes.isEmpty();
// }

// private int findKeyIndex(Node x, int key) {

// int index = 0;
// while (key < x.keys.size() && key > x.keys.get(index)) {
// index++;
// }
// return index;
// }

// }