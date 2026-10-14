import java.util.Arrays;

class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        final int INF = 1_000_000_000;
        int[][] dist = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }

        // 양방향 요금 설정
        for (int[] fare : fares) {
            int u = fare[0];
            int v = fare[1];
            int cost = fare[2];

            dist[u][v] = cost;
            dist[v][u] = cost;
        }

        // 모든 지점 사이의 최소 요금 계산
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                if (dist[i][k] == INF) continue;

                for (int j = 1; j <= n; j++) {
                    if (dist[k][j] == INF) continue;
                    dist[i][j] = Math.min(dist[i][j],dist[i][k] + dist[k][j]);
                }
            }
        }

        int answer = INF;

        // k까지 합승한 뒤 각자 목적지로 이동
        for (int k = 1; k <= n; k++) {
            if (dist[s][k] == INF || dist[k][a] == INF || dist[k][b] == INF) {
                continue;
            }

            int total = dist[s][k] + dist[k][a] + dist[k][b];
            answer = Math.min(answer, total);
        }

        return answer;
    }
}