class Solution {
    public int solution(int n) {
        int answer = 0;
        Boolean[] num = new Boolean[n+1];
        for(int i = 0; i <= n; i++)
           num[i] = false;
        
        num[0] = true;
        num[1] = true;
        
        for(int i = 2; i*i <= n; i++){
            if(num[i] == true) 
                continue;
            for(int j = i+i; j <= n; j += i){
                num[j] = true;
            }
        }
        
        for(int i =0; i<=n; i++){
            if(num[i] == false) 
                answer++;
        }
        return answer;
    }
}