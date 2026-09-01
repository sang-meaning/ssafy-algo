import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        
        // 일단 무게 되면 집어넣고, 뺄 때 시간 업데이트

        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[] {truck_weights[0], 1});

        int now_weight = truck_weights[0];
        int now_time = 1;
        for (int i=1; i<truck_weights.length; i++) {
            
            boolean timeup = true;
            
            if (q.size() == bridge_length) {
                int[] f = q.poll();
                now_weight -= f[0];
                now_time = f[1] + bridge_length;
                timeup = false;
            }
            
            while (now_weight + truck_weights[i] > weight) {
                int[] f = q.poll();
                now_weight -= f[0];
                now_time = f[1] + bridge_length;
                timeup = false;
            }
            
            if (timeup) now_time++;
            
            q.add(new int[] {truck_weights[i], now_time});
            now_weight += truck_weights[i];
            
            while (q.peek()[1] + bridge_length <= now_time) {
                int[] f = q.poll();
                now_weight -= f[0];
            }
        }
        
        while (!q.isEmpty()) {
            int[] f = q.poll();
            now_time = f[1] + bridge_length;
        }
        
        return now_time;
    }
}