class Solution_소수찾기_강상민 {
    public int solution(int n) {
        int answer = 0;
        
       for (int i=2; i<=n; i++) {
           if (prime(i)) answer++;
       }
        
        return answer;
    }
    
    static boolean prime(int p) {
        for (int i=2; i*i<=p; i++) {
            if (p%i==0) return false;
        }
        return true;
    }
}