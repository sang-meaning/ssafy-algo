class Solution {
    public int solution(int n) {
        int answer = 0;
        
        // isNotPrime[i]가 false이면 소수, true이면 소수가 아님
        boolean[] isNotPrime = new boolean[n + 1];
        
        // 0과 1은 소수가 아님
        isNotPrime[0] = true;
        isNotPrime[1] = true;
        
        // 에라토스테네스의 체: 2부터 루트 n까지 배수를 지워나감
        for (int i = 2; i * i <= n; i++) {
            if (!isNotPrime[i]) {
                for (int j = i * i; j <= n; j += i) {
                    isNotPrime[j] = true;
                }
            }
        }
        
        // 소수의 개수 카운트
        for (int i = 2; i <= n; i++) {
            if (!isNotPrime[i]) {
                answer++;
            }
        }
        
        return answer;
    }
}