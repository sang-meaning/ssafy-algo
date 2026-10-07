import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

class Solution {
	static int N;
	static int[][] map;
	static int[][] minDist;
	static int[] dr = { 1, -1, 0, 0 };
	static int[] dc = { 0, 0, 1, -1 };

	static class Node implements Comparable<Node> {
		int r, c, cost;

		Node(int r, int c, int cost) {
			this.r = r;
			this.c = c;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.cost, o.cost);
		}

	}

	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T;
		T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			final int INF = Integer.MAX_VALUE;
			N = Integer.parseInt(br.readLine());

			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				String line = br.readLine();

				for (int j = 0; j < N; j++) {
					map[i][j] = line.charAt(j) - '0';
				}
			}

			minDist = new int[N][N];
			for (int k = 0; k < N; k++) {
				Arrays.fill(minDist[k], INF);
			}

			PriorityQueue<Node> pq = new PriorityQueue<>();
			minDist[0][0] = 0;
			pq.offer(new Node(0, 0, 0));
			while (!pq.isEmpty()) {
				Node cur = pq.poll();
				for (int i = 0; i < 4; i++) {
					int nr = cur.r + dr[i];
					int nc = cur.c + dc[i];

					if (nr < 0 || nr >= N || nc < 0 || nc >= N) {
						continue;
					}
					if (minDist[nr][nc] > cur.cost + map[nr][nc]) {
						minDist[nr][nc] = cur.cost + map[nr][nc];
						pq.offer(new Node(nr, nc, minDist[nr][nc]));
					}

				}
			}
			System.out.println("#" + test_case + " " + minDist[N - 1][N - 1]);
		}
	}
}