import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        
        Queue<Integer> bridge = new LinkedList<>();
        
        for(int i=0; i<bridge_length; i++){
            bridge.offer(0);
        }
        
        int i = 0;
        int count = 0;
        int current_weight = 0;
        
        while(i<truck_weights.length){
            count++;
            
            current_weight -= bridge.poll();
            
            if(weight >= current_weight+truck_weights[i]){
                bridge.offer(truck_weights[i]);
                current_weight += truck_weights[i];
                i++;
            }else{
                bridge.offer(0);
            }
        }
        
        return count+bridge_length;
    }
}