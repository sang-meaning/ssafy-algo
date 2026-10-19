import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) throws Exception {

        BufferedReader br =
                new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {

            int N = Integer.parseInt(br.readLine().trim());
            int M = Integer.parseInt(br.readLine().trim());

            boolean[][] small = new boolean[N + 1][N + 1];

            for (int i = 0; i < M; i++) {

                StringTokenizer st =
                        new StringTokenizer(br.readLine());

                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                small[a][b] = true;
            }

            for (int k = 1; k <= N; k++) {
                for (int i = 1; i <= N; i++) {

                    if (!small[i][k]) {
                        continue;
                    }

                    for (int j = 1; j <= N; j++) {
                        if (small[k][j]) {
                            small[i][j] = true;
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
                    // i < j 또는 j < i 관계를 아는 경우
                    if (small[i][j] || small[j][i]) {
                        count++;
                    }
                }
                // 다른 모든 학생과 비교 가능하면 순서 확정
                if (count == N - 1) {
                    answer++;
                }
            }

            sb.append("#").append(tc)
              .append(" ").append(answer)
              .append("\n");
        }

        System.out.print(sb);
    }
}