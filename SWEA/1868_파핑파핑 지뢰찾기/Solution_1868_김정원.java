package ssafy.swea.kjw;

import java.io.*;
import java.util.*;

public class Solution_1868_김정원 {
	static int N, answer;
	static char[][] map;
	static int[][] mineCnt;
	static boolean[][] visited;

	static int[] dx = {-1, 0, 1, -1, 1, -1, 0, 1};
	static int[] dy = {-1, -1, -1, 0, 0, 1, 1, 1};

	public static void main(String[] args) throws Exception {
		// System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine().trim());

		for (int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(br.readLine().trim());
			map = new char[N][N];
			mineCnt = new int[N][N];
			visited = new boolean[N][N];
			answer = 0;

			// 맵 입력 받기
			for (int i = 0; i < N; i++) {
				String temp = br.readLine().trim();
				for (int j = 0; j < N; j++) {
					map[i][j] = temp.charAt(j);
				}
			}

			// 각 칸 주변에 지뢰가 몇개 있는지 먼저 확인
			for (int y = 0; y < N; y++) {
				for (int x = 0; x < N; x++) {
					if (map[y][x] == '*') {
						mineCnt[y][x] = -1;
						continue;
					}

					int count = 0;
					for (int d = 0; d < 8; d++) {
						int nx = x + dx[d];
						int ny = y + dy[d];

						if (!isMapIn(nx, ny)) continue;

						if (map[ny][nx] == '*') {
							count++;
						}
					}

					mineCnt[y][x] = count;
				}
			}

			// 0인 칸부터 클릭해야 주변 칸들이 같이 열림
			for (int y = 0; y < N; y++) {
				for (int x = 0; x < N; x++) {
					if (visited[y][x]) continue;
					if (mineCnt[y][x] != 0) continue;

					answer++;
					open(x, y);
				}
			}

			// 아직 안열린 칸들은 하나씩 직접 눌러야 함
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == '*') continue;

					if (!visited[i][j]) {
						answer++;
						visited[i][j] = true;
					}
				}
			}

			System.out.println("#" + test_case + " " + answer);
		}
	}

	// 한번 클릭했을 때 같이 열리는 칸들 확인
	static void open(int x, int y) {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {x, y});
		visited[y][x] = true;

		while (!q.isEmpty()) {
			int[] temp = q.poll();
			int cx = temp[0];
			int cy = temp[1];

			for (int d = 0; d < 8; d++) {
				int nx = cx + dx[d];
				int ny = cy + dy[d];

				if (!isMapIn(nx, ny)) continue;
				if (visited[ny][nx]) continue;
				if (mineCnt[ny][nx] == -1) continue;

				// 숫자가 있는 칸도 열리기는 함
				visited[ny][nx] = true;

				// 다만 주변으로 계속 퍼지는건 0인 칸만 가능
				if (mineCnt[ny][nx] == 0) {
					q.offer(new int[] {nx, ny});
				}
			}
		}
	}

	static boolean isMapIn(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}
}