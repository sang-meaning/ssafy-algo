public class Solution_막대색칠하기_한석호 {
    public static void main(String[] args) {
        int n = 6;
        int[] dp = new int[n + 1];
        
        // 초기값 설정
        dp[0] = 1;
        dp[1] = 2;
        
        // 점화식 적용
        for (int i = 2; i <= n; i++) {
            dp[i] = 2 * dp[i - 1] + dp[i - 2];
        }
        
        System.out.println("f(6) = " + dp[6]); // 결과: 169
    }
}