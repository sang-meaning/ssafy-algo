class Solution {
    public int solution(int n) {
        int answer = 0;
        boolean isSosu;
        int i, j;
        
      
        for(j = 2; j <= n; j++){
            isSosu = true;
            
            int limit = (int)Math.sqrt(j);
            for(i=2; i <= limit; i++){
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