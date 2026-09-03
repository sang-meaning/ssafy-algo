import java.util.ArrayDeque;

class Solution {
    public int solution(int[] queue1, int[] queue2) {

    	long s1 = 0, s2 = 0;
    	ArrayDeque<Long> q1 = new ArrayDeque<>();
    	ArrayDeque<Long> q2 = new ArrayDeque<>();
    	for (int i=0; i<queue1.length; i++) { q1.add((long) queue1[i]); s1 += queue1[i]; }
    	for (int i=0; i<queue2.length; i++) { q2.add((long) queue2[i]); s2 += queue2[i]; }
    	
    	int answer = 0;
    	while (answer < 2*(queue1.length + queue2.length)) {
    		if (s1 == s2) break;
    		
    		long f;    		
    		if (s2 == 0 || s1 > s2) {
    			f = q1.poll();
    			q2.add(f);
    			
    			s1 -= f;
    			s2 += f;
    		} else if (s1 == 0 || s1 < s2) {
    			f = q2.poll();
    			q1.add(f);
    			
    			s1 += f;
    			s2 -= f;
    		}
    		
    		answer++;
    	}
    	
        return answer == 2*(queue1.length + queue2.length) ? -1 : answer;
    }
}