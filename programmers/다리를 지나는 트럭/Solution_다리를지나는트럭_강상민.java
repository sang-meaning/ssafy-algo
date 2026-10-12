import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        
        Deque<Integer> q = new ArrayDeque<>(); 
        
        for (int i=0; i<bridge_length; i++) {
            q.add(0); // 다리 길이만큼 큐에 0 들어있다, 해당 위치에 할당된 무게
        }
        
        // 매 초마다 큐에 트럭을 넣을지, 0을 넣을지 결정 필요
        int b_weight = 0;
        int truck_iter = 0;
        int time = 0;
        
        while(true) {
            int arrived = q.poll(); // 도착한것 빼기
            b_weight -= arrived;
            
            // 새로 트럭을 넣어도 되는지
            int truck = truck_weights[truck_iter];
            if (b_weight + truck <= weight) {
                // 트럭을 넣는다
                q.add(truck);
                b_weight += truck;
                truck_iter++;
            } else {
                q.add(0);
            }
            
            time++;
            
            // 종료조건
            if (truck_iter == truck_weights.length) break;
        
            
        }
        
        // 아직 마지막 트럭이 다리 위임
        while(!q.isEmpty()) {
            q.poll();
            time++;
        }
        
        
        return time;
    }
}