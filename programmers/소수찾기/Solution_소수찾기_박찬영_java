class Solution {
    public boolean isPrime(int m){
        if(m<2){
            return false;
        }
        for(int i=2; i*i<=m; i++){
            if(m%i == 0){
                return false;
            }
        }
        return true;
    }
    
    public int solution(int n) {
        int count = 0;
        for (int j = 1; j <=n; j++){
            if (isPrime(j)) {
               count+= 1;
            }
        } 
        return count;

    }  
}