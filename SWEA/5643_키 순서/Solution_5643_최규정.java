import java.util.*;

class Solution {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int tc = 1; tc <= T; tc++) {

            int N = sc.nextInt();
            int M = sc.nextInt();

            boolean[][] graph = new boolean[N + 1][N + 1];

            for (int i = 0; i < M; i++) {

                int a = sc.nextInt();
                int b = sc.nextInt();

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

                    if (i == j) {
                        continue;
                    }

                    if (graph[i][j] || graph[j][i]) {
                        count++;
                    }
                }

                if (count == N - 1) {
                    answer++;
                }
            }

            System.out.println("#" + tc + " " + answer);
        }

        sc.close();
    }
}