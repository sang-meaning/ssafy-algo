import java.util.*;

public class Solution {
    public int[] solution(int []arr) {
        
        List<Integer> ans = new ArrayList<>();
        
        ans.add(arr[0]);
        for (int i=1; i<arr.length; i++) {
            if (arr[i] != arr[i-1])
            	ans.add(arr[i]);
        }
        
        int[] answer = new int[ans.size()];
        for (int i=0; i<ans.size(); i++)
        	answer[i] = ans.get(i);
        
        return answer;
    }
}