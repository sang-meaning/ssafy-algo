import java.util.*;
class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        
        // 다리 건너는 큐
        Queue<int[]> dq = new ArrayDeque<>();
        
        int t = 0; // 다리에 올라탄 시간 
        int on_weight = 0; // 다리에 있는 무게
        int i = 0; // 처리 트럭 개수
        while(i < truck_weights.length){
            t++;
            
            if(!dq.isEmpty()){
                int[] cur = dq.peek();
                if(cur[1] + bridge_length <= t){
                    dq.poll();
                    on_weight -= cur[0];
                    t = cur[1] + bridge_length;
                }
            }
            
            if(on_weight + truck_weights[i] <= weight){
                dq.offer(new int[]{truck_weights[i], t});
                on_weight += truck_weights[i++];
            }
    
        }
    
        answer = t + bridge_length;
        
        return answer;
    }
}