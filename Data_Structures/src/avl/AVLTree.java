
class Node {
    int key;
    Node right;
    Node left;
    int height = 0;
}

public class AVLTree {

    Node root;

    // 추가
    public void add(int key) {

        Node newNode = new Node();
        newNode.key = key;

        if(null == root){
            root = newNode;
        }else{
            root = insertNode(root, newNode);
        }

    }

    private Node insertNode(Node node, Node newNode) {

        if(null == node){
            return newNode;
        }else if(node.key > newNode.key){
            node.left = insertNode(node.left, newNode);
        }else if(node.key < newNode.key){
            node.right = insertNode(node.right, newNode);
        }

        return node;

    }


    // 삭제

    public void remove(int key) {
        root = removeNode(root, key);
    }

    public Node removeNode(Node node, int key) {

        if(null == node) {
            throw new RuntimeException("해당하는 노드가 존재하지 않습니다.");
        }else if(node.key > key) {
            node.left = removeNode(node.left, key);
        }else if(node.key < key) {
            node.right = removeNode(node.right, key);
        }else {

            if(null != node.left) {

                Node predecessor = getLargestNode(node.left);
                int removeKey = node.key;
                node.key = predecessor.key;
                predecessor.key = removeKey;
                node.left = removeNode(node.left, key);
                
            }else if(null != node.right) {

                Node successor = getSmallestNode(node.right);
                int removeKey = node.key;
                node.key = successor.key;
                successor.key = removeKey;
                node.right = removeNode(node.right, key);

            }else {
                return null;
            }

        }

        return node;

    }

    private Node getLargestNode(Node node) {
        if(null == node.right) {
            return node;
        }
        
        return getLargestNode(node.right);
    }

    private Node getSmallestNode(Node node) {
        if(null == node.left) {
            return node;
        }

        return getSmallestNode(node.left);
    }


    // 검색
    public int search(int key) {
        return searchNode(root, key).key;
    }

    private Node searchNode(Node node, int key) {

        Node searchedNode = node;

        if(null == node){
            throw new RuntimeException("찾고하는 노드가 존재하지 않습니다.");
        }else if(node.key > key){
            searchedNode = searchNode(node.left, key);
        }else if(node.key < key){
            searchedNode = searchNode(node.right, key);
        }

        return searchedNode;

    }

    // 출력
    public void printTree() {

        printHelper(root, "", true);

    }

    private void printHelper(Node node, String indent, boolean last) {
        
        if(null != node) {
            System.out.print(indent);
            if(last) {
                System.out.print("R----");
                indent += "     ";
            } else {
                System.out.print("L----");
                indent += "|    ";
            }

            System.out.println(node.key + "(h:" + node.height + ")");
            printHelper(node.left, indent, false);
            printHelper(node.right, indent, true);

        }

    }

    public void traversal() {
        inorderTraversal(root);
        System.out.println();
        System.out.println();
    }

    private void inorderTraversal(Node node) {
        if(null == node) {
            return;
        }
        inorderTraversal(node.left);
        System.out.print(" " + node.key);
        inorderTraversal(node.right);

    }

    public static void main(String[] args) {

        AVLTree avlTree = new AVLTree();
        avlTree.add(5);
        avlTree.add(2);
        avlTree.add(9);
        avlTree.add(3);
        avlTree.add(7);
        avlTree.add(4);
        avlTree.add(8);
        avlTree.add(1);
        avlTree.add(6);
        avlTree.printTree();
        avlTree.remove(7);
        avlTree.printTree();
        System.out.println("traversal");
        avlTree.traversal();

    }
    


}