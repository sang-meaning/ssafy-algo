package _submission;

import java.util.*;
import java.io.*;

public class Solution {

	static final int SIZE = 100;
	static int[][] map = new int[SIZE][SIZE];

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = 10;

		for (int test_case_num = 1; test_case_num <= T; test_case_num++) {

			String tc = br.readLine();

			for (int i = 0; i < SIZE; i++) {
				String line = br.readLine();
				for (int j = 0; j < SIZE; j++) {
					map[i][j] = line.charAt(j) - '0';
				}
			} // input

			int answer = 0;
			Queue<int[]> q = new ArrayDeque<int[]>();
			// 큐, BFS

			// 항상 시작지점이 1,1
			q.offer(new int[] { 1, 1 });
			map[1][1] = 1;

			while (!q.isEmpty()) {
				int[] cur = q.poll();

				for (int dir = 0; dir < 4; dir++) {
					int nr = cur[0] + dr[dir];
					int nc = cur[1] + dc[dir];

					if (nr < 0 || nr >= SIZE || nc < 0 || nc >= SIZE) continue;
					if (map[nr][nc] == 1) continue;
					if (map[nr][nc] == 3) {
						answer = 1;
						q.clear();
						break;
					}
					q.offer(new int[] { nr, nc });
					map[nr][nc] = 1;
				}
			}
			System.out.println("#" + test_case_num + " " + answer);
		}
	}

}