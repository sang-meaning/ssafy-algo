import java.util.*;

class Solution {
    public int solution(int[] elements) {
        Set<Integer> set = new HashSet<>();
        int n = elements.length;
        
        for (int i = 0; i < n; i++){
            int sum = 0;
            for (int j = 0; j < n; j++){
                int index = ( i + j ) % n;
                sum += elements[index];
                set.add(sum);
            }
        }
        return set.size();
    }
}