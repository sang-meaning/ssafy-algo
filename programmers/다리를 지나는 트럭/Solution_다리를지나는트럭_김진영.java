import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        
        Deque<Integer> bridge = new ArrayDeque<>();

        for (int i = 0; i < bridge_length; i++) {
            bridge.add(0);
        }

        int time = 0;
        int currentWeight = 0;
        int idx = 0;

        while (idx < truck_weights.length) {
        //for(int idx=0;idx<truck_weights.length;idx++){

            time++;

            currentWeight -= bridge.poll();

            if (currentWeight + truck_weights[idx] <= weight) {
                bridge.add(truck_weights[idx]);
                currentWeight += truck_weights[idx];
                idx++;
            } else {
                bridge.add(0);
            }
        }
        
        while(!bridge.isEmpty()){
            System.out.printf("%d ",bridge.pollFirst());
        }

        time += bridge_length;
        
        
        return time;
    }
}