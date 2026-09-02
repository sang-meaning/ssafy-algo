import java.util.*;

class Solution {
    public int solution(int[] elements) {
        int n = elements.length;
        int[] new_elements = new int[n * 2];

        for (int i = 0; i < new_elements.length; i++) {
            new_elements[i] = elements[i % n];
        }

        Set<Integer> sum_set = new HashSet<>();

        for (int start = 0; start < n; start++) {
            int sum = 0;

            for (int length = 1; length <= n; length++) {
                sum += new_elements[start + length - 1];
                sum_set.add(sum);
            }
        }

        return sum_set.size();
    }
}