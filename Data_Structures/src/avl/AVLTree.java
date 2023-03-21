class Node{
    int key;
    Node left;
    Node right;
    int height = 0;
}


public class AVLTree{

    Node root;

    // 추가

    public void add(int key) {

        Node newNode = new Node();
        newNode.key = key;

        if(null == root) {
            root = newNode;
        }else {
            root = insertNode(root, newNode);
        }

    }

    private Node insertNode(Node node, Node newNode){

        if(null == node){
            return newNode;
        }else if(node.key > newNode.key){
            node.left = insertNode(node.left, newNode);
        }else {
            node.right = insertNode(node.right, newNode);
        }

        changeNodeHeight(node);

        return rotate(node);
    }

    // 삭제
    public void remove(int key) {
        root = removeNode(root, key);
    }

    private Node removeNode(Node node, int key) {

        if(null == node) {
            throw new RuntimeException("삭제하고자 하는 노드가 없습니다.");
        }else if(node.key > key) {
            node.left = removeNode(node.left, key);
        }else if(node.key < key) {
            node.right = removeNode(node.right, key);
        }else {

            if(null != node.left){

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
            }else{
                return null;
            }
        }
        changeNodeHeight(node);
        return rotate(node);
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

    private Node searchNode(Node node, int key){

        Node searchedNode = node;

        if(null == node){
            throw new RuntimeException("찾고자하는 노드가 존재하지 않습니다.");
        }else if(node.key > key) {
            searchedNode = searchNode(searchedNode.left, key);
        }else if(node.key < key) {
            searchedNode = searchNode(searchedNode.right, key);
        }

        return searchedNode;

    }

    // 회전

    private Node rotate(Node node) {

        int nodeBalance = getNodeBalance(node);

        if(Math.abs(nodeBalance)>=2) {

            if(nodeBalance>1 && 0<=getNodeBalance(node.left)) {
                node = LL_rotate(node);
            }else if(nodeBalance>1 && -1==getNodeBalance(node.left)) {
                node.left = RR_rotate(node.left);
                node = LL_rotate(node);
            }else if(nodeBalance<-1 && 0>=getNodeBalance(node.right)) {
                node = RR_rotate(node);
            }else if(nodeBalance<-1 && 1==getNodeBalance(node.right)) {
                node.right = LL_rotate(node.right);
                node = RR_rotate(node);
            }
            
        }
        return node;

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

    private Node LL_rotate(Node P) {
        Node L = P.left;
        Node LR = L.right;
        L.right = P;
        P.left = LR;
        changeNodeHeight(P);
        changeNodeHeight(L);
        return L;
    }

    // 높이, 밸런스,

    private void changeNodeHeight(Node node) {
        node.height = getNodeHeight(node);
    }

    private int getNodeHeight(Node node) {
        int leftChildHeight = (null != node.left) ? node.left.height : -1;
        int rightChildHeight = (null != node.right) ? node.right.height : -1;

        return Math.max(leftChildHeight, rightChildHeight) + 1;
    }

    private int getNodeBalance(Node node) {
        int leftChildHeight = (null != node.left) ? node.left.height : -1;
        int rightChildHeight = (null != node.right) ? node.right.height : -1;

        return leftChildHeight - rightChildHeight;
    }
    

    // 출력

    public void pirntTree(){
        printHelper(root, "", true);
        System.out.println("----------------------------------------------------------");
        System.out.println();
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

    public void traversal() {
        inorderTraversal(root);
        System.out.println("");
    }

    private void inorderTraversal(Node node) {

        if(null==node){
            return;
        }

        inorderTraversal(node.left);
        System.out.print(" "+node.key);
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
        avlTree.remove(9);
        avlTree.printTree();
        System.out.println("traversal");
        avlTree.traversal();

    }

}