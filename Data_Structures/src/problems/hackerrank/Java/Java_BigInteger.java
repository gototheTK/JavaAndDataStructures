package problems.hackerrank.Java;

import java.io.*;
import java.util.*;
import java.math.BigInteger;

public class Java_BigInteger {

    public static void main(String[] args) {
        /*
         * Enter your code here. Read input from STDIN. Print output to STDOUT. Your
         * class should be named Solution.
         */

        Scanner sc = new Scanner(System.in);

        BigInteger num1 = new BigInteger(sc.next());
        BigInteger num2 = new BigInteger(sc.next());

        System.out.println(num1.add(num2));
        System.out.println(num1.multiply(num2));

    }
}
