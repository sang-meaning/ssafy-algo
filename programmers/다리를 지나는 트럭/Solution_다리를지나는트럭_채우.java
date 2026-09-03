import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        
        int size = truck_weights.length;
        Queue<Integer> queue = new ArrayDeque<>();
        for(int i=0;i<bridge_length;i++){
            queue.add(0);
        }
        int time=0;
        int idx=0;
        int curwei=0;
        
        while(idx<size){
            
            time++;
            curwei-=queue.poll();//건넌 트럭 무게 제거
            
            if(curwei+truck_weights[idx]<=weight){
                queue.add(truck_weights[idx]);
                curwei+=truck_weights[idx];
                idx++;
            }else{
                queue.add(0);
            }
        }
        return time + bridge_length;
    }
}