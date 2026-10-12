package _submission;

import java.util.*;
import java.io.*;

public class Solution {

	static int H, W;

	static char[][] map;

	static int[] dx = { 0, 0, -1, 1 }; // 상하좌우
	static int[] dy = { -1, 1, 0, 0 };

	static char[] dirToChar = { '^', 'v', '<', '>' };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int test_case_num = 1; test_case_num <= T; test_case_num++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");

			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			map = new char[H][W];

			int x = 0;
			int y = 0;
			int dir = 0;

			for (int i = 0; i < H; i++) {
				String line = br.readLine();
				for (int j = 0; j < W; j++) {
					map[i][j] = line.charAt(j);
					switch (map[i][j]) {
					case '^':
						dir = 0;
						x = j;
						y = i;
						break;
					case 'v':
						dir = 1;
						x = j;
						y = i;
						break;
					case '<':
						dir = 2;
						x = j;
						y = i;
						break;
					case '>':
						dir = 3;
						x = j;
						y = i;
						break;
					}
				}
			} // input

			String commandLength = br.readLine();
			String command = br.readLine();

			for (int i = 0; i < command.length(); i++) {
				char key = command.charAt(i);
				if (key == 'S') {
					shot(dir, x, y);
				} else {
					switch (key) {
					case 'U':
						dir = 0;
						break;
					case 'D':
						dir = 1;
						break;
					case 'L':
						dir = 2;
						break;
					case 'R':
						dir = 3;
						break;
					}
					boolean inRange = x + dx[dir] < W && x + dx[dir] >= 0 && y + dy[dir] < H && y + dy[dir] >= 0;
					if (inRange && map[y + dy[dir]][x + dx[dir]] == '.') {
						map[y][x] = '.';
						x += dx[dir];
						y += dy[dir];
						map[y][x] = dirToChar[dir];
					} else {
						map[y][x] = dirToChar[dir];
					}
				}
			}

			System.out.print("#" + test_case_num + " ");
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					System.out.print(map[i][j]);
				}
				System.out.println();
			}
		}
	} // 음수 범위 검사

	static void shot(int dir, int x, int y) {
		while (x + dx[dir] < W && x + dx[dir] >= 0 && y + dy[dir] < H && y + dy[dir] >= 0) {
			x += dx[dir];
			y += dy[dir];
			if (map[y][x] == '*') {
				map[y][x] = '.';
				return;
			} else if (map[y][x] == '#') {
				return;
			}
		}
	}
}