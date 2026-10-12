import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1952_한석호 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());

        for (int t = 1; t <= T; t++) {
            // 요금 입력 (1일, 1달, 3달, 1년)
            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] prices = new int[4];
            for (int i = 0; i < 4; i++) {
                prices[i] = Integer.parseInt(st.nextToken());
            }

            // 1~12월 이용 계획 입력
            int[] days = new int[13];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= 12; i++) {
                days[i] = Integer.parseInt(st.nextToken());
            }

            int[] dp = new int[13];

            for (int i = 1; i <= 12; i++) {
                // 1일 이용권 vs 1달 이용권
                int cost1 = dp[i - 1] + (days[i] * prices[0]);
                int cost2 = dp[i - 1] + prices[1];
                dp[i] = Math.min(cost1, cost2);

                // 3달 이용권 (3월 이상부터 가능)
                if (i >= 3) {
                    int cost3 = dp[i - 3] + prices[2];
                    dp[i] = Math.min(dp[i], cost3);
                }
            }

            // 최종 12개월 누적 비용과 1년 이용권 비교
            int ans = Math.min(dp[12], prices[3]);

            System.out.println("#" + t + " " + ans);
        }
    }
}