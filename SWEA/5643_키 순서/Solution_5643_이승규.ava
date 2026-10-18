import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br =
                new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine().trim());
            int M = Integer.parseInt(br.readLine().trim());

            boolean[][] shorter = new boolean[N + 1][N + 1];

            for (int i = 0; i < M; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                shorter[a][b] = true;
            }

            // k를 중간에 거쳐서 알 수 있는 키 관계를 반영
            for (int k = 1; k <= N; k++) {
                for (int i = 1; i <= N; i++) {
                    if (!shorter[i][k]) {
                        continue;
                    }

                    for (int j = 1; j <= N; j++) {
                        if (shorter[k][j]) {
                            shorter[i][j] = true;
                        }
                    }
                }
            }

            int answer = 0;

            for (int i = 1; i <= N; i++) {
                int count = 0;

                for (int j = 1; j <= N; j++) {
                    if (i == j) {
                        continue;
                    }

                    // i보다 크거나 작은 학생이면 비교 가능
                    if (shorter[i][j] || shorter[j][i]) {
                        count++;
                    }
                }

                if (count == N - 1) {
                    answer++;
                }
            }

            sb.append("#").append(tc)
              .append(" ").append(answer).append("\n");
        }

        System.out.print(sb);
    }
}