package problems.programmers.Java;

import java.util.*;

class HanoiTower {

    public static List<List<Integer>> list = new ArrayList<>();

    public int[][] solution(int n) {

        hanoi_tower(n, 1, 2, 3);
        int[][] answer = new int[list.size()][2];

        int index = 0;

        for (List<Integer> nums : list) {

            answer[index][0] = nums.get(0);
            answer[index][1] = nums.get(1);
            index++;
        }

        return answer;
    }

    public static void hanoi_tower(int n, int from, int temp, int to) {

        if (n == 1) {

            List<Integer> fromTo = new ArrayList<>();
            fromTo.add(from);
            fromTo.add(to);
            list.add(fromTo);

        } else {

            hanoi_tower(n - 1, from, to, temp);
            List<Integer> fromTo = new ArrayList<>();
            fromTo.add(from);
            fromTo.add(to);
            list.add(fromTo);
            hanoi_tower(n - 1, temp, from, to);

        }

    }

}
