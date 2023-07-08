package problems.programmers.Java;

import java.util.*;

class Ponketmon {
    public int solution(int[] nums) {
        int answer = 0;

        Set<Integer> numbers = new HashSet<>();

        for (Integer num : nums) {
            numbers.add(num);
        }

        return numbers.size() <= nums.length / 2 ? numbers.size() : nums.length / 2;
    }
}