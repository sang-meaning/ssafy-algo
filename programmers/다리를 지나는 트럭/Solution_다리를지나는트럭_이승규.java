import java.util.ArrayList;
class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        // 다리에 있는녀석 + 다리에 있어야할 시간 넣기
        // canGo가 true라면 -> 다음차를 보내기
        // canGo 유지할지 말지 check
        
        int doneTruck = 0;
        int pointer = 0;
        int totalWeight = 0;
        ArrayList<Integer> nowOn = new ArrayList<>();
        ArrayList<Integer> howLong = new ArrayList<>();
        boolean canGo = true;
        
        while(doneTruck < truck_weights.length) { // 다 건너갈때까지 
            
            for(int i = 0; i < nowOn.size(); i++) {
                howLong.set(i, howLong.get(i) - 1);
            }
            
            if(!howLong.isEmpty() && howLong.get(0) == 0) {
                // 다가면
                doneTruck++;
                totalWeight -= nowOn.get(0); 
                nowOn.remove(0);
                howLong.remove(0);
            }
            
            canGo = true;
            if(totalWeight + truck_weights[pointer] > weight) {
                canGo = false;
            }
            
            if(canGo) {
                nowOn.add(truck_weights[pointer]);
                totalWeight += truck_weights[pointer];
                howLong.add(bridge_length);
                pointer++;
            }
            
            if(pointer == truck_weights.length)
                pointer = truck_weights.length -1;
            answer++;
        }
        
        return answer;
    }
}