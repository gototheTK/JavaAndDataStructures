
import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class FindMergePointofTwoLists {

    static class SinglyLinkedListNode {
        public int data;
        public SinglyLinkedListNode next;

        public SinglyLinkedListNode(int nodeData) {
            this.data = nodeData;
            this.next = null;
        }
    }

    static class SinglyLinkedList {
        public SinglyLinkedListNode head;
        public SinglyLinkedListNode tail;

        public SinglyLinkedList() {
            this.head = null;
            this.tail = null;
        }

        public void insertNode(int nodeData) {
            SinglyLinkedListNode node = new SinglyLinkedListNode(nodeData);

            if (this.head == null) {
                this.head = node;
            } else {
                this.tail.next = node;
            }

            this.tail = node;
        }
    }

    public static void printSinglyLinkedList(SinglyLinkedListNode node, String sep, BufferedWriter bufferedWriter)
            throws IOException {
        while (node != null) {
            bufferedWriter.write(String.valueOf(node.data));

            node = node.next;

            if (node != null) {
                bufferedWriter.write(sep);
            }
        }
    }

    // Complete the findMergeNode function below.

    /*
     * For your reference:
     *
     * SinglyLinkedListNode {
     * int data;
     * SinglyLinkedListNode next;
     * }
     *
     */
    static int findMergeNode(SinglyLinkedListNode head1, SinglyLinkedListNode head2) {

        int answer = 0;
        SinglyLinkedListNode curr1 = head1;

        SinglyLinkedListNode curr2 = head2;

        SinglyLinkedListNode temp = null;
        Map<SinglyLinkedListNode, SinglyLinkedListNode> map = new HashMap<>();

        while (null != curr1) {
            map.put(curr1, curr1);
            curr1 = curr1.next;
        }

        while (null != curr2) {
            temp = map.getOrDefault(curr2, null);
            if (temp == curr2) {
                answer = curr2.data;
                break;
            }
            curr2 = curr2.next;

        }

        return answer;

    }

    private static final Scanner scanner = new Scanner(System.in);
}