package problems.hackerrank.Java;

import java.io.*;
import java.util.*;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class Java_Subarray {

    public static void main(String[] args) throws IOException {
        /*
         * Enter your code here. Read input from STDIN. Print output to STDOUT. Your
         * class should be named Solution.
         */

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int answer = 0;

        List<Integer> list = new ArrayList<>(100);

        for (int i = 0; i < n; i++) {

            list.add(sc.nextInt());

        }

        for (int i = 0; i <= n; i++) {

            for (int j = i; j <= n; j++) {
                int sum = list.subList(i, j).stream().mapToInt((a) -> {
                    return a;
                }).sum();

                if (sum < 0) {
                    answer++;
                }

            }

        }

        System.out.println(answer);

    }
}
