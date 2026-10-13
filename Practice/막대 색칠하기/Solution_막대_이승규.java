package algo_prac;

public class Solution_막대_이승규 {
    public static void main(String[] args) {
        int[] dp = new int[7];

        dp[0] = 1;
        dp[1] = 2;

        for (int i = 2; i <= 6; i++) {
            dp[i] = 2 * dp[i - 1] + dp[i - 2];
        }

        System.out.println(dp[6]); // 169
    }
}