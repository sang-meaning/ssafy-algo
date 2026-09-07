import java.util.HashSet;
import java.util.Set;

class Solution {
    public int solution(int[] elements) {
        int n = elements.length;
        Set<Integer> set = new HashSet<>();
        
        // 원형 수열 처리를 위해 배열을 두 배로 확장합니다.
        int[] extendedElements = new int[n * 2];
        for (int i = 0; i < n; i++) {
            extendedElements[i] = elements[i];
            extendedElements[i + n] = elements[i];
        }
        
        // 부분 수열의 길이 (1부터 n까지)
        for (int len = 1; len <= n; len++) {
            // 첫 번째 윈도우의 합 계산
            int currentSum = 0;
            for (int i = 0; i < len; i++) {
                currentSum += extendedElements[i];
            }
            set.add(currentSum);
            
            for (int i = 1; i < n; i++) {
                currentSum = currentSum - extendedElements[i - 1] + extendedElements[i + len - 1];
                set.add(currentSum);
            }
        }
        
        return set.size();
    }
}