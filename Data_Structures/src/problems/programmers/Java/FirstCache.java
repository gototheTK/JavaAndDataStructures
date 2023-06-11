package problems.programmers.Java;

import java.util.LinkedList;

public class FirstCache {

    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        LinkedList<String> queue = new LinkedList<>();

        if (cacheSize == 0) {
            return 5 * cities.length;
        }

        for (String city : cities) {

            if (queue.contains(city.toLowerCase())) {
                answer += 1;
                String temp = queue.remove(queue.indexOf(city.toLowerCase()));
                queue.add(temp);
            } else if (queue.size() >= cacheSize) {
                answer += 5;
                queue.removeFirst();
                queue.add(city.toLowerCase());
            } else {
                answer += 5;
                queue.add(city.toLowerCase());
            }

        }

        return answer;
    }

}
