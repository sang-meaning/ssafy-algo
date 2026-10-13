import java.util.Arrays;

class Solution_합승택시요금_한석호 {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int INF = 200 * 100000; // 최대 지점 수(200) * 최대 요금(100,000)보다 큰 값 설정
        int[][] dist = new int[n + 1][n + 1];

        // 1. 거리 배열 초기화
        for (int i = 1; i <= n; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }

        // 2. 주어진 요금 정보 반영 (양방향 그래프)
        for (int[] fare : fares) {
            int u = fare[0];
            int v = fare[1];
            int cost = fare[2];
            dist[u][v] = cost;
            dist[v][u] = cost;
        }

        // 3. 플로이드-워샬(Floyd-Warshall) 알고리즘 수행
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (dist[i][j] > dist[i][k] + dist[k][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        // 4. 모든 지점 k를 합승 해제 지점으로 가정하여 최솟값 계산
        int answer = INF;
        for (int k = 1; k <= n; k++) {
            // s -> k (합승), k -> a (A 혼자), k -> b (B 혼자)
            int cost = dist[s][k] + dist[k][a] + dist[k][b];
            answer = Math.min(answer, cost);
        }

        return answer;
    }
}