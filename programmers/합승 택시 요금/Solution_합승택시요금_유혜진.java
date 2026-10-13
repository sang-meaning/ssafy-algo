import java.util.*;

// 프로그래머스 - 합승 택시 요금 (플로이드-워셜)
class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        final int INF = 100_000_000;
        int[][] dist = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }
        for (int[] f : fares) {
            dist[f[0]][f[1]] = f[2];
            dist[f[1]][f[0]] = f[2];
        }

        // 모든 지점 사이의 최단 요금
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        // s → k 까지 합승, k에서 각자 a, b로 이동 (k == s면 합승 안 함)
        int answer = INF;
        for (int k = 1; k <= n; k++) {
            answer = Math.min(answer, dist[s][k] + dist[k][a] + dist[k][b]);
        }
        return answer;
    }
}