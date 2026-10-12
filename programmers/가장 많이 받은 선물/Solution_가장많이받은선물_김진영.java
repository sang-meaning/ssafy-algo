class Solution {
    public int solution(String[] friends, String[] gifts) {
        int answer = 0;
        int N = friends.length;
        
        int[][] arr = new int[N][N];        
        int[] present = new int[N];
        int[] result = new int[N];

        for(int i=0;i<gifts.length;i++){ // 선물 주고 받은 것 파싱
            String str[] = gifts[i].split(" ");
            for(int j=0;j<N;j++){ //이름 배열 저장
                for(int k=0;k<N;k++){
                    if(friends[j].equals(str[0]) && friends[k].equals(str[1])){
                        arr[j][k]++;
                    }    
                }
            }   
        }
        
        for(int i=0;i<N;i++){ //선물지수 +-
            for(int j=0;j<N;j++){
                present[i] += arr[i][j];
                present[i] -= arr[j][i];
            }
        }
        
        for(int i=0;i<N-1;i++){ //정답 카운트
            for(int j=i+1;j<N;j++){
                if(arr[i][j] > arr[j][i]){
                    result[i]++;
                }else if(arr[i][j] < arr[j][i]){
                    result[j]++;
                }else if(arr[i][j] == arr[j][i]){
                    if(present[i] > present[j]){
                        result[i]++;
                    }
                    else if(present[i] < present[j]){
                        result[j]++;
                    }
                }
            }
        }

        for(int i=0;i<N;i++){
            answer = Math.max(answer,result[i]);
        }   
        
        return answer;
    }
}