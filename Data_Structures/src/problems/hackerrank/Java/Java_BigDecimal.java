package problems.hackerrank.Java;

import java.math.BigDecimal;
import java.util.*;

class Java_BigDecimal {
    public static void main(String[] args) {
        // Input
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String[] s = new String[n + 2];
        for (int i = 0; i < n; i++) {
            s[i] = sc.next();
        }
        sc.close();

        s = Arrays.copyOf(s, n);

        Arrays.sort(s, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {

                // TODO Auto-generated method stub

                BigDecimal standard = new BigDecimal(o1);
                BigDecimal comparision = new BigDecimal(o2);
                return comparision.compareTo(standard);
            }
        });

        // Output
        for (int i = 0; i < n; i++) {
            System.out.println(s[i]);
        }
    }
}