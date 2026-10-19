package submission;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

	static int N, W, H, min;
	static int[][] map;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {

			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			map = new int[H][W];
			for (int r = 0; r < H; r++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int c = 0; c < W; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			} // input

			// dfs 가 좋겠다.
			min = Integer.MAX_VALUE;
			dropBall(0);

			System.out.println("#" + test_case + " " + min);
		}
	}

	static int countRemain() {
		int count = 0;
		for (int r = 0; r < H; r++) {
			for (int c = 0; c < W; c++) {
				if (map[r][c] != 0)
					count++;
			}
		}
		return count;
	}

	static void dropBall(int cnt) {
		min = Math.min(countRemain(), min);

		if (cnt == N || min == 0) {
			return;
		}

		for (int w = 0; w < W; w++) {
			int[][] temp = new int[H][W];
			for (int i = 0; i < H; i++) {
				temp[i] = Arrays.copyOf(map[i], W);
			}

			boolean isHitted = false;
			for (int h = 0; h < H; h++) {
				if (map[h][w] != 0) {
					brickBomb(h, w);
					gravity();
					isHitted = true;
					break;
				}
			}
			if (!isHitted)
				continue;
			dropBall(cnt + 1);
			map = temp;
		}
	}

	// 벽돌을 연쇄로 부숨
	// 부서지는 벽돌의 좌표를 받음
	static void brickBomb(int h, int w) {
		int range = map[h][w] - 1;
		map[h][w] = 0;
		if (range == 0)
			return;
		for (int i = 1; i <= range; i++) {
			for (int dir = 0; dir < 4; dir++) {
				int nr = h + dr[dir] * i;
				int nc = w + dc[dir] * i;
				if (nr < 0 || nr >= H || nc < 0 || nc >= W)
					continue;
				if (map[nr][nc] != 0) {
					brickBomb(nr, nc);
				}
			}
		}
	}

	// 블럭이 아래로 떨어짐
	static void gravity() {
		for (int c = 0; c < W; c++) {
			int bottom = H - 1;
			for (int r = H - 1; r >= 0; r--) {
				if (map[r][c] != 0) {
					int temp = map[r][c];
					map[r][c] = 0;
					map[bottom--][c] = temp;
				}
			}
		}
	}

}
