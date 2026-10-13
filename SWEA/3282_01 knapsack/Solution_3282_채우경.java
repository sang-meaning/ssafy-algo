import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Knapsack {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String line = br.readLine();
        if (line == null || line.trim().isEmpty()) return;

        int T = Integer.parseInt(line.trim());

        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());// 가방 수
            int K = Integer.parseInt(st.nextToken());//최대 부피

            // dp[w] : 부피 w일 때 담을 수 있는 최대 가치
            int[] dp = new int[K + 1];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int V = Integer.parseInt(st.nextToken()); // 부피
                int C = Integer.parseInt(st.nextToken()); // 가치

                // 1차원 배열 DP 사용 시 중복 선택 방지를 위해 K부터 V까지 역순 갱신
                for (int w = K; w >= V; w--) {
                    dp[w] = Math.max(dp[w], dp[w - V] + C);
                }//w 부피가 K이하인 상태에서 최고의 가치를 구해야함
            }
            System.out.println("#"+(T+1)+ " "+dp[K]);
        }
    }
}