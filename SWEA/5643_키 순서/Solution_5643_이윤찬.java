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

            boolean[][] smaller = new boolean[N + 1][N + 1];

            for (int m = 0; m < M; m++) {
                StringTokenizer st = new StringTokenizer(br.readLine());

                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                smaller[a][b] = true;
            }

            // k를 거쳐 알 수 있는 비교 관계 추가
            for (int k = 1; k <= N; k++) {
                for (int i = 1; i <= N; i++) {

                    // i < k를 모르면 k를 통해 추론할 수 없음
                    if (!smaller[i][k]) {
                        continue;
                    }

                    for (int j = 1; j <= N; j++) {
                        if (smaller[k][j]) {
                            smaller[i][j] = true;
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

                    // i < j 또는 j < i를 알면 비교 가능한 학생
                    if (smaller[i][j] || smaller[j][i]) {
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