package algo_prac;

import java.io.*;
import java.util.*;

public class Solution_3282_이승규 {
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            int[][] dp = new int[N + 1][K + 1];

            for (int i = 1; i <= N; i++) {
                st = new StringTokenizer(br.readLine());

                int V = Integer.parseInt(st.nextToken());
                int C = Integer.parseInt(st.nextToken());

                for (int j = 1; j <= K; j++) {
                    if (j < V) {
                        // 현재 물건을 넣을 수 없는 경우
                        dp[i][j] = dp[i - 1][j];
                    } else {
                        // 물건을 넣는 경우 vs 넣지 않는 경우
                        dp[i][j] = Math.max(
                            dp[i - 1][j],
                            dp[i - 1][j - V] + C
                        );
                    }
                }
            }

            sb.append("#").append(tc).append(" ")
              .append(dp[N][K]).append("\n");
        }

        System.out.print(sb);
    }
}
