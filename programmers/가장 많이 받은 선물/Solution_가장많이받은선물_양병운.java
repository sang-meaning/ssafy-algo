import java.util.*;
class Solution {
    public int solution(String[] friends, String[] gifts) {
        //이번달까지 많이 준 사람이 다음 달에 선물 받
        //없거나 같다 - 선물지수가 큰사람이 작은 사람에게 선물 받
        //선물 지수 : 이번 달까지 준거 - 받은거
        //이것도 같다면 선물 주고 받기 안함
        //다음달에 선물 받는 사람 개수 큰 사람
        Map<String, Integer> friendNum = new HashMap<>();
        int[] absList = new int[friends.length];
        for(int i=0; i<friends.length; i++) friendNum.put(friends[i], i);
        int[][] giftMatrix = new int[friends.length][friends.length];
        for(int i=0; i<gifts.length; i++){
            String[] conn = gifts[i].split(" ");
            int send = friendNum.get(conn[0]);
            int received = friendNum.get(conn[1]);
            absList[send]++;
            absList[received]--;
            giftMatrix[send][received]++;
        }
        int max = 0;
        for(int i=0; i<friends.length; i++){
            for(int j=0; j<friends.length; j++){
                System.out.print(giftMatrix[i][j]);
            }
            System.out.println();
        }
        for(int i=0; i<friends.length; i++){
            int cnt = 0;
            for(int j=0; j<friends.length; j++){
                if(i==j) continue;
                if(giftMatrix[i][j] > giftMatrix[j][i]) cnt++;
                else if(giftMatrix[i][j] == giftMatrix[j][i]) {
                    if(absList[i] > absList[j]) cnt++;
                }else continue;
            }
            if(cnt > max) max = cnt;
        }
        return max;
    }
    public int arraySum(int[] array){
        int sum = 0;
        for(int i=0; i<array.length; i++){
            sum += array[i];
        }
        return sum;
    }
}