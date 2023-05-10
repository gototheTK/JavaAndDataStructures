package problems.programmers.Sql;

public class length2Tiling {

    /**
     * 
     * 동적계획법을 이용하여 타일링문제 풀기
     * 
     * @param n
     * @return theNumberOfCases
     */

    public int solution(int n) {

        int[] nums = new int[n + 1];

        nums[0] = 1;
        nums[1] = 1;

        return tiling(n, nums);

    }

    public int tiling(int n, int[] nums) {

        if (0 > n) {
            return 0;
        }

        if (0 < nums[n]) {
            return nums[n] % 1000000007;
        }

        return nums[n] = (tiling(n - 1, nums) + tiling(n - 2, nums)) % 1000000007;

    }

}
