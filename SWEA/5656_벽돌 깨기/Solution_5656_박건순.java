import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {
	static int N, W, H, minRemain;
	static int[][] map;
	static int[] dr = { 0, 1, 0, -1 };
	static int[] dc = { 1, 0, -1, 0 };

	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T;
		T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			minRemain = Integer.MAX_VALUE;
			map = new int[H][W];
			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			dfs(0, map);

			System.out.println(String.format("#%d %d", test_case, minRemain));
		}
	}

	static void dfs(int count, int[][] map) {
		if (minRemain == 0)
			return;

		int remain = countBricks(map);

		if (remain == 0) {
			minRemain = 0;
			return;
		}

		if (count == N) {
			minRemain = Math.min(minRemain, remain);
			return;
		}

		for (int c = 0; c < W; c++) {
			int r = 0;

			while (r < H && map[r][c] == 0) {
				r++;
			}

			if (r == H)
				continue;

			int[][] nextMap = copyMap(map);

			boomBricks(r, c, nextMap);
			gravity(nextMap);

			dfs(count + 1, nextMap);
		}
	}

	static void boomBricks(int r, int c, int[][] map) {
		int boomRange = map[r][c];
		map[r][c] = 0;

		for (int d = 0; d < 4; d++) {
			for (int distance = 1; distance < boomRange; distance++) {
				int nr = r + dr[d] * distance;
				int nc = c + dc[d] * distance;

				if (nr < 0 || nr >= H || nc < 0 || nc >= W) {
					break;
				}

				if (map[nr][nc] == 0) {
					continue;
				}

				boomBricks(nr, nc, map);
			}
		}

	}

	static void gravity(int[][] map) {
		for (int c = 0; c < W; c++) {
			int writeRow = H - 1;

			for (int r = H - 1; r >= 0; r--) {
				if (map[r][c] == 0)
					continue;

				int brick = map[r][c];
				map[r][c] = 0;
				map[writeRow][c] = brick;
				writeRow--;
			}
		}
	}

	static int countBricks(int[][] map) {
		int count = 0;
		for (int r = 0; r < H; r++) {
			for (int c = 0; c < W; c++) {
				if (map[r][c] != 0) {
					count++;
				}
			}
		}
		return count;
	}

	static int[][] copyMap(int[][] map) {
		int[][] copy = new int[H][];

		for (int i = 0; i < H; i++) {
			copy[i] = map[i].clone();
		}

		return copy;
	}
}