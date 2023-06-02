package problems.programmers.java;

class Solution {
    public String solution(String s) {
        String answer = "";

        char last = ' ';
        char[] chars = s.toCharArray();

        for (int i = 0; i < chars.length; i++) {

            char ch = chars[i];

            if (last == ' ' && ch >= 'a' && ch <= 'z') {
                chars[i] -= 32;
            }

            if (last != ' ' && ch >= 'A' && ch <= 'Z') {
                chars[i] += 32;
            }

            last = ch;

        }

        return String.valueOf(chars);
    }
}