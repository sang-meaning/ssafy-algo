package _submission;

import java.util.*;
import java.io.*;

public class Solution {

	static int N;
	static int[][] cheese;
	static final int MAX_DAY = 100;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 계획
		// i인칸들을 0으로 만든다
		// 덩어리개수를 센다

		int T = Integer.parseInt(br.readLine());

		for (int test_case_num = 1; test_case_num <= T; test_case_num++) {

			N = Integer.parseInt(br.readLine());

			cheese = new int[N][N];

			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					cheese[i][j] = Integer.parseInt(st.nextToken());
				}
			} // 인풋

			int max = 1; // 첫날에 다 먹어버리면 치즈덩어리가 0이 되는게 아니었다 // 0일의 치즈 덩이

			for (int i = 1; i <= MAX_DAY; i++) {

				for (int r = 0; r < N; r++) {
					for (int c = 0; c < N; c++) {
						if (cheese[r][c] == i) {
							cheese[r][c] = 0;
						}
					}
				}

				int cnt = 0;
				boolean[][] visited = new boolean[N][N];
				// ff의 방문배열. 탐색한 덩어리 표시와 ff로 방문한 이어진 칸 표시 동시에 담당
				
				for (int r = 0; r < N; r++) {
					for (int c = 0; c < N; c++) {
						if (cheese[r][c] != 0 && !visited[r][c]) {
							cnt++;
							FF(r, c, visited);
						}
					}
				}

				if (cnt > max) {
					max = cnt;
				}
			}

			System.out.println("#" + test_case_num + " " + max);
		}
	}

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	static void FF(int r, int c, boolean[][] visited) {

		visited[r][c] = true;

		for (int dir = 0; dir < 4; dir++) {
			int nr = r + dr[dir];
			int nc = c + dc[dir];

			if (nr >= N || nr < 0 || nc >= N || nc < 0) continue;
			if (visited[nr][nc] == true) continue;

			if (cheese[nr][nc] != 0) {
				FF(nr, nc, visited);
			}
		}
	}
}