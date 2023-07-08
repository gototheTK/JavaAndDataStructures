package problems.programmers.Java;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TableHashFunction {

    /**
     * @param data
     * @param col
     * @param row_begin
     * @param row_end
     * @return
     */
    public static int solution(int[][] data, int col, int row_begin, int row_end) {
        int answer = 0;
        int[] s_i = new int[row_end - row_begin + 1];

        Arrays.sort(data,
                (arg0, arg1) -> (arg0[col - 1] == arg1[col - 1]) ? (arg1[0] - arg0[0])
                        : (arg0[col - 1] - arg1[col - 1]));

        for (int i = 0; i < data.length; i++) {

            System.out.printf("%d %d %d \n", data[i][0], data[i][1], data[i][2]);
        }

        for (int i = row_begin; i <= row_end; i++) {

            for (int k = 0; k < data[i - 1].length; k++) {
                s_i[i - row_begin] += data[i - 1][k] % i;

            }
            System.out.println(s_i[i - row_begin]);
        }

        for (int i = 0; i < s_i.length; i++) {
            answer = answer ^ s_i[i];
        }

        return answer;

    }

    public static void main(String[] args) {

        int[][] data = { { 2, 2, 6 }, { 1, 5, 10 }, { 4, 2, 9 }, { 3, 8, 3 } };
        int answer = solution(data, 2, 2, 3);

        System.out.println(answer);
    }

}
