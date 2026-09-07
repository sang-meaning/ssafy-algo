import java.util.*;

class Solution {
    static int answer = 0;
    
    public int solution(int[] numbers, int target) {
        
        
        // depth 와 현재 합, 종료조건(target) 필요
        dfs(0, 0, target, numbers);
        
        return answer;
    }
    
    void dfs(int depth, int total, int target, int[] numbers) {
        if (depth == numbers.length) {
            if (total == target) answer++;
            return; // 끝까지 훑었는데, target와 같아서 answer올리고 다음 시행으로
        }
        
        dfs(depth+1, total+numbers[depth], target, numbers);
        dfs(depth+1, total-numbers[depth], target, numbers);
        
    }
}