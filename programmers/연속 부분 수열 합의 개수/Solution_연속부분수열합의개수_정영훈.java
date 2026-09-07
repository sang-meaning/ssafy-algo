
import java.util.*;
class Solution_연속부분수열합의개수_정영훈{
    public int solution(int[] elements) {
        int answer = 0;
        int length = elements.length;
        Set<Integer> set = new HashSet<>();
        
        for(int i = 1; i <= length; i++){
            int left = 0;
            int right = left + i;
            int sum = 0;
            
            for(int j = left; j<right; j++){
                sum += elements[j];
            }
            
            while(left < length){
                set.add(sum);
                if(length == i) break;
                
                right %= length;
                sum -= elements[left++];
                sum += elements[right++];
            }
        }
        answer = set.size();
        return answer;
    }
}