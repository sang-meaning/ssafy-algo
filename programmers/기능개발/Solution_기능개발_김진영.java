import java.util.*;
import java.io.*;

class Solution {
    
    public int[] solution(int[] progresses, int[] speeds) {
        int[] answer = {};
        Deque<int []> q = new ArrayDeque<>();
        ArrayList<Integer> list = new ArrayList<>();
        
        for(int i=0;i<progresses.length;i++){
            q.add(new int[] {i, progresses[i]});
        }
        
        int days = 1;
        int result = 0;
        while(!q.isEmpty()){
            int commit[] = q.pollFirst();

            result = 0;
            int index = commit[0];
            int pp = commit[1] + speeds[index] * days;
            while(pp < 100){
                days++;
                pp = commit[1] + speeds[index] * days;
            }
            result++;

            while(!q.isEmpty()){
                commit = q.pollFirst();
                index = commit[0];
                pp = commit[1] + speeds[index] * days;

                if(pp < 100){
                    q.addFirst(new int[] {index,commit[1]});
                    break;
                }else{                    
                    result++;
                }
            }
            list.add(result);

        }
        answer = new int[list.size()];
        for(int i=0;i<list.size();i++){
            answer[i] = list.get(i);
        }
        
        return answer;
    }
}