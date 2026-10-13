import java.util.*;

class Solution {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int tc = 1; tc <= T; tc++) {

            int N = sc.nextInt();
            int K = sc.nextInt();

            int[] V = new int[N + 1];
            int[] C = new int[N + 1];

            for (int i = 1; i <= N; i++) {
                V[i] = sc.nextInt();
                C[i] = sc.nextInt();
            }

            // dp[i][w] : 앞의 i개 물건으로 용량 w 이하에서 얻는 최대 가치
            int[][] dp = new int[N + 1][K + 1];

            for (int i = 1; i <= N; i++) {

                for (int w = 0; w <= K; w++) {

                    // 현재 물건을 선택하지 않음
                    dp[i][w] = dp[i - 1][w];

                    // 현재 물건을 선택할 수 있다면
                    if (w >= V[i]) {
                        dp[i][w] = Math.max(dp[i][w], dp[i - 1][w - V[i]] + C[i]);
                    }
                }
            }

            System.out.println("#" + tc + " " + dp[N][K]);
        }

        sc.close();
    }
}