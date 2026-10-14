
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int testcase = 1; testcase <= T; testcase++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            int[][] dp = new int[n + 1][k + 1];

            for (int i = 1; i <= n; i++) {
                st = new StringTokenizer(br.readLine());
                int v = Integer.parseInt(st.nextToken()); // 부피
                int c = Integer.parseInt(st.nextToken()); // 가치

                for (int w = 0; w <= k; w++) {
                    // 현재 물건을 선택하지 않는 경우
                    dp[i][w] = dp[i - 1][w];

                    // 현재 물건을 선택하는 경우
                    if (w >= v) {
                        dp[i][w] = Math.max(
                            dp[i][w],
                            dp[i - 1][w - v] + c
                        );
                    }
                }
            }

            System.out.println("#" + testcase + " " + dp[n][k]);
        }
    }
}
