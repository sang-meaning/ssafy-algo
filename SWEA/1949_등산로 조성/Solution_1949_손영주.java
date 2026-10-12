package submission;

import java.util.*;
import java.io.*;

public class Solution{

	static int N, K, max;
	static int[][] map;
	static boolean[][] visited;
	

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int test_case_num = 1; test_case_num <= T; test_case_num++) {

			StringTokenizer st = new StringTokenizer(br.readLine(), " ");

			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			map = new int[N][N];
			visited = new boolean[N][N];

			int high = 0;

			for (int c = 0; c < N; c++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int r = 0; r < N; r++) {
					map[c][r] = Integer.parseInt(st.nextToken());
					if (map[c][r] > high) {
						high = map[c][r];
					}
				}
			} // 입력
			max = 0;
			for (int c = 0; c < N; c++) {
				for (int r = 0; r < N; r++) {
					if (map[c][r] == high) {
						visited[c][r] = true;
						makeTrail(c, r, 1, true);
						visited[c][r] = false;
					}
				}
			}

			System.out.println("#" + test_case_num + " " + max);
		}
	}

	static int[] dr = { 0, 0, -1, 1 }; // 상하좌우
	static int[] dc = { -1, 1, 0, 0 };

	static void makeTrail(int c, int r, int cnt, boolean chance) {

		max = Math.max(cnt, max);

		int cur = map[c][r];
		for (int dir = 0; dir < 4; dir++) {
			int nr = r + dr[dir];
			int nc = c + dc[dir];
			
			if (nr >= N || nr < 0 || nc >= N || nc < 0) { // 경계면 다음 방향
				continue;
			}
			if (visited[nc][nr]) { // 현루트에서 갔던 곳이면 다른 방향
				continue;
			}

			if (map[nc][nr] < cur) { // 다음이 현재 위치보다 낮으면 이동
				visited[nc][nr] = true;
				makeTrail(nc, nr, cnt + 1, chance);
				visited[nc][nr] = false;
			} else if (map[nc][nr] - cur < K && chance) { // 공사를 아직 안 했고 공사하면 낮아질 경우 공사하고 이동
				int temp = map[nc][nr];
				map[nc][nr] = cur - 1; // 현재 높이보다 1칸 작게 깎는다.
				visited[nc][nr] = true;
				makeTrail(nc, nr, cnt + 1, false);
				map[nc][nr] = temp;
				visited[nc][nr] = false;
			}
		}
	}

}