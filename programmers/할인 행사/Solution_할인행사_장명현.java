import java.util.HashMap;
import java.util.Map.Entry;

class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
        
        HashMap<String, Integer> wantMap = new HashMap<>();
        for (int i=0; i<want.length; i++) {
        	wantMap.put(want[i], number[i]);
        }
        
        HashMap<String, Integer> now = new HashMap<>();
        for (int i=0; i<discount.length; i++) {
        	now.put(discount[i], now.getOrDefault(discount[i], 0) + 1);
        	
        	if (i >= 10) {
        		now.computeIfPresent(discount[i-10], (k, v) -> v-1 == 0 ? null : v-1);
        	}
        	
			if (i >= 9 && now.size() == wantMap.size()) {
	        	boolean count = true;
	            for (Entry<String, Integer> e : wantMap.entrySet()) {
	            	if (now.get(e.getKey()) == null || now.get(e.getKey()) != e.getValue()) {
	            		count = false;
	            		break;
	            	}
	            }
	            if (count) answer++;
			}
        }
        
        return answer;
    }
}