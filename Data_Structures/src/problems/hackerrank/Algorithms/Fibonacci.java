package problems.hackerrank.Algorithms;

public class Fibonacci {
    public int solution(int n) {
        int answer = 0;
        int n_1 = 1;
        int n_2 = 1;

        for (int i = 2; i < n; i++) {

            answer = n_1 + n_2;
            n_2 = n_1 % 1234567;
            n_1 = answer % 1234567;

        }

        return answer % 1234567;
    }
}
