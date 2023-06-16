package problems.programmers.Java;

import java.util.HashSet;
import java.util.Set;

public class Network {

    public int solution(int n, int[][] computers) {
        int answer = 0;

        int[] disjoint = new int[n];
        for (int i = 0; i < n; i++) {
            disjoint[i] = i;
        }

        for (int i = 0; i < n; i++) {

            for (int j = i; j < n; j++) {

                if (computers[i][j] == 1) {
                    disjoint[j] = disjoint[i];
                }

            }

        }

        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < n; i++) {

            set.add(disjoint[i]);

        }
        answer = set.size();

        return answer;
    }

    public static void main(String[] args) {

        Network test = new Network();

        int[][] test1 = { { 1, 1, 0 }, { 1, 1, 0 }, { 0, 0, 1 } };

        test.solution(3, test1);

    }

}
