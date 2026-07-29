import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;

class Solution {
    public int solution(String[] friends, String[] gifts) {
        int answer = 0;
        int friendsNum = friends.length; // 친구수
        int[][] giftHistory = new int[friendsNum][friendsNum]; // 준거 포함
        int[] recieve = new int[friendsNum]; // 다음달 받게 될 수
        int[] pValue = new int[friendsNum]; // 선물지수
        
        // hashmap 하나에 이름 - index 저장하기. 
        Map<String, Integer> nameIdx = new HashMap<String,Integer>();
        for(int i = 0; i < friendsNum; i++) {
            nameIdx.put(friends[i], i);
        }
        
        // gift History 넣기
        for(int i = 0; i < gifts.length; i++) {
            String A = gifts[i].split(" ")[0];
            String B = gifts[i].split(" ")[1];
            int Aidx = nameIdx.get(A);
            int Bidx = nameIdx.get(B);
            
            giftHistory[Aidx][Bidx]++;
        }
        
        // pValue 계산하기
        for(int i = 0; i < friendsNum; i++) {
            for(int j = 0; j < friendsNum; j++) {
                pValue[i] += giftHistory[i][j];
                pValue[i] -= giftHistory[j][i];
            }
        }
        
        // for문 2개로 비교해서 recieve에 넣기
        for(int i = 0; i < friendsNum; i++) {
            for(int j = 0; j < friendsNum; j++) {
                if(i == j) continue; 
                int given = giftHistory[i][j]; // 준양
                int taken = giftHistory[j][i]; // 받은양
                
                if(given > taken) { //준게 받은것보다 많은 경우
                    recieve[i]++;
                }
                if(given == taken) { // 같은경우, pvalue 비교해서 많으면 +
                    if(pValue[i] > pValue[j])
                        recieve[i]++;
                }
            }
        }
        Arrays.sort(recieve);
        answer = recieve[friendsNum -1];
        return answer;
    }
}
    
