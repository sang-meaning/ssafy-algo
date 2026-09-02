import java.util.*;

class Solution {
    public int solution(int[] elements) {

        int answer = 0;
        int len = elements.length;
        Set<Integer> sumList = new HashSet<>();
        
        for(int l = 1; l <= len; l++){
            
            int sum = 0;
            
            for(int i = 0; i < l; i++){
                sum += elements[i];
            }
            
            sumList.add(sum);
            
            for(int i = 1; i < len; i++){
                int out = i-1;
                int in = (i+l-1) % len;
                
                sum -= elements[out];
                sum += elements[in];
                sumList.add(sum);
            }
            
        }
        
        answer = sumList.size();
        return answer;
    }
}