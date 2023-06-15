package problems.programmers.Java;

import java.util.*;

/**
 * Processes
 */
public class Processes {

    public int solution(int[] priorities, int location) {
        int answer = 0;
        int index = location;
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                // TODO Auto-generated method stub
                return o2 - o1;
            }
        });

        LinkedList<Integer> queue = new LinkedList<>();

        for (int i = 0; i < priorities.length; i++) {
            priorityQueue.add(priorities[i]);
            queue.add(priorities[i]);
        }

        while (!queue.isEmpty()) {

            if (queue.peek() == priorityQueue.peek()) {
                priorityQueue.remove();
                answer++;
                if (queue.removeFirst() == priorities[location] && index == 0) {
                    break;
                }
            } else {
                queue.add(queue.removeFirst());

            }
            index = index > 0 ? index - 1 : queue.size() - 1;
        }

        return answer;
    }

    public static void main(String[] args) {

        Processes temp = new Processes();
        // int[] test1 = { 2, 1, 3, 2 };
        // System.out.println("return: " + temp.solution(test1, 2));
        int[] test2 = { 1, 1, 9, 1, 1, 1 };
        System.out.println("return: " + temp.solution(test2, 0));
    }

}