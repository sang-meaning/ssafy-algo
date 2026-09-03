import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_9229_유혜진 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken()); // 과자 봉지 개수
            int M = Integer.parseInt(st.nextToken()); // 무게 제한

            int[] weights = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                weights[i] = Integer.parseInt(st.nextToken());
            }

            int maxWeight = -1; // M 이하로 들 수 있는 방법이 없는 경우 -1 출력

            // 2개의 과자를 고르는 모든 조합 탐색
            for (int i = 0; i < N - 1; i++) {
                for (int j = i + 1; j < N; j++) {
                    int sum = weights[i] + weights[j];

                    // 무게 합이 제한 M 이하이고, 기존 최댓값보다 크다면 갱신
                    if (sum <= M) {
                        maxWeight = Math.max(maxWeight, sum);
                    }
                }
            }

            System.out.println("#" + tc + " " + maxWeight);
        }
    }
}