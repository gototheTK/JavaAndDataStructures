package problems.programmers.Java;

public class GoForWalk {

    public int[] solution(String[] park, String[] routes) {
        int[] answer = {};
        int[] coordinate = { 0, 0 };

        for (int i = 0; i < park.length; i++) {
            if (park[i].contains("S")) {
                coordinate[0] = i;
                coordinate[1] = park[i].indexOf("S");
            }
        }

        for (String route : routes) {

            String[] temp = route.split(" ");
            int[] vector = getVector(temp[0], temp[1]);

            boolean isPossible = isPossibleMoving(park, temp, coordinate);
            if (isPossible) {
                coordinate[0] += vector[0];
                coordinate[1] += vector[1];
            }

        }

        answer = coordinate;
        return answer;
    }

    public boolean isPossibleMoving(String[] park, String[] vector, int[] coordinate) {

        String ward = vector[0];
        int scala = Integer.parseInt(vector[1]);

        for (int i = 1; i <= scala; i++) {

            if (ward.equals("N")) {

                if (0 > coordinate[0] - i || park[coordinate[0] - i].charAt(coordinate[1]) == 'X') {
                    return false;
                }

            } else if (ward.equals("E")) {

                if (park[0].length() - 1 < coordinate[1] + i || park[coordinate[0]].charAt(coordinate[1] + i) == 'X') {
                    return false;
                }

            } else if (ward.equals("S")) {

                if (park.length - 1 < coordinate[0] + i || park[coordinate[0] + i].charAt(coordinate[1]) == 'X') {
                    return false;
                }

            } else if (ward.equals("W")) {

                if (0 > coordinate[1] - i || park[coordinate[0]].charAt(coordinate[1] - i) == 'X') {
                    return false;
                }

            }

        }

        return true;
    }

    public int[] getVector(String ward, String scala) {

        int step = Integer.parseInt(scala);
        int[] result = { 0, 0 };

        if (ward.equals("N")) {
            int[] temp = { -1 * step, 0 };
            result = temp;
        } else if (ward.equals("E")) {
            int[] temp = { 0, 1 * step };
            result = temp;
        } else if (ward.equals("S")) {
            int[] temp = { 1 * step, 0 };
            result = temp;
        } else if (ward.equals("W")) {
            int[] temp = { 0, -1 * step };
            result = temp;
        }

        return result;
    }

}
