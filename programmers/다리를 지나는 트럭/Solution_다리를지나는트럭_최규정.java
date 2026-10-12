import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        
        Queue<Integer> queue = new ArrayDeque<>();
        
        int current_weight = 0;
        int truck_index = 0;
        int passed_truck = 0;
        int answer = 0;
        
        for(int i = 0; i < bridge_length; i++){
           queue.offer(0);
        }
        
        while(passed_truck < truck_weights.length){
            int out = queue.poll();
            
            if(out != 0){
                current_weight = current_weight - out;
                passed_truck++;
            }
            
            if (truck_index < truck_weights.length) {

                int in = truck_weights[truck_index];

                if (current_weight + in > weight) {
                    queue.offer(0);
                } else {
                    queue.offer(in);
                    current_weight += in;
                    truck_index++;
                }
            }
            else {
                queue.offer(0);
            }
            answer++;
        }
        
        
        return answer;
    }
}