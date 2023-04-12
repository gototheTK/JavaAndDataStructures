package problems.hackerrank.Algorithms;

import java.util.ArrayList;
import java.util.List;

public class GradingStudents {

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

}
