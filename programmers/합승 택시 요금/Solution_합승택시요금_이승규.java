import java.util.*;

class Solution {

    public int solution(int n, int s, int a, int b, int[][] fares) {

        final long INF = Long.MAX_VALUE / 4;
        long[][] dist = new long[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }

        for (int[] fare : fares) {
            int from = fare[0];
            int to = fare[1];
            int cost = fare[2];

            dist[from][to] = cost;
            dist[to][from] = cost;
        }

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {

                    if (dist[i][k] == INF || dist[k][j] == INF) {
                        continue;
                    }

                    dist[i][j] = Math.min(
                        dist[i][j],
                        dist[i][k] + dist[k][j]
                    );
                }
            }
        }

        long answer = INF;

        for (int k = 1; k <= n; k++) {
            if (dist[s][k] == INF ||
                dist[k][a] == INF ||
                dist[k][b] == INF) {
                continue;
            }

            long cost = dist[s][k] + dist[k][a] + dist[k][b];

            answer = Math.min(answer, cost);
        }

        return (int) answer;
    }
}