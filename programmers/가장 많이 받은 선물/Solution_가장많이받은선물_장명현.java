import java.util.HashMap;

class Solution {
    public int solution(String[] friends, String[] gifts) {
        int n = friends.length;
        int m = gifts.length;
        
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        for (int i=0; i<n; i++) {
            map.put(friends[i], i);
        }
        
        int[][] cnt = new int[n][n];
        for (int i=0; i<m; i++) {
        	int idx  = gifts[i].indexOf(' ');
        	String a = gifts[i].substring(0, idx);
        	String b = gifts[i].substring(idx+1);
        	cnt[map.get(a)][map.get(b)]++;
        }
        
        int[] giftScore = new int[n];
        for (int i=0; i<n; i++) {
        	for (int j=0; j<n; j++) giftScore[i] += cnt[i][j];
        	for (int j=0; j<n; j++) giftScore[i] -= cnt[j][i];
        }
        
        int[] nextMonth = new int[n];
        for (int i=0; i<n; i++) {
        	for (int j=i+1; j<n; j++) {
        		if (cnt[i][j] > cnt[j][i]) 		 { nextMonth[i]++; continue; }
        		if (cnt[i][j] < cnt[j][i]) 		 { nextMonth[j]++; continue; }
        		if (giftScore[i] > giftScore[j]) { nextMonth[i]++; continue; }
        		if (giftScore[i] < giftScore[j]) { nextMonth[j]++; continue; }
        	}
        }
        
        int answer = 0;
        for (int i=0; i<n; i++) {
        	answer = Math.max(answer, nextMonth[i]);
        }
        
        return answer;
    }
}