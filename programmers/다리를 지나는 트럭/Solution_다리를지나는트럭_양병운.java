import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int time = 0;
        int next = 0;
        int truckSize = truck_weights.length;
        boolean[] visited = new boolean[truckSize];
        int[][] bridge = new int[bridge_length][2]; // [나갈 시간, 차량 번호]
        for (int i = 0; i < bridge_length; i++) Arrays.fill(bridge[i], -1);
        int onBridge = 0;
        int answer = 0;

        while (checkVisited(visited)) {
            time++;

            // 나갈 차량
            for (int i = 0; i < bridge_length; i++) {
                if (bridge[i][0] != -1 && bridge[i][0] <= time) {
                    int truck = bridge[i][1];
                    onBridge -= truck_weights[truck];
                    visited[truck] = true;
                    bridge[i][0] = -1;
                    bridge[i][1] = -1;
                    answer = time;
                }
            }

            // 들어갈 차량
            if (next < truckSize) {
                int truck = next;
                if (onBridge + truck_weights[truck] <= weight) {
                    for (int i = 0; i < bridge_length; i++) {
                        if (bridge[i][0] == -1) {
                            bridge[i][0] = time + bridge_length;
                            bridge[i][1] = truck;
                            onBridge += truck_weights[truck];
                            next++;
                            break;
                        }
                    }
                }
            }
        }
        return answer;
    }

    public boolean checkVisited(boolean[] visited) {
        for (boolean v : visited) {
            if (!v) return true;
        }
        return false;
    }
}