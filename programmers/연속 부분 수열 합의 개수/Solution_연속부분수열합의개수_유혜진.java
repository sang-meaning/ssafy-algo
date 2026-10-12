import java.util.HashSet;
import java.util.Set;

class Solution {
    public int solution(int[] elements) {
        int n = elements.length;
        
        // 중복을 제거하며 합을 저장할 Set
        Set<Integer> sumSet = new HashSet<>();
        
        // 원형 수열 처리를 위해 배열을 2배로 확장
        int[] extended = new int[n * 2];
        for (int i = 0; i < n; i++) {
            extended[i] = elements[i];
            extended[i + n] = elements[i];
        }
        
        // len: 연속 부분 수열의 길이 (1부터 n까지)
        for (int len = 1; len <= n; len++) {
            // start: 시작 인덱스 (0부터 n-1까지)
            for (int start = 0; start < n; start++) {
                int sum = 0;
                
                // start 위치부터 len 개수만큼의 합 계산
                for (int k = 0; k < len; k++) {
                    sum += extended[start + k];
                }
                
                sumSet.add(sum);
            }
        }
        
        // 서로 다른 부분 수열의 합 개수 반환
        return sumSet.size();
    }
}