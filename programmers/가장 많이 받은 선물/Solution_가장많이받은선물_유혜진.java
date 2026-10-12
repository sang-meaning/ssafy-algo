import java.util.*;

class Solution {
    public int solution (String[] friends, String[] gifts) {
        int n = friends.length;
        
        // 이름 -> 인덱스 매핑 (HashMap)
        Map<String, Integer> friendIndex = new HashMap<>();
        for(int i=0; i<n; i++) {
            friendIndex.put(friends[i], i);
        }
        
        // 주고받은 선물 내역(matrix), 선물 지수(degree) 계산 
        int[][] giftMatrix = new int[n][n];
        int[] giftDegree = new int[n];
        
        for(String gift:gifts) {
            String[] parts = gift.split(" ");
            int giver = friendIndex.get(parts[0]);
            int receiver = friendIndex.get(parts[1]);
            
            giftMatrix[giver][receiver]++;
            giftDegree[giver]++;
            giftDegree[receiver]--;    
        }
        
        // 다음 달에 받을 선물 수 계산
        int[] nextMonthGifts = new int[n];
        
        for(int i=0; i<n; i++){
            for(int j=i+1; j<n; j++){
                int giftFromIToJ = giftMatrix[i][j];
                int giftFromJToI = giftMatrix[j][i];
                
                // 주고받은 기록이 있고 수가 다른 경우
                if(giftFromIToJ>giftFromJToI) {
                    nextMonthGifts[i]++;
                } else if(giftFromIToJ<giftFromJToI) {
                    nextMonthGifts[j]++;
                } else {
                    // 주고받은 기록이 없거나 수가 같은 경우 (degree 비교)
                    if(giftDegree[i]>giftDegree[j]) {
                        nextMonthGifts[i]++;
                    }
                    else if(giftDegree[i]<giftDegree[j]) {
                        nextMonthGifts[j]++;
                    }
                }
            }
        }
        
        // 가장 많은 선물을 받는 사람의 선물 수
        int maxGifts=0;        
        for(int count:nextMonthGifts) {
            maxGifts = Math.max(maxGifts, count);
        }
        
        return maxGifts;
        
    }
}