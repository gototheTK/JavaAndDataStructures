import javax.lang.model.element.Element;

class Node {
    int key;
    Node left, right, parent;
    int height = 0;
}

public class AVLTree2 {

    Node root;

    // 추가
    public void add(int key) {

        Node newNode = new Node();
        newNode.key = key;

        if (null == root) {
            root = newNode;
        } else {
            insertNode(root, newNode);
        }

    }

    private Node insertNode(Node x, Node newNode) {

        if (null == x) {
            return newNode;
        } else if (x.key > newNode.key) {
            x.left = insertNode(x.left, newNode);
        } else {
            x.right = insertNode(x.right, newNode);
        }
        changeNodeHeight(x);
        return rotate(x);

    }

    public void remove(int key) {
        root = removeNode(root, key);
    }

    private Node removeNode(Node x, int key) {

        if (null == x) {
            throw new RuntimeException("해당하는 노드가 존재하지 않습니다.");
        } else if (x.key > key) {
            x.left = removeNode(x.left, key);
        } else if (x.key < key) {
            x.right = removeNode(x.right, key);
        } else {

            if (null != x.left) {

                Node predecessor = getLargestNode(x.left);
                int removeKey = x.key;
                x.key = predecessor.key;
                predecessor.key = removeKey;
                x.left = removeNode(x.left, key);

            } else if (null != x.right) {
                Node successor = getSmallestNode(x.right);
                int removeKey = x.key;
                x.key = successor.key;
                successor.key = removeKey;
                x.right = removeNode(x.right, key);
            } else {
                return null;
            }

        }
        changeNodeHeight(x);
        return rotate(x);

    }

    private Node getLargestNode(Node x) {
        if (null == x.right) {
            return x;
        }
        return getLargestNode(x.right);
    }

    private Node getSmallestNode(Node x) {
        if (null == x.left) {
            return x;
        }
        return getSmallestNode(x);
    }

    private Node rotate(Node x) {

        int nodeBalance = getNodeBalance(x);

        if (Math.abs(nodeBalance) >= 2) {

            if (nodeBalance > 1 && 0 <= getNodeBalance(x.left)) {
                x = LL_rotate(x);
            } else if (nodeBalance > 1 && -1 == getNodeBalance(x.left)) {
                x.left = RR_rotate(x.left);
                x = LL_rotate(x);
            } else if (nodeBalance < -1 && 0 >= getNodeBalance(x.right)) {
                x = RR_rotate(x);
            } else if (nodeBalance < -1 && 1 == getNodeBalance(x.right)) {
                x.right = LL_rotate(x.right);
                x = RR_rotate(x);
            }

        }

        return x;

    }

    private Node LL_rotate(Node P) {

        Node L = P.left;
        Node LR = L.right;
        L.right = P;
        P.left = LR;

        changeNodeHeight(P);
        changeNodeHeight(L);

        return L;

    }

    private Node RR_rotate(Node P) {

        Node R = P.right;
        Node RL = R.left;
        R.left = P;
        P.right = RL;

        changeNodeHeight(P);
        changeNodeHeight(R);

        return R;

    }

    private void changeNodeHeight(Node x) {

        x.height = getNodeHeight(x);

    };

    private int getNodeHeight(Node x) {
        int leftHeight = (null == x.left) ? -1 : x.left.height;
        int rightHeight = (null == x.right) ? -1 : x.right.height;

        return Math.max(leftHeight, rightHeight) + 1;
    }

    private int getNodeBalance(Node x) {
        int leftHeight = (null == x.left) ? -1 : x.left.height;
        int rightHeight = (null == x.right) ? -1 : x.right.height;
        return leftHeight - rightHeight;
    }

    private void printHelper(Node x, String indent, boolean last) {
        if (x != null) {
            System.out.print(indent);
            if (last) {
                System.out.print("R----");
                indent += "   ";
            } else {
                System.out.print("L----");
                indent += "|  ";
            }

            System.out.println(x.key + "(h:" + x.height + ")");
            printHelper(x.left, indent, false);
            printHelper(x.right, indent, true);
        }
    }

    public void printTree() {
        printHelper(this.root, "", true);
    }

    public static void main(String[] args) {
        AVLTree2 avlTree = new AVLTree2();
        avlTree.add(5);
        avlTree.add(2);
        avlTree.printTree();
        avlTree.add(9);
        avlTree.add(3);
        avlTree.add(7);
        avlTree.add(4);
        avlTree.add(8);
        avlTree.add(1);
        avlTree.add(6);
        avlTree.printTree();
        System.out.println("traversal");

        avlTree.remove(9);
        avlTree.printTree();
    }

}