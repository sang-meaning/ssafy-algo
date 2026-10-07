import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

class Solution {
	static int n;
	static int[][] map;
	static int[][] dist;

	static int[][] dir = {
			{ 1, 0 },
			{ 0, 1 },
			{ -1, 0 },
			{ 0, -1 }
	};

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

			// map 입력
			for (int i = 0; i < n; i++) {
				String s = bf.readLine();

				for (int j = 0; j < n; j++) {
					map[i][j] = s.charAt(j) - '0';
				}
			}

			// dist 배열을 최대값으로 초기화
			for (int i = 0; i < n; i++) {
				Arrays.fill(dist[i], Integer.MAX_VALUE);
			}

			dijkstra();

			System.out.println("#" + test_case + " " + dist[n - 1][n - 1]);
		}
	}

	public static void dijkstra() {

		PriorityQueue<Node> pq = new PriorityQueue<>();

		// 시작 위치 비용 0
		dist[0][0] = 0;

		pq.offer(new Node(0, 0, 0));

		while (!pq.isEmpty()) {

			Node cur = pq.poll();

			int x = cur.x;
			int y = cur.y;
			int cost = cur.cost;

			// 이미 더 짧은 경로를 찾았다면 무시
			if (cost > dist[x][y]) {
				continue;
			}

			// 목적지 도착
			if (x == n - 1 && y == n - 1) {
				return;
			}

			// 상하좌우 탐색
			for (int i = 0; i < 4; i++) {

				int nx = x + dir[i][0];
				int ny = y + dir[i][1];

				// 맵 밖이면 무시
				if (nx < 0 || ny < 0 || nx >= n || ny >= n) {
					continue;
				}

				// 현재 위치까지 비용
				// +
				// 다음 위치 복구 비용
				int nextCost = cost + map[nx][ny];

				// 기존에 알고 있던 비용보다 더 작다면 갱신
				if (nextCost < dist[nx][ny]) {

					dist[nx][ny] = nextCost;

					pq.offer(
						new Node(nx, ny, nextCost)
					);
				}
			}
		}
	}
}