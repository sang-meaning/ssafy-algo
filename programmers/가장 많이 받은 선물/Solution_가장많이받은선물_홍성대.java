import java.util.*;

class Solution {
    public int solution(String[] friends, String[] gifts) {
        int n = friends.length;
        Map<String, Integer> friendIndex = new HashMap<>();
        for (int i = 0; i < n; i++) {
            friendIndex.put(friends[i], i);
        }
        int [][] a = new int [n][n];
        for (String gift : gifts) {
            String[] parts = gift.split(" ");
            String giver = parts[0]; // 선물을 준 사람
            String receiver = parts[1]; // 선물을 받은 사람

            // 이름에 해당하는 인덱스 가져오기
            int giverIdx = friendIndex.get(giver);
            int receiverIdx = friendIndex.get(receiver);

            // 준 사람 -> 받은 사람 횟수 1 증가
            a[giverIdx][receiverIdx]++;
        }
        //선물 지수 계산
        int [] get = new int[n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                get[i] += a[i][j] - a[j][i];
            }
        }
        //선물 받기 계산
        int [] answer = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(i == j){
                    continue;
                }
                //준 선물이 더 많을때 ++
                if(a[i][j] > a[j][i]){
                    answer[i]++;
                //주고받은 선물이 같을땐 선물 지수 높은놈이 ++
                }else if(a[i][j] == a[j][i]){
                    if(get[i] > get[j]){
                        answer[i]++;
                    }
                }
            }
        }
        int maxval = 0;
        for(int i=0; i < n; i++){
            if(answer[i] > maxval){
                maxval = answer[i];
            }
        }
        return maxval;
        
    }
}