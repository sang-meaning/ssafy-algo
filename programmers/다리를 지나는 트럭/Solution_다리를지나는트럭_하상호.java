package al_prac.pgms;
import java.util.*;

public class Solution_다리를지나는트럭_하상호 {
    public int solution(
            int bridge_length,
            int weight,
            int[] truck_weights
    ) {
        Queue<Integer> bridge = new ArrayDeque<>();

        // 다리의 각 칸을 0으로 초기화
        for (int i = 0; i < bridge_length; i++) {
            bridge.offer(0);
        }

        int time = 0;
        int currentWeight = 0;
        int truckIndex = 0;

        while (truckIndex < truck_weights.length) {
            time++;

            // 다리의 맨 앞 차량이 다리에서 나감
            int exitedTruck = bridge.poll();
            currentWeight -= exitedTruck;

            int nextTruck = truck_weights[truckIndex];

            // 다음 트럭을 다리에 올릴 수 있는 경우
            if (currentWeight + nextTruck <= weight) {
                bridge.offer(nextTruck);
                currentWeight += nextTruck;
                truckIndex++;
            } else {
                // 트럭을 올릴 수 없다면 빈칸 추가
                bridge.offer(0);
            }
        }

        // 마지막 트럭이 다리에 진입한 후
        // 다리를 완전히 빠져나오는 데 필요한 시간
        return time + bridge_length;
    }
}
