
import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'getTotalX' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     * 1. INTEGER_ARRAY a
     * 2. INTEGER_ARRAY b
     */

    public static int gcd(int a, int b) {

        if (a == 0) {
            return b;
        }

        return gcd(b % a, a);

    }

    public static int lcm(int a, int b) {

        return (a * b) / gcd(a, b);

    }

    public static int getTotalX(List<Integer> a, List<Integer> b) {
        // Write your code here

        int count = 0;
        int answer = count;
        int gcdNum = 1;
        int lcmNum = 1;

        for (Integer num : a) {

            int num1 = num > lcmNum ? lcmNum : num;
            int num2 = num > lcmNum ? num : lcmNum;

            lcmNum = lcm(num1, num2);
        }

        gcdNum = b.get(0);

        for (Integer num : b) {

            int num1 = num > gcdNum ? gcdNum : num;
            int num2 = num > gcdNum ? num : gcdNum;

            gcdNum = gcd(num1, num2);
        }

        if (lcmNum > gcdNum || gcdNum % lcmNum != 0) {
            return answer;
        }

        for (int n = lcmNum; n <= gcdNum; n += lcmNum) {

            if (gcdNum % n == 0) {
                count++;
            }
        }
        answer = count;

        return answer;

    }

}

public class GetTotalX {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int m = Integer.parseInt(firstMultipleInput[1]);

        List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        List<Integer> brr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        int total = Result.getTotalX(arr, brr);

        bufferedWriter.write(String.valueOf(total));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
