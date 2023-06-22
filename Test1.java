import java.util.HashMap;
import java.util.Map;

public class Test1 {

    public static void main(String[] args) {

        String[] input = new String[] {
                "1622025201 REQUEST 10001 192.168.0.1",
                "1622025201 REQUEST 10002 192.168.0.2",
                "1622025201 REQUEST 10003 192.168.0.1"
        };

        Map<String, Integer> mapIp = new HashMap<>();
        Map<String, Integer> mapRequestTime = new HashMap<>();

        for (String log : input) {

            String[] logs = log.split(" ");

            /** 아이피개수 */
            if (logs.length > 3) {
                String ip = logs[4];
                mapIp.put(ip, mapIp.getOrDefault(ip, 0));
            }

            for (String ip : mapIp.keySet()) {
                System.out.println(ip + " (" + mapIp.get(ip) + ")");
            }

            if ("REQUEST".equals(logs[1])) {

                Integer requestTime = Integer.parseInt(logs[0]);
                String id = logs[2];
                mapRequestTime.put(id, requestTime);
            } else {

                Integer requestTime = mapRequestTime.getOrDefault(logs[2], -1);
                Integer responseTime = Integer.parseInt(logs[0]);

                Integer seconds = responseTime - requestTime;

                System.out.println(
                        logs[2] + " " + requestTime + " " + (seconds < responseTime ? seconds + "sec" : "fail"));

            }

        }

    }

}
