import java.util.*;

class Solution {
    public int solution(String[] friends, String[] gifts) {
        int a = friends.length;
        
        // 맵에 저장해서 인덱스를 좀 쉽게 꺼내게 했음
        HashMap<String, Integer> map = new HashMap<>();
        for(int i = 0; i < a; i++){
            map.put(friends[i], i);
        }
        int[][] fromTo = new int[a][a];
        // 선물지수 라는 뜻 ㅎ
        int[] tjsanfwltn = new int[a];
        int[] ans = new int[a];
        for(String gift : gifts){
            String[] s = gift.split(" ");
            
            int from = map.get(s[0]);
            int to = map.get(s[1]);
            fromTo[from][to]++;
            tjsanfwltn[from]++;
            tjsanfwltn[to]--;
        }
        // 비교를 함
        for(int i = 0; i < a - 1 ; i++){
            for(int j = i + 1; j < a; j++){
              // 그냥 준 선물 갯수가 많은 거
                if(fromTo[i][j] > fromTo[j][i]) ans[i]++;
                else if(fromTo[i][j] < fromTo[j][i]) ans[j]++;
                else{
                  // 이거는 선물 지수로 하는 거 
                    if(tjsanfwltn[i] > tjsanfwltn[j]) ans[i]++;
                    else if(tjsanfwltn[i] < tjsanfwltn[j]) ans[j]++;
                }
            }
        }
        
        int answer = 0;
        for(int k : ans){
            answer = Math.max(k, answer);
        }
        return answer;
    }
}