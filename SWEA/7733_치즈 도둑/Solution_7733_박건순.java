import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {
	static int N;
	static int[][] cheese;
	static int maxCount;
	static int bestCheese;
	static boolean[][] visited;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T;
		T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(br.readLine());
			bestCheese = 0;
			maxCount = 1;
			cheese = new int[N][N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					cheese[i][j] = Integer.parseInt(st.nextToken());

					if (cheese[i][j] > bestCheese) {
						bestCheese = cheese[i][j];
					}
				}
			}
			cheeseCount(1);
			System.out.println("#" + test_case + " " + maxCount);
		}
	}

	static void cheeseCount(int day) {
		if (day == bestCheese) {
			return;
		}
		visited = new boolean[N][N];
		int count = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (cheese[i][j] > day && !visited[i][j]) {
					dfs(day, i, j);
					count++;
				}
			}
		}
		if (count > maxCount) {
			maxCount = count;
		}
		cheeseCount(day + 1);
	}

	static void dfs(int day, int r, int c) {
		visited[r][c] = true;

		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];

			if (nr < 0 || nr >= N || nc < 0 || nc >= N)
				continue;
			if (visited[nr][nc])
				continue;
			if (cheese[nr][nc] <= day)
				continue;

			dfs(day, nr, nc);
		}
	}
}