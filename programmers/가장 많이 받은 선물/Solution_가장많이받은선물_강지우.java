import java.util.HashMap;
import java.util.Map;

class Solution {
    public int solution(String[] friends, String[] gifts) {
		int n = friends.length;
		
		Map<String, Integer> hMap = new HashMap<>();
		
		for(int i=0; i<n; i++) {
			hMap.put(friends[i], i);
		}
		
		int[][] giftGrid = new int[n][n];
		
		int[] giftDegree = new int[n];
		
		for(String gift: gifts) {
			String[] parts = gift.split(" ");
			int giver = hMap.get(parts[0]);
			int receiver = hMap.get(parts[1]);
			
			giftGrid[giver][receiver]++;
			giftDegree[giver]++;
			giftDegree[receiver]--;
		}
		
		int[] nextMonthGifts = new int[n];
		
		for(int i=0; i<n; i++) {
			for(int j=i+1; j<n; j++) {
				
				
				if (giftGrid[i][j] > giftGrid[j][i]) {
					nextMonthGifts[i]++;
				} else if (giftGrid[i][j] < giftGrid[j][i]) {
					nextMonthGifts[j]++;
				} else {
					
					if (giftDegree[i] > giftDegree[j]) {
						nextMonthGifts[i]++;
					} else if (giftDegree[i] < giftDegree[j]) {
						nextMonthGifts[j]++;
					}
					
				}	
			}
		}
		
		
		
		int maxGifts = 0;
		for (int count: nextMonthGifts) {
			maxGifts = Math.max(maxGifts, count);
		}
		
		return maxGifts;
		
    }
}