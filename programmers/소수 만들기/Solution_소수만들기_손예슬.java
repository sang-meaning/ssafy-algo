class Solution {
    public int solution(int n) {
        int answer = 0;
        boolean isSosu;
        int i, j;
        
      
        for(j = 2; j <= n; j++){
            isSosu = true;
            
            for(i=2; i <= Math.sqrt(j); i++){
                if (j % i == 0) {
                    isSosu = false;
                    break;
                }
            }
            
            if(isSosu) {answer += 1;}
        }
        
        return answer;
    }
}