class Solution {
    public int solution(int n) {
        int[] arr = new int[n+1];
        
        for(int i=2;i<=n;i++){
            arr[i] = 1;
        }
        
        for(int i=2;i<=n;i++){
            for(int j=i*2;j<=n;j+=i){
                arr[j] = 0;
            }
        }
        
        int answer = 0;
        for(int i=0;i<=n;i++){
            if(arr[i] == 1){
                answer++;
            }
        }

        return answer;
    }
}