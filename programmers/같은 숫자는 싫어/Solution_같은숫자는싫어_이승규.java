import java.util.*;

public class Solution {
    public int[] solution(int []arr) {
        ArrayList<Integer> answerList = new ArrayList<>();
        int prev = arr[0];
        answerList.add(arr[0]);
        
        for(int i = 0; i < arr.length; i++) {
            if(prev != arr[i]) {
                answerList.add(arr[i]);
            }
            prev = arr[i];
        }
        
        int[] answer = new int[answerList.size()];
        
        for(int i = 0; i < answerList.size(); i++)
            answer[i] = answerList.get(i);
        
        return answer;
    }
}