import java.util.*;
class Solution {
    public int solution(int[] elements) {
        int answer = 0;
        int len = elements.length;
        // 중복 없는 값 -> set
        Set<Integer> set = new HashSet<>();
        
        for(int i = 1; i <= len; i++){
            int left = 0;
            int right= left + i;
            int sum = 0;
            
            for(int j = left; j<right; j++){
                sum += elements[j];
            }
            
            while(left < len){
                set.add(sum);
                
                if(len == i) break;
                
                right %= len;
                sum -= elements[left++];
                sum += elements[right++];
            }
        }
        answer = set.size();
        return answer;
    }
}