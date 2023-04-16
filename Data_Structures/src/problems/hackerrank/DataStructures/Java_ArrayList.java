import java.io.*;
import java.util.*;

public class Java_ArrayList {

    public static void main(String[] args) {
        /*
         * Enter your code here. Read input from STDIN. Print output to STDOUT. Your
         * class should be named Solution.
         */

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        List<List<String>> blocks = new ArrayList<>(n);
        sc.nextLine();
        for (int i = 0; i < n; i++) {

            List<String> list = Arrays.asList(sc.nextLine().split("\\s+"));
            blocks.add(list);

        }

        n = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < n; i++) {
            String[] temp = sc.nextLine().split("\\s+");
            int line = Integer.parseInt(temp[0]);
            int index = Integer.parseInt(temp[1]);
            if (blocks.size() > line && blocks.get(line - 1).size() > index) {
                System.out.println(blocks.get(line - 1).get(index));
            } else {
                System.out.println("ERROR!");
            }

        }

    }
}