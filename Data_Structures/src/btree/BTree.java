package btree;

import java.time.ZoneId;
import java.time.zone.ZoneOffsetTransition;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

class Node {
    List<Integer> keys;
    List<Node> nodes;

    Node(int t) {
        this.keys = new ArrayList<>(2 * t);
        this.nodes = new ArrayList<>(2 * t);
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
        if (keyIndex < x.keys.size() && key == x.keys.get(keyIndex)) {
            throw new RuntimeException("동일한 키가 존재 합니다.");
        }

        if (isLeafNode(x)) {
            x.keys.add(key);
        } else {

            Node y = insertKey(x.nodes.get(keyIndex), key);
            if (t * 2 == y.keys.size()) {
                x = splitNode(x, y, keyIndex);
            }
        }

        if (root == x && t * 2 == x.keys.size()) {
            Node newRoot = new Node(t);
            x = splitNode(newRoot, x, 0);
        }

        return x;
    }

    private Node splitNode(Node x, Node y, int yNodeIndex) {
        /**
         * x: 부모 노드
         * y: 자식 노드
         * yNodeIndex: y 노드가 x 노드의 몇 번째 자식인지 알 수 있는 index
         * 
         * 분할
         * t
         * t-1 <= keys <= 2t-1
         * 분할 조건: keys == 2t
         * 중간값(medianKey) = t
         */
        Node z = new Node(t);
        int medianKey = y.keys.get(t);
        int yKeySize = y.keys.size();
        int yNodeSize = y.nodes.size();

        z.keys.addAll(y.keys.subList(t + 1, yKeySize));
        y.keys.subList(t, yKeySize).clear();

        if (!y.nodes.isEmpty()) {
            z.nodes.addAll(y.nodes.subList(t + 1, yNodeSize));
            y.nodes.subList(t + 1, yNodeSize).clear();
        }

        if (root == y) {
            x.keys.add(medianKey);
            x.nodes.add(y);
            x.nodes.add(z);
        } else {
            x.keys.add(yNodeIndex, medianKey);
            x.nodes.add(z);
        }
        return x;
    }

    private boolean isLeafNode(Node x) {
        return x.nodes.isEmpty();
    }

    private int findKeyIndex(Node x, int key) {
        int i = 0;
        while (i < x.keys.size() && x.keys.get(i) < key) {
            i++;
        }
        return i;
    }

    public void remove(int key) {
        removeKey(root, key);
    }

    private void removeKey(Node x, int key) {
        int keyIndex = findKeyIndex(x, key);
        if (keyIndex < x.keys.size() && x.keys.get(keyIndex) == key) {

            if (isLeafNode(x)) {
                case1(x, keyIndex);
            }

        } else {

        }

    }

    private void case1(Node x, int keyIndex) {
        x.keys.remove(keyIndex);
    }

    private void case2(Node x, int keyIndex) {
        int key = x.keys.get(keyIndex);
        Node y = x.nodes.get(keyIndex);
        Node z = x.nodes.get(keyIndex + 1);

        if (y.nodes.size() >= t) {
            int removeKey = getLargestKey(y);
            x.keys.set(keyIndex, removeKey);
            removeKey(y, removeKey);
        } else if (z.nodes.size() >= t) {
            int removeKey = getSmallestKey(z);
            x.keys.set(keyIndex, removeKey);
            removeKey(z, removeKey);
        } else {
            Node newNode = merge(x, keyIndex);
            removeKey(newNode, key);
        }
    }

    private void case3(Node x, int key, int keyIndex) {

        boolean isExistLeftChild = 0 != keyIndex;
        boolean isExistRightChild = keyIndex != x.nodes.size() - 1;

        if (isExistLeftChild && x.nodes.get(keyIndex - 1).keys.size() >= t) {

            Node y = x.nodes.get(keyIndex - 1);
            Node z = x.nodes.get(keyIndex);
            int parentKey = x.keys.get(keyIndex - 1);
            int yLastKey = y.keys.get(y.keys.size() - 1);

            x.keys.set(keyIndex - 1, yLastKey);
            y.keys.remove(y.keys.size() - 1);
            z.keys.add(0, parentKey);

            if (!isLeafNode(y)) {
                Node yLastChildNode = y.nodes.get(y.nodes.size() - 1);

                z.nodes.add(0, yLastChildNode);
                y.nodes.remove(yLastChildNode);
            }

            removeKey(z, key);

        } else if (isExistRightChild && x.nodes.get(keyIndex + 1).keys.size() >= t) {

            Node y = x.nodes.get(keyIndex);
            Node z = x.nodes.get(keyIndex + 1);
            int parentKey = x.keys.get(keyIndex);
            int zFirstKey = z.keys.get(0);

            x.keys.set(keyIndex, zFirstKey);
            z.keys.remove(0);
            y.keys.add(parentKey);

            if (!isLeafNode(z)) {
                Node zFirstChildNode = z.nodes.get(0);
                y.nodes.add(zFirstChildNode);
                z.nodes.remove(zFirstChildNode);
            }

            removeKey(y, key);

        } else {

            Node newNode = merge(x, keyIndex);
            removeKey(newNode, key);

        }

    }

    private Node merge(Node x, int keyIndex) {

        Node y = x.nodes.get(keyIndex);
        Node z = x.nodes.get(keyIndex + 1);

        int key = x.keys.get(keyIndex);
        x.keys.remove(keyIndex);
        y.keys.add(key);

        y.keys.addAll(z.keys);
        y.nodes.addAll(z.nodes);
        x.nodes.remove(z);

        if (x.keys.isEmpty()) {
            x.nodes.clear();
            root = y;
        }

        return y;
    }

    private int getLargestKey(Node node) {
        return 0;
    }

    private int getSmallestKey(Node node) {
        PriorityQueue<Integer> a = new PriorityQueue<>();

        return 0;
    }

    public void traversal() {
        inorderTraversal(root);
        System.out.println("");
    }

    private void inorderTraversal(Node node) {
        // TODO : 중위 순회
        for (int i = 0; i < node.keys.size(); i++) {
            if (!node.nodes.isEmpty()) {
                inorderTraversal(node.nodes.get(i));
            }
            System.out.printf("%d ", node.keys.get(i));
        }
        if (!node.nodes.isEmpty()) {
            inorderTraversal(node.nodes.get(node.nodes.size() - 1));
        }
    }

    public void printTree() {
        printTree(root, 0, "");
    }

    private void printTree(Node node, int index, String indent) {
        printKey(node, index, indent);
        if (!isLeafNode(node)) {
            for (int i = 0; i < node.nodes.size(); i++) {
                printTree(node.nodes.get(i), i, indent + "  ");
            }
        }
    }

    private void printKey(Node node, int index, String indent) {
        if (root != node) {
            System.out.printf("%sㄴ[C%d]", indent, index);
        } else {
            System.out.print("  [R]  ");
        }

        for (int key : node.keys) {

            System.out.printf("%d", key);

        }

        System.out.println();

    }

}