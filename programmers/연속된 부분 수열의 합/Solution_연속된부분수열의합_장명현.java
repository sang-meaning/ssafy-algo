import java.util.HashMap;

class Solution {
    public int[] solution(int[] sequence, int k) {
        int[] answer = {};
        
        int N = sequence.length;
        int[] prefix = new int[N+1];
        
        prefix[1] = sequence[0];
        for (int i=2; i<=N; i++) prefix[i] = prefix[i-1] + sequence[i-1];
        
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i=1; i<=N; i++) {
        	map.put(prefix[i], i);
        }
        
        int ansLen = Integer.MAX_VALUE;
        for (int i=1; i<=N; i++) {
        	int si = i;
        	int li = map.getOrDefault(prefix[si-1] + k, -1);
        	
        	if (li != -1 && li - si < ansLen) {
        		ansLen = li - si;
        		answer = new int[] {si-1, li-1};
        	}
        }
        
        return answer;
    }
}