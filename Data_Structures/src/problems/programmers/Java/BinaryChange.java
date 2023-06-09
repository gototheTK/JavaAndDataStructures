package problems.programmers.Java;

/**
 * BinaryChange
 */
public class BinaryChange {

    public int[] solution(String s) {
        int[] answer = new int[2];
        String binary = s;
        int countOfZero = 0;
        int count = 0;

        /**
         * 1. 0의 개수를 알아낸다.
         * 2. 0의 개수만큼 0을 제거한다.
         * 3. 0을 제거한 후 문자열의 길이를 이진변화한다.
         * 4. 문자열의 수가 1이 될때까지 위의 처리를 반복한다.
         **/

        while (!binary.equals("1")) {

            // 1. 0의 개수를 알아낸다.
            int zeros = binary.length() - binary.replaceAll("0", "").length();

            // 2. 0의 개수만큼 0을 제거한다.
            int length = binary.replaceAll("0", "").length();

            // 3. 0을 제거한 후 문자열의 길이를 이진변화한다.
            binary = Integer.toBinaryString(length);
            count++;
            countOfZero += zeros;
            System.out.println(binary + " : " + binary.equals("1"));
        }

        answer[0] = count;
        answer[1] = countOfZero;
        return answer;
    }

    public static void main(String[] args) {

        BinaryChange solution = new BinaryChange();

        int[] test = solution.solution("110010101001");

        System.out.println(
                "Test Case 1: " + test[0] + "/" + test[1]);

        test = solution.solution("01110");

        System.out.println(
                "Test Case 2: " + test[0] + "/" + test[1]);

        test = solution.solution("1111111");

        System.out.println(
                "Test Case 3: " + test[0] + "/" + test[1]);

    }

}