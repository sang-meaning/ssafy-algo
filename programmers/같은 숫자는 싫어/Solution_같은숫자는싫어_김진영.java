import java.util.*;
import java.io.*;

public class Solution {
    public int[] solution(int []arr) {
        
        Deque<Integer> q = new ArrayDeque<>();
        
        for(int t : arr){            
            if(q.isEmpty()){
                q.add(t);
            }else{
                int temp = q.getLast();
                if(t == temp){
                    continue;
                }else{
                    q.add(t);
                }
            }       
        }
        
        int[] answer = new int[q.size()];
        int index = 0;
        while(!q.isEmpty()){
            int t = q.poll();
            answer[index++] = t;
        }
        return answer;
    }
}