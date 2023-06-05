package problems.programmers.Java;

import java.util.LinkedList;
import java.util.List;

public class Correct_Parenthesis {

    boolean solution(String s) {

        LinkedList<Character> stack = new LinkedList<Character>();

        for (Character ch : s.toCharArray()) {

            if (!stack.isEmpty() && (stack.peek() == '(' && ch == ')')) {
                stack.removeLast();
            } else {
                stack.add(ch);
            }

        }

        return stack.isEmpty() ? true : false;

    }

}
