import java.io.*;
import java.util.*;

public class Solution {

    public static long arrayManipulation(int n, List<List<Integer>> queries) {
        // Write your code here

        long[] numbers = new long[n];
        TreeSet<Integer> indexes = new TreeSet<>();
        long max = 0;

        List<Integer> temp = queries.remove(0);

        numbers[temp.get(0) - 1] = temp.get(2);
        numbers[temp.get(1) - 1] = temp.get(2);

        for (List<Integer> query : queries) {

            Integer from = query.get(0) - 1;
            Integer to = query.get(1) - 1;
            long value = query.get(2).longValue();

            Integer frontFrom = indexes.floor(from);
            frontFrom = null == frontFrom ? from : frontFrom;
            Integer backTo = indexes.ceiling(to);
            backTo = null == backTo ? to : backTo;

            indexes.add(from);
            indexes.add(to);

            Iterator<Integer> fromTo = indexes.subSet(frontFrom, true, backTo, false).iterator();

            while (fromTo.hasNext()) {

                Integer index = fromTo.next();
                numbers[index] += value;
                max = numbers[index] > max ? numbers[index] : max;

            }

        }

        return max;
    }

}
