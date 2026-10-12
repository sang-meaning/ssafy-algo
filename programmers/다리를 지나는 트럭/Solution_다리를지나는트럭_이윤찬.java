import java.util.Queue;
import java.util.LinkedList;

class Solution{
	public int solution(int bridge_length, int weight , int[] truck_weights){
		int index= 0;
		int weightSum = 0;
		int answer =0;		
		Queue<Integer> que = new LinkedList<>();
		
		for (int i =0 ; i<bridge_length; i++){
			que.add(0);
		}
		
		while ( index < truck_weights.length){
			answer++;
			weightSum -= que.poll();
			
			if(weightSum + truck_weights[index] <= weight){
				que.add(truck_weights[index]);
				index++;
				
			}
			else{
				que.add(0);
			}
		answer+=bridge_length;
		return answer;
	}
    }
}