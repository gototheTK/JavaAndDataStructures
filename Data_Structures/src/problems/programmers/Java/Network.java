package problems.programmers.Java;

import java.util.*;

public class Network {

    public void union(int u, int v, int[] disjoint) {

        /**
         * int[][] computers= {
         * {1,0,0,0,0,0,1},
         * {0,1,1,0,1,0,0},
         * {0,1,1,1,0,0,0},
         * {0,0,1,1,0,0,0},
         * {0,1,0,0,1,1,0},
         * {0,0,0,0,1,1,1},
         * {1,0,0,0,0,1,1}
         * };
         * int n = 7;
         **/

        u = find(u, disjoint);
        v = find(v, disjoint);

        if (u > v) {
            disjoint[u] = ;
        } else {
            disjoint[v] = u;
        }

    }

    public int find(int v, int[] disjoint) {

        /**
         * int[][] computers= {
         * {1,0,0,0,0,0,1},
         * {0,1,1,0,1,0,0},
         * {0,1,1,1,0,0,0},
         * {0,0,1,1,0,0,0},
         * {0,1,0,0,1,1,0},
         * {0,0,0,0,1,1,1},
         * {1,0,0,0,0,1,1}
         * };
         * int n = 7;
         **/

        if (disjoint[v] == v) {
            return disjoint[v];
        } else {
            return disjoint[v] = find(disjoint[v], disjoint);
        }

    }

    public int solution(int n, int[][] computers) {
        int answer = 0;

        int[] disjoint = new int[n];
        for (int i = 0; i < n; i++) {
            disjoint[i] = i;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                if (computers[i][j] == 1 && find(i, disjoint) != find(j, disjoint)) {
                    union(i, j, disjoint);
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

        int[][] computers = {
                { 1, 0, 0, 0, 0, 0, 1 },
                { 0, 1, 1, 0, 1, 0, 0 },
                { 0, 1, 1, 1, 0, 0, 0 },
                { 0, 0, 1, 1, 0, 0, 0 },
                { 0, 1, 0, 0, 1, 1, 0 },
                { 0, 0, 0, 0, 1, 1, 1 },
                { 1, 0, 0, 0, 0, 1, 1 }
        };
        int n = 7;

        Network test = new Network();

        System.out.println("실행 결과 : " + test.solution(n, computers));

    }

}
