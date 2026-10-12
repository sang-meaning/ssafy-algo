package cording_prac;

public class Solution_막대_하상호 {
    public static void main(String[] args) {
        int n = 6;
        int[] dp = new int[n + 1];
        
        dp[1] = 2;
        dp[2] = 5;
        
        for (int i = 3; i <= n; i++) {
            dp[i] = 2 * dp[i - 1] + dp[i - 2];
        }
        
        System.out.println("f(6) = " + dp[6]);
    }
}
