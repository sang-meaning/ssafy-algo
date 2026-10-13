import java.util.*;

// SWEA 3282. 0/1 Knapsack - DP
public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int tc = 1; tc <= T; tc++) {
            int N = sc.nextInt(); // 물건 개수
            int K = sc.nextInt(); // 가방 부피

            // dp[w] = 부피 w 이하로 담을 수 있는 최대 가치
            int[] dp = new int[K + 1];

            for (int i = 0; i < N; i++) {
                int V = sc.nextInt(); // 부피
                int C = sc.nextInt(); // 가치
                // 같은 물건을 두 번 담지 않도록 뒤에서부터 갱신
                for (int w = K; w >= V; w--) {
                    dp[w] = Math.max(dp[w], dp[w - V] + C);
                }
            }

            System.out.println("#" + tc + " " + dp[K]);
        }
    }
}