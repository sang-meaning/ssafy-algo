import java.io.*;
import java.util.*;

class Solution {
    static int[] cost = new int[4];
    static int[] plan = new int[13];
    static int[] dp = new int[13];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for(int tc=1; tc<=T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i=0; i<4; i++) cost[i] = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            for(int i=1; i<=12; i++) plan[i] = Integer.parseInt(st.nextToken());

            dp[0] = 0;
            for(int i=1; i<=12; i++) {
                // 1일권 vs 1개월권
                dp[i] = dp[i-1] + Math.min(plan[i] * cost[0], cost[1]);

                // 3개월권
                if(i >= 3) dp[i] = Math.min(dp[i], dp[i-3] + cost[2]);
            }

            int ans = Math.min(dp[12], cost[3]);
            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }
        System.out.print(sb);
    }
}