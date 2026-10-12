import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
    	int N = prices.length;
        int[] answer = new int[N];
        
        Stack <int[]> s = new Stack<>();
        

        for (int i=0; i<N; i++) {
        	while (!s.isEmpty() && s.peek()[0] > prices[i]) {
            	int[] f = s.peek();
            	answer[f[1]] = i-f[1];
            	s.pop();
        	}
        	s.add(new int[] {prices[i], i});
        	
        }
        
        while (!s.isEmpty()) {
        	int[] f = s.peek();
        	answer[f[1]] = N-1 - f[1];
        	s.pop();
        }
        
        return answer;
    }
}