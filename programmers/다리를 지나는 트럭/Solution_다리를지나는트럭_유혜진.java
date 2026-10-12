import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        Queue<Integer> bridge = new LinkedList<>();
        
        // 다리 길이만큼 0으로 채워 초기화 (다리 공간 확보)
        for (int i = 0; i < bridge_length; i++) {
            bridge.offer(0);
        }
        
        int time = 0;
        int currentWeight = 0;
        int index = 0; // 대기 트럭의 인덱스
        
        // 대기 중인 트럭이 남아있거나 다리 위에 트럭이 남아있는 동안 반복
        while (index < truck_weights.length) {
            time++;
            
            // 1초가 지났으므로 다리의 맨 앞 요소(트럭 또는 0)를 내보냄
            currentWeight -= bridge.poll();
            
            // 다음 트럭이 다리에 오를 수 있는지 무게 확인
            if (currentWeight + truck_weights[index] <= weight) {
                bridge.offer(truck_weights[index]);
                currentWeight += truck_weights[index];
                index++; // 다음 트럭으로 이동
            } else {
                // 무게 초과로 올라가지 못하면 0을 넣어 다리 길이 유지
                bridge.offer(0);
            }
        }
        
        // 마지막 트럭이 다리에 올라간 시점에서 반복문이 끝나므로,
        // 해당 트럭이 다리를 완전히 건너는 시간(bridge_length)을 더해줌
        return time + bridge_length;
    }
}