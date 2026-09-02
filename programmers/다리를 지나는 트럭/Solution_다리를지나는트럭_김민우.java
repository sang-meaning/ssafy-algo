import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        Queue<int[]> bridge = new LinkedList<>();
        
        int curW = 0;
        int time = 0;
        int exit_time = 0;
        
        for(int trucks : truck_weights){
            time++;
            while(!bridge.isEmpty() && bridge.peek()[1] <= time){
                curW -= bridge.poll()[0];
            }
            while(curW + trucks > weight || bridge.size() >= bridge_length){
                time = bridge.peek()[1];
                while(!bridge.isEmpty() && bridge.peek()[1] <= time){
                    curW -= bridge.poll()[0];
                }
            }
            exit_time = time + bridge_length;
            bridge.offer(new int[]{trucks, exit_time});
            curW += trucks;
        }
        
        answer = exit_time;
        return answer;
    }
}