import java.util.HashSet;
import java.util.Set;

class Solution {
    public int solution(int[] elements) {
        Set<Integer> uniqueSums = new HashSet<>();
        int n = elements.length;

        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = 0; j < n; j++) {
                sum += elements[(i + j) % n];
                uniqueSums.add(sum);
            }
        }

        return uniqueSums.size();
    }
}