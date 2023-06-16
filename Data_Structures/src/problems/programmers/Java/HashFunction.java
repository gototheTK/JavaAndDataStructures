package problems.programmers.Java;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class HashFunction {

    /**
     * @param data
     * @param col
     * @param row_begin
     * @param row_end
     * @return
     */
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        int answer = 0;

        TreeMap<Integer, List<List<Integer>>> treeMap = new TreeMap<>();
        for (int[] tuple : data) {

            List<List<Integer>> treeTuple = treeMap.getOrDefault(tuple[col], new ArrayList<List<Integer>>());

            treeTuple.add(Arrays.stream(tuple).boxed().collect(Collectors.toList()));

            treeTuple.sort(new Comparator<List<Integer>>() {

                @Override
                public int compare(List<Integer> o1, List<Integer> o2) {
                    // TODO Auto-generated method stub
                    return o2.get(col) - o1.get(col);
                }

            });

            treeMap.put(tuple[col], treeTuple);
        }

        Integer index = 0;
        Iterator iterator = treeMap.entrySet().forEach(null);;

        while(iterator


        return answer;

    }

}
