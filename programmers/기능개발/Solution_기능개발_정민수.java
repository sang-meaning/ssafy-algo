import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        int[] answer = {};
        
        Queue<Integer> que = new LinkedList<>(); //남은작업일수
        
        for(int i=0; i<progresses.length; i++){
            
            int day = ((100-progresses[i]) / speeds[i]);
            
            if((100-progresses[i])%speeds[i] > 0){
                day++;
            }
            
            que.offer(day);
        }
        
        
        List<Integer> list = new ArrayList<>();
        int count = 1;
        int standard = que.poll();
        
        while(!que.isEmpty()){
            int next = que.peek();
            
            if(standard >= next){
                count++;
                que.poll();
            }else{
                list.add(count);
                count = 1;
                standard = que.poll();
            }
        }
        
        list.add(count);
        
        answer = new int[list.size()];
        
        for(int i=0; i<answer.length; i++){
            answer[i] = list.get(i);
        }
        
        return answer;
    }
}