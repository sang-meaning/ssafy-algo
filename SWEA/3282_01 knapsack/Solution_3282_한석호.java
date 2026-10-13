import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_3282_한석호 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());

        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken()); // 물건의 개수
            int K = Integer.parseInt(st.nextToken()); // 가방의 최대 부피

            int[] v = new int[N + 1];
            int[] value = new int[N + 1];

            for (int i = 1; i <= N; i++) {
                st = new StringTokenizer(br.readLine());
                v[i] = Integer.parseInt(st.nextToken());
                value[i] = Integer.parseInt(st.nextToken());
            }

            int[][] dp = new int[N + 1][K + 1];

            // 0/1 냅색 DP 수행
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= K; j++) {
                    if (j < v[i]) {
                        // 현재 물건의 부피가방 용량보다 크면 넣을 수 없음
                        dp[i][j] = dp[i - 1][j];
                    } else {
                        // 넣지 않는 경우와 넣는 경우 중 최댓값 선택
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - v[i]] + value[i]);
                    }
                }
            }

            sb.append("#").append(tc).append(" ").append(dp[N][K]).append("\n");
        }

        System.out.print(sb);
    }
}