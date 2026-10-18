import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) throws Exception {

        BufferedReader br =
                new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {

            // 0: 1일권, 1: 1달권, 2: 3달권, 3: 1년권
            int[] price = new int[4];

            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int i = 0; i < 4; i++) {
                price[i] = Integer.parseInt(st.nextToken());
            }

            int[] plan = new int[13];

            st = new StringTokenizer(br.readLine());

            for (int i = 1; i <= 12; i++) {
                plan[i] = Integer.parseInt(st.nextToken());
            }

            int[] dp = new int[13];

            for (int i = 1; i <= 12; i++) {

                // 이번 달을 1일권으로 이용하는 비용
                int dayCost = plan[i] * price[0];

                // 이번 달을 1일권 또는 1달권으로 처리
                dp[i] = dp[i - 1]
                        + Math.min(dayCost, price[1]);

                // 최근 3개월을 3달권으로 처리하는 경우와 비교
                int threeMonthCost =
                        dp[Math.max(0, i - 3)] + price[2];

                dp[i] = Math.min(dp[i], threeMonthCost);
            }

            // 1년권과 비교
            int answer = Math.min(dp[12], price[3]);

            sb.append("#").append(tc)
              .append(" ").append(answer)
              .append("\n");
        }

        System.out.print(sb);
    }
}