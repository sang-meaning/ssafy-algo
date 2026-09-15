package alg_prac;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

class Solution_1868_이승규 {
	static char[][] map;
	static int n, toDelete, click;
	static boolean[][] checked;
	static int[][] dir = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 }, { 1, 1 }, { -1, -1 }, { -1, 1 }, { 1, -1 } };

	public static void main(String args[]) throws Exception {

		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine());
		String s;
		StringTokenizer st;
		StringBuilder sb;

		for (int test_case = 1; test_case <= T; test_case++) {
			sb = new StringBuilder();
			n = Integer.parseInt(bf.readLine());
			map = new char[n][n];
			toDelete = n * n;

			for (int i = 0; i < n; i++) {
				s = bf.readLine();
				for (int j = 0; j < n; j++) {
					map[i][j] = s.charAt(j);
					if (map[i][j] == '*')
						toDelete--;
				}
			}

			checked = new boolean[n][n];
			click = 0;

			selectZero();

			click += toDelete;

			sb.append("#").append(test_case).append(" ").append(click);
			System.out.println(sb);
		}
	}

	public static void selectZero() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (map[i][j] == '*')
					continue;
				if (!checked[i][j]) {
					if (checkZero(i, j)) {
						revealZero(i, j);
						click++;
					}
				}
			}
		}
	}

	public static boolean checkZero(int x, int y) {
		for (int i = 0; i < 8; i++) {
			int nx = x + dir[i][0];
			int ny = y + dir[i][1];

			if (nx < 0 || ny < 0 || nx >= n || ny >= n)
				continue;
			if (map[nx][ny] == '*')
				return false;
		}
		return true;
	}

	public static void revealZero(int x, int y) {
		// bfs
		// 맨 처음, 클릭한 곳을 q에 넣는다.
		// while(!q.isEmpty())
		// q. poll
		// checked = true
		// todelete --;
		// checkZero가 true라면 checked가 false인 주변 8개 칸을 q에 넣는다
		// checkZero가 false라면

		ArrayDeque<int[]> q = new ArrayDeque<>();
		int[] pos = new int[2];
		pos[0] = x;
		pos[1] = y;
		q.offer(pos);

		while (!q.isEmpty()) {
			pos = q.poll();
			if(checked[pos[0]][pos[1]]) 
				continue;
			checked[pos[0]][pos[1]] = true;
			toDelete--;
			if (checkZero(pos[0], pos[1])) {
				for (int i = 0; i < 8; i++) {
					int nx = pos[0] + dir[i][0];
					int ny = pos[1] + dir[i][1];

					if (nx < 0 || ny < 0 || nx >= n || ny >= n)
						continue;
					if (!checked[nx][ny]) {
						q.offer(new int[] { nx, ny });
					}
				}
			}
		}
	}
}
