package _submission;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static char[][] map;
	static int N;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		for (int test_case_num = 1; test_case_num <= T; test_case_num++) {

			N = Integer.parseInt(br.readLine());
			map = new char[N][N];

			for (int r = 0; r < N; r++) {
				String line = br.readLine();
				for (int c = 0; c < N; c++) {
					map[r][c] = line.charAt(c);
				}
			}

			int click = 0;
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (map[r][c] == '.' && countBomb(r, c) == 0) {
						unvail(r, c);
						click++;
					}
				}
			} // 남은 .들은 폭탄이 1개 이상인 것.

			// 주변에 폭탄이 없는 칸부터 클릭
			// 연쇄적으로 밝혀지는 칸들을 밝히는 클릭수  + 주변에 폭탄이 있는 칸의 개수 (= 클릭 수)

			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (map[r][c] == '.') {
						click++;
					}
				}
			}

			System.out.println("#" + test_case_num + " " + click);
		}
	}

	static int[] dr = { -1, -1, 0, 1, 1, 1, 0, -1 }; // 상 우상 우 우하 하 좌하 좌 좌상
	static int[] dc = { 0, 1, 1, 1, 0, -1, -1, -1 }; // 상 우상 우 우하 하 좌하 좌 좌상

//	지뢰가 없는 칸이라면 변이 맞닿아 있거나 꼭지점이 맞닿아 있는 최대 8칸에 대해 몇 개의 지뢰가 있는지가 0에서 8사이의 숫자로 클릭한 칸에 표시된다.
//	만약 이 숫자가 0이라면 근처의 8방향에 지뢰가 없다는 것이 확정된 것이기 때문에 그 8방향의 칸도 자동으로 숫자를 표시해 준다.
	static void unvail(int r, int c) {
		map[r][c] = (char) (countBomb(r, c) + '0'); // 기록
		if (map[r][c] == '0') {

			for (int dir = 0; dir < 8; dir++) {

				int nr = r + dr[dir];
				int nc = c + dc[dir];

				if (!inRange(nr, nc)) {
					continue;
				}
				if (map[nr][nc] == '.') {
					unvail(nr, nc);
				}
			}
		}
	}

	static int countBomb(int r, int c) {
		int count = 0;
		for (int dir = 0; dir < 8; dir++) {

			int nr = r + dr[dir];
			int nc = c + dc[dir];

			if (!inRange(nr, nc)) {
				continue;
			}
			if (map[nr][nc] == '*') {
				count++;
			}
		}
		return count;
	}

	// RXC
	static boolean inRange(int r, int c) {
		return (r < N && r >= 0 && c < N && c >= 0);
	}

}
