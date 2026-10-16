import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int N, M;
    static boolean[][] reach;

    public static void main(String[] args) throws Exception {
        BufferedReader br =
                new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine().trim());

        for (int test_case = 1; test_case <= T; test_case++) {
            N = Integer.parseInt(br.readLine().trim());
            M = Integer.parseInt(br.readLine().trim());

            reach = new boolean[N + 1][N + 1];

            // 1. 직접 주어진 키 비교 저장
            for (int i = 0; i < M; i++) {
                StringTokenizer st =
                        new StringTokenizer(br.readLine());

                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                reach[a][b] = true; // a가 b보다 작음
            }

            // 2. 간접적으로 알 수 있는 키 비교 계산
            for (int k = 1; k <= N; k++) {
                for (int i = 1; i <= N; i++) {
                    // i < k를 모르면 k를 통해 연결할 수 없음
                    if (!reach[i][k]) continue;

                    for (int j = 1; j <= N; j++) {
                        // i < k이고 k < j라면 i < j
                        if (reach[k][j]) {
                            reach[i][j] = true;
                        }
                    }
                }
            }

            // 3. 자신의 순서를 알 수 있는 학생 수 계산
            int answer = 0;

            for (int i = 1; i <= N; i++) {
                int count = 0;

                for (int j = 1; j <= N; j++) {
                    if (i == j) continue;

                    if (reach[i][j] || reach[j][i]) {
                        count++;
                    }
                }

                if (count == N - 1) {
                    answer++;
                }
            }

            sb.append('#').append(test_case).append(' ')
              .append(answer).append('\n');
        }

        System.out.print(sb);
    }
}