import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {

        Deque<Integer> bridge = new ArrayDeque<>();

        for (int i = 0; i < bridge_length; i++) {
            bridge.add(0);
        }

        int curW = 0;
        int t = 0;
        int idx = 0;

        while (idx < truck_weights.length || curW > 0) {

            curW -= bridge.poll();

            if (idx < truck_weights.length
                    && curW + truck_weights[idx] <= weight) {

                bridge.offer(truck_weights[idx]);
                curW += truck_weights[idx];
                idx++;

            } else {
                bridge.offer(0);
            }

            t++;
        }

        return t;
    }
}