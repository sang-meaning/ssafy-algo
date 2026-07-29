import java.util.Map;
import java.util.HashMap;
import java.util.StringTokenizer;

class Solution {
    public int solution(String[] friends, String[] gifts) {
        int answer = 0;
        int l = friends.length;
        
        // 이름 <-> 인덱스 Map
        Map<String, Integer> nameIdx = new HashMap<>();
        for(int i = 0; i<l; i++){
            nameIdx.put(friends[i], i);
        }
        
        // 거래 내역 -> 2차원 배열
        int[][] arr = new int[l][l];
        for(String giftPair : gifts){
            StringTokenizer st = new StringTokenizer(giftPair);
            int giveIdx = nameIdx.get(st.nextToken());
            int receIdx = nameIdx.get(st.nextToken());
            arr[giveIdx][receIdx]++;
        }
        
        // 선물 지수 배열 구하기
        int[] pnum = new int[l];
        for(int i= 0; i<l; i++){
            int row = 0;
            int col = 0;
            for (int j =0; j<l ;j++){
                col += arr[j][i];
                row += arr[i][j];
            }
            pnum[i] = row - col;
        }
        
        // 받을 선물 수 구하기
        int[] result = new int[l];
        for(int i = 0; i< l-1; i++){
            for(int j = i+1; j<l; j++){
    
                if(arr[i][j] < arr[j][i]){
                    result[j]++;
                }else if(arr[i][j] > arr[j][i]){
                    result[i]++;
                }else{
                    if(pnum[i] < pnum[j]) result[j]++;
                    else if(pnum[i] > pnum[j]) result[i]++;
                }
            }    
        }
        
        // 최대 값 구하기
        answer = result[0];
        for(int n: result){
            if (answer < n) answer = n;
        }
        
        return answer;
    }
}



// m: [0, 0, 2, 0] -> 선물지수: (row가 0인것의 합) - (col이 0인 것 합) = -3
// r: [3, 0, 0, 0] -> 3 - 1 = 2
// f: [1, 1, 0, 0] -> 2 - 2 = 0
// n: [1, 0, 0, 0] -> 1 - 0 = 1

// 서로 비교 -> [i][j] [j][i] -> 0이 아닌거 -> 0 <= i < n-1, i+1 <= j < n
// r 1, m 1, n 1, f 1
// 선물 지수 비교 -> r 1, n 1