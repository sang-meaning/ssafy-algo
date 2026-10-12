import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        
        Queue<Integer> queue = new LinkedList<>();
		
        for (int i=0; i<progresses.length; i++) {
        	int day = (100 - progresses[i] + speeds[i] - 1)/speeds[i];
        	queue.offer(day);
        }
        
        ArrayList<Integer> result = new ArrayList<>();
        
        while (!queue.isEmpty()) {
        	
        	int standard = queue.poll();
        	int count = 1;
        	
        	while (!queue.isEmpty() && queue.peek() <= standard) {
        		queue.poll();
        		count++;
        	}
        	
        	result.add(count);
        }
        
        int[] answer = new int[result.size()];
        
        for (int i=0; i<result.size(); i++) {
        	answer[i] = result.get(i);
        }
        
        return answer;
    }
}