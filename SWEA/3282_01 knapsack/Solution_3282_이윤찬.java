import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
        );

        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            // dp[k] = 부피 k 이하에서 얻을 수 있는 최대 가치
            int[] dp = new int[K + 1];

            for (int i = 0; i < N; i++) {

                st = new StringTokenizer(br.readLine());

                int V = Integer.parseInt(st.nextToken());
                int C = Integer.parseInt(st.nextToken());

                // 뒤에서부터 탐색하여 물건 중복 선택 방지
                for (int k = K; k >= V; k--) {
                    dp[k] = Math.max(dp[k], dp[k - V] + C);
                }
            }

            sb.append("#").append(tc)
              .append(" ").append(dp[K]).append("\n");
        }

        System.out.print(sb);
    }
}
