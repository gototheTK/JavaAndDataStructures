import java.text.NumberFormat;
import java.util.Arrays;

public class Test3 {

    public static void main(String[] args) {

        Object[][] records = new Object[][] {
                { "AAA", 1.23456, 123456 },
                { "BBBBB", 12.3, 123 },
                { "CCCC", 123.40, 1234 },
        };

        int first = 0;
        int second = 0;
        int third = 0;

        for (Object[] record : records) {
            if (((String) record[0]).length() > first) {
                first = ((String) record[0]).length();
            }

            if ((Double.toString((Double) record[1])).length() > second) {
                second = (Double.toString((Double) record[1])).length();
            }

            if ((Integer.toString((Integer) record[2])).length() > third) {
                third = (Integer.toString((Integer) record[2])).length();
            }
        }

        for (Object[] record : records) {

            System.out.printf("|" + "%-5s" + "|" + "%6.2f" + "|" + "%6d" + "|\n", record[0], record[1], record[2]);
        }

    }

}
