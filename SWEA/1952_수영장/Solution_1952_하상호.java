package cording_prac;

import java.io.*;
import java.util.*;

public class Solution_1952_하상호 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int day = Integer.parseInt(st.nextToken());
            int month = Integer.parseInt(st.nextToken());
            int threeMonths = Integer.parseInt(st.nextToken());
            int year = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            int[] plan = new int[13];
            for (int i = 1; i <= 12; i++) {
                plan[i] = Integer.parseInt(st.nextToken());
            }

            // dp[i] : i월까지 필요한 최소 비용
            int[] dp = new int[13];

            for (int i = 1; i <= 12; i++) {
                // 1일권 또는 1개월권 사용
                int oneMonthCost = Math.min(plan[i] * day, month);
                dp[i] = dp[i - 1] + oneMonthCost;

                // 3개월권 사용
                if (i >= 3) {
                    dp[i] = Math.min(dp[i],
                                     dp[i - 3] + threeMonths);
                }
            }

            // 1년권과 비교
            int answer = Math.min(dp[12], year);

            sb.append('#').append(tc).append(' ')
              .append(answer).append('\n');
        }

        System.out.print(sb);
    }
}
