import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

class Solution {
	static int n;
	static int[][] map;
	static int[][] dist;

	static int[][] dir = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };

	static class Node implements Comparable<Node> {
		int x;
		int y;
		int cost;

		public Node(int x, int y, int cost) {
			this.x = x;
			this.y = y;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}
	}

	public static void main(String args[]) throws Exception {

		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			n = Integer.parseInt(bf.readLine());

			map = new int[n][n];
			dist = new int[n][n];
			
			for (int i = 0; i < n; i++) {
				String s = bf.readLine();
				for (int j = 0; j < n; j++) {
					map[i][j] = s.charAt(j) - '0';
				}
			}
			for (int i = 0; i < n; i++) {
				Arrays.fill(dist[i], Integer.MAX_VALUE);
			}

			dijkstra();

			System.out.println("#" + test_case + " " + dist[n - 1][n - 1]);
		}
	}

	public static void dijkstra() {
		PriorityQueue<Node> pq = new PriorityQueue<>();

		dist[0][0] = 0;

		pq.offer(new Node(0, 0, 0));

		while (!pq.isEmpty()) {
			Node cur = pq.poll();

			int x = cur.x;
			int y = cur.y;
			int cost = cur.cost;

			if (cost > dist[x][y]) {
				continue;
			}

			if (x == n - 1 && y == n - 1) {
				return;
			}

			for (int i = 0; i < 4; i++) {

				int nx = x + dir[i][0];
				int ny = y + dir[i][1];

				if (nx < 0 || ny < 0 || nx >= n || ny >= n) {
					continue;
				}

				int nextCost = cost + map[nx][ny];

				if (nextCost < dist[nx][ny]) {
					dist[nx][ny] = nextCost;
					pq.offer(new Node(nx, ny, nextCost));
				}
			}
		}
	}
}