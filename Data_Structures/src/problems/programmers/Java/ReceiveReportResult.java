package problems.programmers.Java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReceiveReportResult {
    public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];

        // 신고한놈들 -> 신고당한놈들 (k>=2)

        Map<String, Integer> reporters = new HashMap<>();
        Map<String, List<String>> targets = new HashMap<>();

        for (int i = 0; i < id_list.length; i++) {

            reporters.put(id_list[i], 0);
            List<String> list = targets.getOrDefault(id_list[i], new ArrayList<>());
            targets.put(id_list[i], list);

        }

        for (int i = 0; i < report.length; i++) {
            String reporter = report[i].split(" ")[0];
            String target = report[i].split(" ")[1];

            List<String> list = targets.get(target);

            if (!list.contains(reporter)) {
                list.add(reporter);
            }

            targets.put(target, list);
        }

        for (String username : targets.keySet()) {

            List<String> reports = targets.get(username);
            if (reports.size() >= k) {

                for (String reporter : reports) {
                    int count = reporters.get(reporter);
                    reporters.put(reporter, count + 1);
                }

            }

        }

        for (int i = 0; i < id_list.length; i++) {
            int value = reporters.getOrDefault(id_list[i], 0);
            answer[i] = value;
        }

        return answer;
    }
}
