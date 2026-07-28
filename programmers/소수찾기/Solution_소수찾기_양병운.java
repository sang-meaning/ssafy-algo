class Solution {
    public int solution(int n) {
        int cnt = 1;
        if(n == 2) return cnt;
        for(int i=3; i<=n; i++){
            boolean primeFlag = true;
            for(int j=2; j*j<=i; j++){
                if(i%j==0){
                    primeFlag = false;   
                    break;
                }
            }
            if(primeFlag) {
                cnt++;
            }
        }
        return cnt;
    }
}