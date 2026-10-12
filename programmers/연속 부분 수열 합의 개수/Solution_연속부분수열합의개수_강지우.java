import java.util.HashSet;
import java.util.Set;

class Solution {
    public int solution(int[] elements) {
        Set<Integer> set = new HashSet<>();
        int n = elements.length;
        
        for (int len=1; len<=n; len++) {	// 부분수열 길이
        	for (int start=0; start<n; start++) {	// 시작 위치
        		int sum = 0;
        		
        		for (int i=0; i<len; i++) {
        			sum += elements[(start + i) % n];
        		}
        		
        		set.add(sum);
        	}
        }
        
        return set.size();
    }
}