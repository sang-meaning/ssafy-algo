public class Solution {
	public int solution(int n, int s, int a, int b, int[][] fares) {
		int[][] dist = new int[n+1][n+1];
		for (int i=1; i<=n; i++) {
			for (int j=1; j<=n; j++) {
				if (i == j) continue;
				dist[i][j] = Integer.MAX_VALUE;
			}
		}

		for (int i=0; i<fares.length; i++) {
			int u = fares[i][0];
			int v = fares[i][1];
			int w = fares[i][2];
			dist[u][v] = dist[v][u] = w;
		}
		
		for (int k=1; k<=n; k++) {
			for (int i=1; i<=n; i++) {
				for (int j=1; j<=n; j++) {
					if (dist[i][k] == Integer.MAX_VALUE || dist[k][j] == Integer.MAX_VALUE) continue;
					
					dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
				}
			}
		}
		
		int answer = Integer.MAX_VALUE;
		for (int k=1; k<=n; k++) {
			if (dist[s][k] == Integer.MAX_VALUE || dist[k][a] == Integer.MAX_VALUE || dist[k][b] == Integer.MAX_VALUE) continue;
			answer = Math.min(answer, dist[s][k] + dist[k][a] + dist[k][b]);
		}
		return answer;
	}
}