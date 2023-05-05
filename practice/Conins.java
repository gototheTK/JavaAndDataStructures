package practice;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Conins {

    /**
     * @param conins
     * @return
     */
    public Integer coninCases(Integer sum, List<Integer> conins) {

        Integer cases = 0;
        List<Integer> stack = new LinkedList<>();
        Collections.sort(conins, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                // TODO Auto-generated method stub
                return o2 - o1;
            }
        });
        Integer coin = conins.


        while (!conins.isEmpty()&&) {

        }

        return cases;
    }

    public static void main(String[] args) {

    }

}
