import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        int N = progresses.length;
        int[] left = new int[N+1];
        
        for (int i=0; i<N; i++) {
        	left[i] = (100 - progresses[i]) / speeds[i];
        	if ((100 - progresses[i]) % speeds[i] > 0) 
        		left[i]++;
        }
        left[N] = 10000;
        
        List<Integer> list = new ArrayList<>();
        
        int now = left[0], suma = 1;
        for (int i=1; i<=N; i++) {
        	if (left[i] > now) {
        		list.add(suma);
        		now = left[i];
        		suma = 1;
        	} else {
        		suma++;
        	}
        }
        
        int[] answer = new int[list.size()];
        for (int i=0; i<list.size(); i++) {
        	answer[i] = list.get(i);
        }
        
        return answer;
    }
}