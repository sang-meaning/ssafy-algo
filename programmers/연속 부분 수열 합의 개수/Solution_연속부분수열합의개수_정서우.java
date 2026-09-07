import java.util.*;

class Solution_연속부분수열합의개수_정서우 {
    public int solution(int[] elements) {
        int answer = 0;

        Set<Integer> set = new HashSet<>();
        int len = elements.length;
        int[] elements2 = new int[len * 2];

        for (int i = 0; i < len * 2; i++) {
            elements2[i] = elements[i % len];
        }

        for (int l = 1; l <= len; l++) {
            for (int start = 0; start < len; start++) {
                int sum = 0;

                for (int i = 0; i < l; i++) {
                    sum += elements2[start + i];
                }

                set.add(sum);
            }
        }

        answer = set.size();
        return answer;
    }
}