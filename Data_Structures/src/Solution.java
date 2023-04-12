import java.io.*;
import java.util.*;

public class Solution {

    /*
     * Complete the 'gradingStudents' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts INTEGER_ARRAY grades as parameter.
     */

    public static List<Integer> gradingStudents(List<Integer> grades) {
        // Write your code here
        // First, Making a loop.
        // Second, Fetching the grades.
        // Third, Rounding a grade if the grade is less than 5 Or Passing.
        // Fourth, Returning to First until the rest of the grades for process.
        // Fifth, Returning the grades.

        /*
         * num % 5 < 3
         * 5 - (num % 5)
         */

        List<Integer> finalGrades = new ArrayList<Integer>();

        for (Integer grade : grades) {

            if (grade.intValue() > 37 && grade.intValue() % 5 < 3) {
                grade = new Integer(grade += 5 - (grade % 5));
            }
            ;

            finalGrades.add(grade);

        }

        return finalGrades;

    }

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
