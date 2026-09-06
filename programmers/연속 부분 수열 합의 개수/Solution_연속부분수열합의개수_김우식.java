import java.util.*;

class Solution {
    public int solution(int[] elements) {

        Set<Integer> set = new HashSet<>();

        int N = elements.length;

        for (int i = 1; i <= N; i++) {

            for (int j = 0; j < N; j++) {

                int sum = 0;

                for (int k = 0; k < i; k++) {

                    int idx = (j + k) % N;

                    sum += elements[idx];
                }

                set.add(sum);
            }
        }

        return set.size();
    }
}