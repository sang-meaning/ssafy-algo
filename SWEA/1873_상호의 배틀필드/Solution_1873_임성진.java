import java.util.*;
import java.io.*;

public class Solution_1873_임성진 {
	static char[][] map;
	static int H, W, r, c, dir;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static char[] mark = { '^', 'v', '<', '>' };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine().trim());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine().trim());

			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());

			map = new char[H][W];

			for (int i = 0; i < H; i++) {
				String line = br.readLine().trim();
				for (int j = 0; j < W; j++) {
					map[i][j] = line.charAt(j);
					int d = toMarkDir(map[i][j]);
					if (d != -1) {
						r = i;
						c = j;
						dir = d;
					}
				}
			}

			int num = Integer.parseInt(br.readLine().trim());

			StringBuilder cmd = new StringBuilder();
			while (cmd.length() < num) {
				cmd.append(br.readLine().trim());
			}

			for (int i = 0; i < num; i++) {
				if (cmd.charAt(i) == 'S') shoot();
				else move(toDir(cmd.charAt(i)));
			}

			sb.append('#').append(tc).append(' ');
			for (char[] row : map) {
				sb.append(row).append('\n');
			}
		}
		System.out.print(sb);
	}

	static int toMarkDir(char ch) {
		for (int i = 0; i < 4; i++) {
			if (mark[i] == ch) return i;
		}
		return -1; 
	}

	static void move(int d) {
		dir = d;
		int nr = r + dr[d];
		int nc = c + dc[d];
		map[r][c] = mark[dir];
		if (nr < 0 || nr >= H || nc < 0 || nc >= W)
			return;
		if (map[nr][nc] != '.')
			return;
		map[r][c] = '.';
		r = nr;
		c = nc;
		map[r][c] = mark[dir];
	}

	static void shoot() {
		int nr = r, nc = c;
		while (true) {
			nr += dr[dir];
			nc += dc[dir];
			if (nr < 0 || nr >= H || nc < 0 || nc >= W)
				return;
			if (map[nr][nc] == '*') {
				map[nr][nc] = '.';
				return;
			}
			if (map[nr][nc] == '#')
				return;
		}
	}

	static int toDir(char cmd) {
		switch (cmd) {
		case 'U':
			return 0;
		case 'D':
			return 1;
		case 'L':
			return 2;
		default:
			return 3;
		}
	}
}