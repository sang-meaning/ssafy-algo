import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        
        
        // 7,3,9 큐에 add
        Deque<Integer> q = new ArrayDeque<>();
        for (int i=0; i<progresses.length; i++) {
            int day = (int)Math.ceil( (100.0 - progresses[i]) / speeds[i] );
            q.add(day);
        }
        
        int count = 1; // 연속 작업 개수
        int flag = q.poll();
        Deque<Integer> q2 = new ArrayDeque<>(); // 정답 넣을 큐
        
        while(!q.isEmpty()) {
            int cur = q.peek();
            if (cur <= flag) { 
                q.poll();
                count++;
            } else {
                q2.add(count);
                count = 1;
                flag = q.poll();
            }
            
        }
        
        q2.add(count);
        
        //System.out.println(q2.size());
        int[] answer = new int[q2.size()];
        
        int iter = 0;
        while(!q2.isEmpty()) {
            answer[iter] = q2.poll();
            iter++;
        }
        
        return answer;
    }
}