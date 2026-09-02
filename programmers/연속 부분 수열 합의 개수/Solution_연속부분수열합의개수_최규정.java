import java.util.*;

class Solution {
    public int solution(int[] elements) {
        int answer = 0;
        Set<Integer> set = new HashSet<>();
        
        for(int start = 0; start < elements.length; start++){
            int sum = 0;
            
            for(int len = 1; len <= elements.length; len++){
                int index = (start + len - 1) % elements.length;
                sum = sum + elements[index];
                
                set.add(sum);
            }
            
        }
        
        answer = set.size();
            
        return answer;
    }
}