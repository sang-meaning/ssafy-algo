
import java.io.*;
import java.util.*;

class Solution {
    static int N, M;
    static boolean[][] graph;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
        );

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            M = Integer.parseInt(br.readLine());

            graph = new boolean[N + 1][N + 1];

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

            int answer = N;

            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (i == j) continue;

                    if (!graph[i][j] && !graph[j][i]) {
                        answer--;
                        break;
                    }
                }
            }

            System.out.println("#" + tc + " " + answer);
        }
    }
}
