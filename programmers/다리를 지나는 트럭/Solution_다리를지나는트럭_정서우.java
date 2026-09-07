import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;

        Deque<Integer> q = new ArrayDeque<>(bridge_length);
        for (int i = 0; i < bridge_length; i++) {
            q.add(0);
        }

        int time = 0;
        int current_weight = 0;
        int truck_index = 0;

        while (truck_index < truck_weights.length) {
            time++;

            current_weight -= q.poll();

            int next_truck = truck_weights[truck_index];

            if (current_weight + next_truck <= weight) {
                q.add(next_truck);
                current_weight += next_truck;
                truck_index++;
            } else {
                q.add(0);
            }
        }

        answer = time + bridge_length;
        return answer;
    }
}