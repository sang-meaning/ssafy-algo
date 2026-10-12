import java.util.*;

public class Solution {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int tc = 1; tc <= T; tc++) {

            int day = sc.nextInt();
            int month = sc.nextInt();
            int threeMonth = sc.nextInt();
            int year = sc.nextInt();

            int[] plan = new int[13];
            int[] dp = new int[13];

            for (int i = 1; i <= 12; i++) {
                plan[i] = sc.nextInt();
            }

            for (int i = 1; i <= 12; i++) {

                int cost = Math.min(
                    plan[i] * day,
                    month
                );

                dp[i] = dp[i - 1] + cost;

                if (i >= 3) {
                    dp[i] = Math.min(
                        dp[i],
                        dp[i - 3] + threeMonth
                    );
                }
            }

            int answer = Math.min(dp[12], year);

            System.out.println("#" + tc + " " + answer);
        }
    }
}