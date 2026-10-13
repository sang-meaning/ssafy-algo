import java.util.*;

class Solution {
    public static void main(String[] args) {
        int N = 6;
        long[] dp = new long[N + 1];
        dp[0] = 1;
        dp[1] = 2;
        for(int i = 2; i <= N; i++) {
            dp[i] = dp[i - 1] * 2 + dp[i - 2];
        }

        System.out.println(dp[N]);
    }
}