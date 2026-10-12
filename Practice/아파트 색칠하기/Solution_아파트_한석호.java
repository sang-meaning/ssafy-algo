public class Solution_아파트_한석호 {
    public static void main(String[] args) {
        int n = 8;
        int[] dp = new int[n + 1];
        
        dp[1] = 2; // f(1) = 2
        dp[2] = 3; // f(2) = 3
        
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        
        System.out.println("f(8) = " + dp[8]); // 결과: 55
    }
}