import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            int[][] dp = new int[N + 1][K + 1];
            
            for (int i = 1; i <= N; i++) {
                st = new StringTokenizer(br.readLine());
                
                int volume = Integer.parseInt(st.nextToken());
                int value = Integer.parseInt(st.nextToken());
                
                for (int k = 0; k <= K; k++) {

                    // 현재 물건을 넣지 않는 경우
                    dp[i][k] = dp[i - 1][k];

                    // 현재 물건을 넣을 수 있는 경우
                    if (k >= volume) {
                        dp[i][k] = Math.max(dp[i][k], dp[i - 1][k - volume] + value);
                    }
                }
            }

            sb.append("#").append(tc)
              .append(" ").append(dp[N][K])
              .append("\n");
        }
        System.out.print(sb);
    }
}