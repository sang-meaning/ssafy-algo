import java.io.*;
import java.util.*;

public class Solution_5656_임성진 {

	static int N, W, H, ans;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine().trim());

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());

			int[][] map = new int[H][W];
			for (int r = 0; r < H; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < W; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}

			ans = Integer.MAX_VALUE;
			dfs(0, map);

			sb.append('#').append(tc).append(' ').append(ans).append('\n');
		}

		System.out.print(sb);
	}

	static void dfs(int cnt, int[][] map) {
		int remain = count(map);
		if (remain == 0 || cnt == N) {
			ans = Math.min(ans, remain);
			return;
		}
		if (ans == 0) return;

		for (int c = 0; c < W; c++) {

			int r = 0;
			while (r < H && map[r][c] == 0) r++;
			if (r == H) continue;

			int[][] next = copy(map);
			boom(next, r, c);
			drop(next);
			dfs(cnt + 1, next);
		}
	}

	static void boom(int[][] map, int r, int c) {
		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] { r, c, map[r][c] });
		map[r][c] = 0;

		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int power = cur[2];

			for (int d = 0; d < 4; d++) {
				int nr = cur[0];
				int nc = cur[1];
				for (int k = 1; k < power; k++) {
					nr += dr[d];
					nc += dc[d];
					if (nr < 0 || nr >= H || nc < 0 || nc >= W) break;
					if (map[nr][nc] == 0) continue;

					if (map[nr][nc] > 1) {
						q.add(new int[] { nr, nc, map[nr][nc] });
					}
					map[nr][nc] = 0;
				}
			}
		}
	}

	static void drop(int[][] map) {
		for (int c = 0; c < W; c++) {
			int idx = H - 1;
			for (int r = H - 1; r >= 0; r--) {
				if (map[r][c] != 0) {
					int v = map[r][c];
					map[r][c] = 0;
					map[idx--][c] = v;
				}
			}
		}
	}

	static int count(int[][] map) {
		int cnt = 0;
		for (int r = 0; r < H; r++) {
			for (int c = 0; c < W; c++) {
				if (map[r][c] != 0) cnt++;
			}
		}
		return cnt;
	}

	static int[][] copy(int[][] map) {
		int[][] res = new int[H][W];
		for (int r = 0; r < H; r++) {
			res[r] = map[r].clone();
		}
		return res;
	}
}
