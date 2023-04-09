
/**
 * RedBlackTree
 */
class Node {
    int key;
    Node left, right, parent;
    int color = 1;
}

public class RedBlackTree {

    private static final int BLACK = 0;
    private static final int RED = 1;

    private Node root;

    // 삽입
    public void add(int key) {
        Node node = new Node();
        node.key = key;

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
        } else if (x.key <= newNode.key && !isExist(x.right)) {
            x.right = newNode;
            newNode.parent = x;
        } else if (x.key > newNode.key) {
            insertNode(x.left, newNode);
        } else {
            insertNode(x.right, newNode);
        }

        insertFixUp(x);

    }

    private void insertFixUp(Node g) {

        if (isRED(g.right) && isRED(g.right.left) && isBLACK(g.left)) {
            LL_rotate(g.right);
            swapColor(g, g.right);
            RR_rotate(g);
        } else if (isRED(g.right) && isRED(g.right.right) && isBLACK(g.left)) {
            swapColor(g, g.right);
            RR_rotate(g);
        } else if (isRED(g.right) && (isRED(g.right.right) || isRED(g.right.left))) {
            g.color = RED;
            g.right.color = BLACK;
            g.left.color = BLACK;
        } else if (isRED(g.left) && isRED(g.left.right) && isBLACK(g.right)) {
            RR_rotate(g.left);
            swapColor(g, g.left);
            LL_rotate(g);
        } else if (isRED(g.left) && isRED(g.left.left) && isBLACK(g.right)) {
            swapColor(g, g.left);
            LL_rotate(g);
        } else if (isRED(g.left) && (isRED(g.left.left) || isRED(g.left.right))) {
            g.color = RED;
            g.left.color = BLACK;
            g.right.color = BLACK;
        }

    }

    // 삭제
    public void remove(int key) {
        removeNode(root, key);
    }

    private void removeNode(Node x, int key) {

        if (null == x) {
            throw new RuntimeException("해당하는 노드를 찾을 수 없습니다.");
        } else if (x.key > key) {
            removeNode(x.left, key);
        } else if (x.key < key) {
            removeNode(x.right, key);
        } else {

            if (isExist(x.left)) {
                Node predecessor = getLargestNode(x.left);
                int removeKey = x.key;
                x.key = predecessor.key;
                predecessor.key = removeKey;
                removeNode(x.left, key);
            } else if (isExist(x.right)) {
                Node successor = getSmallestNode(x.right);
                int removeKey = x.key;
                x.key = successor.key;
                successor.key = removeKey;
                removeNode(x.right, key);
            } else {

                if (root == x) {
                    root = null;
                } else {

                    if (isBLACK(x)) {
                        removeFixUp(x);
                    }

                    if (x.parent.left == x) {
                        x.parent.left = null;
                    } else if (x.parent.right == x) {
                        x.parent.right = null;
                    }

                    x.parent = null;

                }

            }

        }

    }

    /*
     * 삽입시 루트 노드색깔 검은색 고려
     * Grand Parent를 기준으로 insertFixUp을 함.
     * 레드 블랙 트리는 부모를 넣는것도 고려해야한다.
     * 삭제시 루트노드이면 루트노드만 null을 한다.
     * 삭제시 1번,3번케이스는 마지막에 형제를 재설정해줘야한다.
     * 삭제시 4번 케이스는 형제노드쪽을 기준으로 회전한다.
     * 삭제 고정시 x는 검은색으로 칠해줘야한다.
     */

    private void removeFixUp(Node x) {

        while (isBLACK(x) && root != x) {

            if (x.parent.right == x) {

                Node w = x.parent.left;

                if (isRED(w)) {
                    w.color = BLACK;
                    x.parent.color = RED;
                    LL_rotate(x.parent);
                    w = x.parent.left;
                }

                if (isBLACK(w.left) && isBLACK(w.right)) {
                    w.color = RED;
                    x = x.parent;
                } else {

                    if (isBLACK(w.left)) {
                        w.color = RED;
                        w.left.color = BLACK;
                        RR_rotate(w);
                        w = x.parent.left;
                    }

                    w.color = x.parent.color;
                    x.parent.color = BLACK;
                    w.left.color = BLACK;
                    LL_rotate(x.parent);
                    break;

                }

            } else if (x.parent.left == x) {

                Node w = x.parent.right;

                if (isRED(w)) {
                    w.color = BLACK;
                    x.parent.color = RED;
                    RR_rotate(x.parent);
                    x = x.parent.right;
                }

                if (isBLACK(w.left) && isBLACK(w.right)) {
                    w.color = RED;
                    x = x.parent;
                } else {

                    if (isBLACK(w.right)) {
                        w.left.color = BLACK;
                        w.color = RED;
                        LL_rotate(w);
                        w = x.parent.right;
                    }

                    w.color = x.parent.color;
                    x.parent.color = RED;
                    w.right.color = BLACK;
                    RR_rotate(x.parent);
                    break;

                }

            }

        }

        x.color = BLACK;

    }

    private Node getLargestNode(Node node) {
        if (!isExist(node.right)) {
            return node;
        }
        return getLargestNode(node.right);
    }

    private Node getSmallestNode(Node node) {
        if (!isExist(node.left)) {
            return node;
        }
        return getSmallestNode(node.left);
    }

    // 찾기
    public int search(int key) {
        return 0;
    }

    // 출력
    public void printTree() {
        printHelper(root, "", true);
    }

    // 필수기능

    private void printHelper(Node root, String indent, boolean last) {

        if (root != null) {
            System.out.print(indent);
            if (last) {
                System.out.print("R----");
                indent += "     ";
            } else {
                System.out.print("L----");
                indent += "|    ";
            }
            String sColor = root.color == RED ? "RED" : "BLACK";
            System.out.println(root.key + "(" + sColor + ")");
            printHelper(root.left, indent, false);
            printHelper(root.right, indent, true);
        }

    }

    private boolean isRED(Node x) {
        return (x != null) && (RED == x.color);
    }

    private boolean isBLACK(Node x) {
        return (x == null) || (BLACK == x.color);
    }

    private boolean isExist(Node x) {
        return null != x;
    }

    private void swapColor(Node nodeA, Node nodeB) {
        int temp = nodeA.color;
        nodeA.color = nodeB.color;
        nodeB.color = temp;
    }

    private Node LL_rotate(Node P) {

        Node GP = P.parent;
        Node L = P.left;
        Node LR = L.right;
        L.right = P;
        P.left = LR;

        P.parent = L;
        if (isExist(LR)) {
            LR.parent = P;
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

        R.parent = P;

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

    public static void main(String[] args) {
        RedBlackTree redBlackTree = new RedBlackTree();
        redBlackTree.add(5);
        redBlackTree.add(2);
        redBlackTree.add(9);
        redBlackTree.add(3);
        redBlackTree.add(7);
        redBlackTree.add(4);
        redBlackTree.add(8);
        redBlackTree.add(1);
        redBlackTree.add(6);
        redBlackTree.printTree();
        System.out.println("traversal");

        redBlackTree.remove(9);
        redBlackTree.printTree();
    }

}