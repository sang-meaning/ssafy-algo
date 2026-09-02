import java.util.*;

public class Solution {
    public int[] solution(int []arr) {
        Stack<Integer> numList = new Stack<>();
        numList.push(arr[0]);
        for(int i = 1; i < arr.length; i++){
            if(arr[i] == numList.peek())
                continue;
            numList.push(arr[i]);
        }
        int[] answer = new int[numList.size()];
        for(int i = answer.length-1; i >= 0; i--){
            answer[i] = numList.pop();
        }
        
        return answer;
    }
}