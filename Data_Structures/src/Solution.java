import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String s = scan.nextLine();
        // Write your code here.
        if (s.length() == 0) {
            System.out.println("0");
            return;
        }
        String[] strs = s.split("[\\s!,?._'@]+");

        if (strs[0].equals("")) {
            System.out.println(strs.length - 1);
        } else {
            System.out.println(strs.length);
        }

        for (String str : strs) {
            if (!str.equals("")) {
                System.out.println(str.length());
                System.out.println(str);
            }

        }
        scan.close();
    }
}