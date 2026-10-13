package algo_prac;

public class Solution_아파트_이승규 {
    public static void main(String[] args) {
        int[] dp = new int[9];

        dp[1] = 2;
        dp[2] = 3;

        for (int i = 3; i <= 8; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        System.out.println(dp[8]); // 55
    }
}
