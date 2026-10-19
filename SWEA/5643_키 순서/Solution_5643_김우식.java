
import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in)
        );
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine());
            int M = Integer.parseInt(br.readLine());

            boolean[][] graph = new boolean[N + 1][N + 1];

            for (int i = 0; i < M; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());

                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                graph[a][b] = true;
            }

            for (int k = 1; k <= N; k++) {
                for (int i = 1; i <= N; i++) {
                    for (int j = 1; j <= N; j++) {
                        if (graph[i][k] && graph[k][j]) {
                            graph[i][j] = true;
                        }
                    }
                }
            }

            int answer = 0;

            for (int i = 1; i <= N; i++) {
                int count = 0;

                for (int j = 1; j <= N; j++) {
                    if (graph[i][j] || graph[j][i]) {
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
