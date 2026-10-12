import java.util.*;

class Solution {
    public int solution(String[] friends, String[] gifts) {
        int answer = 0;
        int fl = friends.length;
        
        Map<String, Integer> map = new HashMap<>();
        
        //맵을 만들어 각 n번째 문자열을 맵의 키값으로 접근할 수 있게 함 
        for (int i = 0; i < fl; i++) {
            map.put(friends[i], i);
        }
        
        int[][] present = new int[fl][fl];
        String[] temp = new String[2];
        
        //단어를 공백으로 분리해서 대입
        for (int i = 0; i < gifts.length; i++) {
            temp = gifts[i].split(" ");
            present[map.get(temp[0])][map.get(temp[1])]++;
        }
        
        // [친구 번호][0] = 행의 합, 준 선물 수
        // [친구 번호][1] = 열의 합, 받은 선물 수
        // [친구 번호][2] = 선물 지수
        int[][] gtpoint = new int[fl][3];

        for (int i = 0; i < fl; i++) {
            for (int j = 0; j < fl; j++) {
                gtpoint[i][0] += present[i][j];
                gtpoint[i][1] += present[j][i];
            }

            gtpoint[i][2] = gtpoint[i][0] - gtpoint[i][1];
        }
        // 다음 달에 각 친구가 받을 선물 수
        int[] nextmonth = new int[fl];
        
        for (int i = 0; i < fl; i++) {
            for (int j = i + 1; j < fl; j++) {
                
                // i가 j에게 더 많이 준 경우
                if (present[i][j] > present[j][i]) {
                    nextmonth[i]++;
                }
                
                // j가 i에게 더 많이 준 경우
                else if (present[i][j] < present[j][i]) {
                    nextmonth[j]++;
                }
                
                // 주고받은 수가 같은 경우 선물 지수 비교
                else {
                    if (gtpoint[i][2] > gtpoint[j][2]) {
                        nextmonth[i]++;
                    } else if (gtpoint[i][2] < gtpoint[j][2]) {
                        nextmonth[j]++;
                    }
                }
            }
        }
        
        for (int i = 0; i < fl; i++) {
            answer = Math.max(answer, nextmonth[i]);
        }
        
        return answer;
    }
}