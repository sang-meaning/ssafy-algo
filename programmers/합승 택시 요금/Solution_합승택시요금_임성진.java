import java.util.Arrays;

class Solution {

	static final int INF = 20_000_001;

	public int solution(int n, int s, int a, int b, int[][] fares) {
		int[][] dist = new int[n + 1][n + 1];
		for (int i = 1; i <= n; i++) {
			Arrays.fill(dist[i], INF);
			dist[i][i] = 0;
		}

		for (int[] f : fares) {
			dist[f[0]][f[1]] = f[2];
			dist[f[1]][f[0]] = f[2];
		}

		for (int k = 1; k <= n; k++) {
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= n; j++) {
					if (dist[i][k] + dist[k][j] < dist[i][j]) {
						dist[i][j] = dist[i][k] + dist[k][j];
					}
				}
			}
		}

		int answer = Integer.MAX_VALUE;
		for (int k = 1; k <= n; k++) {
			answer = Math.min(answer, dist[s][k] + dist[k][a] + dist[k][b]);
		}

		return answer;
	}
}
