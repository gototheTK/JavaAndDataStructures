package problems.programmers.Java;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Running_Race {

    public String[] solution(String[] players, String[] callings) {
        String[] answer = Arrays.copyOf(players, players.length);

        Map<String, Integer> ranks = new HashMap<>();

        for (int i = 0; i < players.length; i++) {
            ranks.put(players[i], i + 1);
        }

        for (String player : callings) {

            Integer rank = ranks.get(player);
            String overturned = answer[rank - 2];
            answer[rank - 2] = answer[rank - 1];
            answer[rank - 1] = overturned;
            ranks.put(answer[rank - 2], rank - 1);
            ranks.put(answer[rank - 1], rank);
        }

        return answer;
    }
}
