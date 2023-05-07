import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Coins {

    /**
     * @param conins
     * @return
     */
    public static Integer coninCases(Integer sum, List<Integer> bag) {

        /**
         * 1. 동전들을 내림 차순 정렬한다.
         * 2. 가방에서 큰 동전을 꺼내서 sum과의 몫과 나머지를 구한다.
         * 3. 2에서 구한 나머지와 다시 모듈 연산을 하여 적절한 값들을 큰값부터 시작해서 찾는다.
         * 4. 찾았거나 못찾았거나 모든 동전의 경우를 다 시험해봤다면, 가방에서 큰 동전을 다시 꺼내 2~3번을 가방이 빌때까지 반복한다.
         */

        Integer answer = 0;
        Integer divisor = 0;
        Integer quotient = 0;
        Integer remainder = 0;
        Integer coin = 0;
        Integer index = 0;

        /**
         * 
         * 내림차순 정렬
         */
        Collections.sort(bag, new Comparator<Integer>() {

            @Override
            public int compare(Integer o1, Integer o2) {
                // TODO Auto-generated method stub
                return o2 - o1;
            }

        });

        while (!bag.isEmpty()) {

            if (0 == quotient) {
                divisor = bag.remove(0);
                quotient = (Integer) sum / divisor;
                remainder = sum % divisor;
            }

            coin = !bag.isEmpty() && 0 != remainder ? bag.get(index) : divisor;

            System.out.println(divisor + " : " + remainder + " % " + coin + " = " + remainder % coin);

            answer = remainder % coin == 0 ? answer + 1 : answer;
            remainder += divisor;
            index++;

            if (bag.size() == index) {
                index = 0;
                remainder += coin;
                quotient--;
            }

        }

        return answer;
    }

    public static void main(String[] args) {

        List<Integer> bag1 = new ArrayList<>(1000);
        bag1.add(1);
        bag1.add(2);
        bag1.add(3);

        System.out.println("합계 : " + coninCases(4, bag1));

        List<Integer> bag2 = new ArrayList<>(1000);
        bag2.add(2);
        bag2.add(5);
        bag2.add(3);
        bag2.add(6);

        System.out.println("합계 : " + coninCases(10, bag2));

    }

}
