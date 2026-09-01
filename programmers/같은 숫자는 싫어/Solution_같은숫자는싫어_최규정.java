import java.util.*;

public class Solution {
    public int[] solution(int []arr) {
        
        
        Deque<Integer> deque = new ArrayDeque<>();
        
        for(int i = 0; i < arr.length; i++){
            if(deque.isEmpty() || deque.peekLast() != arr[i]){
                deque.offer(arr[i]);
            }
        }
        
        int[] answer = new int[deque.size()];
        int num = 0;
        while(!deque.isEmpty()){
            answer[num] = deque.pollFirst();
            num++;
        }
        
        return answer;
    }
}