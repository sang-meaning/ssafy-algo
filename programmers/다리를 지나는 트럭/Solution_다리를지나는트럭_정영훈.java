import java.util.*;

class Solution_다리를지나는트럭_정영훈 {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        
        Queue<Integer> trucks=new LinkedList<>();
        Queue<Integer> inBridge=new LinkedList<>();
        for(int truck : truck_weights){
            trucks.offer(truck);
        }
        for(int i=0; i<bridge_length-1; i++){
            inBridge.offer(0);
            
            
        }
        int completedTruck=0;
        int cWeight=0;
        int time=1;
        while(completedTruck!=truck_weights.length){
            time++;
            
            if(!trucks.isEmpty() && (trucks.peek()+cWeight)<=weight){
                int truck=trucks.peek();
                inBridge.offer(truck);
                cWeight+=trucks.poll();
            }else{
                inBridge.offer(0);
            }
            
            int end=inBridge.poll();
            if(end!=0) completedTruck++;
            cWeight-=end;  
        }
        answer=time;
        
        
        
        return answer;
    }
}