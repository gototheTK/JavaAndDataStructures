import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Test1 {

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

            char[] tuple = new char[first + second + third + 4];
            Arrays.fill(tuple, ' ');
            tuple[0] = '|';
            char[] temp0 = ((String) record[0]).toCharArray();
            System.arraycopy(temp0, 0, tuple, 1, temp0.length);
            tuple[first + 1] = '|';

            char[] temp1 = String.format("%" + second + ".2f", (Double) record[1]).toCharArray();
            System.arraycopy(temp1, 0, tuple, first + 2, temp1.length);
            tuple[first + second + 2] = '|';

            char[] temp2 = String.format("%" + third + "d", (Integer) record[2]).toCharArray();
            System.arraycopy(temp2, 0, tuple, first + second + 3, temp2.length);
            tuple[first + second + third + 3] = '|';

            System.out.println(String.valueOf(tuple));
        }

    }

    // public static void main(String[] args) {

    // String[] input = new String[] {
    // "1622025201 REQUEST 10001 192.168.0.1",
    // "1622025201 REQUEST 10002 192.168.0.2",
    // "1622025201 REQUEST 10003 192.168.0.1"
    // };

    // Map<String, Integer> mapIp = new HashMap<>();
    // Map<String, Integer> mapRequestTime = new HashMap<>();

    // for (String log : input) {

    // String[] logs = log.split(" ");

    // /** 아이피개수 */
    // if (logs.length > 3) {
    // String ip = logs[4];
    // mapIp.put(ip, mapIp.getOrDefault(ip, 0));
    // }

    // for (String ip : mapIp.keySet()) {
    // System.out.println(ip + " (" + mapIp.get(ip) + ")");
    // }

    // if ("REQUEST".equals(logs[1])) {

    // Integer requestTime = Integer.parseInt(logs[0]);
    // String id = logs[2];
    // mapRequestTime.put(id, requestTime);
    // } else {

    // Integer requestTime = mapRequestTime.getOrDefault(logs[2], -1);
    // Integer responseTime = Integer.parseInt(logs[0]);

    // Integer seconds = responseTime - requestTime;

    // System.out.println(
    // logs[2] + " " + requestTime + " " + (seconds < responseTime ? seconds + "sec"
    // : "fail"));

    // }

    // }

    // }

}
