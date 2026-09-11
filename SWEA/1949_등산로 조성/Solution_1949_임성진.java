import java.util.*;
import java.io.*;

public class Solution_1949_임성진 {
	static int N, K, max;
	static int[][] map;
	static boolean[][] visited;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine().trim());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine().trim());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			map = new int[N][N];
			visited = new boolean[N][N];
			int peak = 0;

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine().trim());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					peak = Math.max(peak, map[i][j]);
				}
			}

			max = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] != peak) continue;  // 최고봉인 칸에서만 출발
					visited[i][j] = true;
					dfs(i, j, 1, false);
					visited[i][j] = false;
				}
			}
			sb.append('#').append(tc).append(' ').append(max).append('\n');
		}
		System.out.print(sb);
	}

	static void dfs(int r, int c, int len, boolean cut) {
		max = Math.max(max, len);

		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];

			if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
			if (visited[nr][nc]) continue;

			if (map[nr][nc] < map[r][c]) {
				visited[nr][nc] = true;
				dfs(nr, nc, len + 1, cut);
				visited[nr][nc] = false;

			} else if (!cut && map[nr][nc] - K < map[r][c]) {   // K 이내로 깎으면
				int origin = map[nr][nc];
				map[nr][nc] = map[r][c] - 1;          // 필요한 최소만
				visited[nr][nc] = true;

				dfs(nr, nc, len + 1, true);

				visited[nr][nc] = false;
				map[nr][nc] = origin;
			}
		}
	}
}