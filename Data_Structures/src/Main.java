
import btree.BTree;
import hashtable.LinearProbingHashTable;

public class Main {

    public static void main(String[] args) {

        // TODO : Linear Probing이 적용된 해시 테이블
        // System.out.println("Linear Probing");
        // LinearProbingHashTable linearProbingHashTable = new
        // LinearProbingHashTable(16);
        // linearProbingHashTable.put("hi1", 1);
        // linearProbingHashTable.put("hi2", 2);
        // linearProbingHashTable.put("hi3", 3);
        // linearProbingHashTable.put("hi10", 10);
        // linearProbingHashTable.put("hi11", 11);
        // linearProbingHashTable.put("hi12", 12);
        // linearProbingHashTable.put("hi13", 13);
        // linearProbingHashTable.put("hi14", 14);
        // linearProbingHashTable.printHashTable();
        // linearProbingHashTable.remove("hi12");
        // linearProbingHashTable.printHashTable();
        // System.out.printf("find key:%s value:%s\n\n", "hi11",
        // linearProbingHashTable.get("hi11"));

        // // TODO : Quadratic Probing이 적용된 해시 테이블
        // System.out.println("Quadratic Probing");
        // QuadraticProbingHashTable quadraticProbingHashTable = new
        // QuadraticProbingHashTable(16);
        // quadraticProbingHashTable.put("hi1", 1);
        // quadraticProbingHashTable.put("hi2", 2);
        // quadraticProbingHashTable.put("hi3", 3);
        // quadraticProbingHashTable.put("hi10", 10);
        // quadraticProbingHashTable.put("hi11", 11);
        // quadraticProbingHashTable.put("hi12", 12);
        // quadraticProbingHashTable.put("hi13", 13);
        // quadraticProbingHashTable.put("hi14", 14);
        // quadraticProbingHashTable.printHashTable();
        // quadraticProbingHashTable.remove("hi12");
        // quadraticProbingHashTable.printHashTable();
        // System.out.printf("find key:%s value:%s\n", "hi10",
        // quadraticProbingHashTable.get("hi10"));

        BTree bTree = new BTree(2);
        bTree.add(3);
        bTree.add(4);
        bTree.add(5);
        bTree.add(1);
        bTree.add(2);
        bTree.add(6);
        bTree.add(8);
        bTree.add(9);
        bTree.add(7);
        bTree.add(10);
        bTree.add(12);
        bTree.add(13);
        bTree.add(11);
        bTree.add(14);
        bTree.add(15);

        bTree.remove(5);
        bTree.remove(6);
        bTree.remove(7);
        bTree.remove(4);
        bTree.remove(3);
        bTree.remove(1);

        bTree.printTree();
        bTree.traversal();

    }

}