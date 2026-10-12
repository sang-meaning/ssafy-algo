import java.util.*;


class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        Queue<Integer> bridge = new ArrayDeque<>();
        
        for (int i = 0; i < bridge_length; i++) {
            bridge.offer(0);
        }

        int time = 0;
        int curWeight = 0; 
        int truck = 0;  

        while (truck < truck_weights.length) {
            time++;

            curWeight -= bridge.poll();

            if (curWeight + truck_weights[truck] <= weight) {
                bridge.offer(truck_weights[truck]); 
                curWeight += truck_weights[truck];
                truck++;                            
            } else {
                bridge.offer(0); 
            }
        }


        return time + bridge_length;
    }
}