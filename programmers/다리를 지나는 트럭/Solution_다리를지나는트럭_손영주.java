package submission;

import java.util.ArrayDeque;
import java.util.Queue;

public class Solution {
	static public int solution(int bridge_length, int weight, int[] truck_weights) {
		
		Queue<Integer> bridge = new ArrayDeque<>();
		int bridgeWeight = 0;
		int truck_N = truck_weights.length;
		int arrivedTruck = 0;
		
		for (int i = 0; i < bridge_length; i++) {
			bridge.offer(0);
		}
		// 초기상태.
		
		int truckIdx = 0;
		int sec = 0;
		
		while(arrivedTruck < truck_N) {
			sec++;
			int truck = bridge.poll(); // 다리에서 빠지는 단계.
			
			if (truck > 0) {
				arrivedTruck++;
				bridgeWeight -= truck;
			}
			
			if ((truckIdx < truck_N) && (truck_weights[truckIdx] + bridgeWeight <= weight)) { // 들어오는 단계.
				bridge.offer(truck_weights[truckIdx]);
				bridgeWeight += truck_weights[truckIdx];
				truckIdx++;
			}else {
				bridge.offer(0);
			}
		}
		
        return sec;
    }

}
