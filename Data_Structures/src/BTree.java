import java.util.ArrayList;
import java.util.List;

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

}