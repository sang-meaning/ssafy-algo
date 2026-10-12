import java.io.BufferedReader;
import java.io.InputStreamReader;

class Solution {
	static int N;
	static char[][] charArr;
	static boolean[][] visited;
	static int minClick;
	static int[] dr = { -1, 1, 0, 0, -1, -1, 1, 1 };
	static int[] dc = { 0, 0, -1, 1, -1, 1, -1, 1 };

	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T;
		T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(br.readLine());
			charArr = new char[N][N];
			visited = new boolean[N][N];
			minClick = 0;
			for (int i = 0; i < N; i++) {
				String line = br.readLine();
				for (int j = 0; j < N; j++) {
					charArr[i][j] = line.charAt(j);
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (charArr[i][j] != '*' && !visited[i][j]) {
						if (!findMine(i, j)) {
							visited[i][j] = true;
							minClick++;
						}
					}
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (charArr[i][j] != '*' && !visited[i][j]) {
						minClick++;
					}
				}
			}

			System.out.println("#" + test_case + " " + minClick);
		}
	}

	static boolean findMine(int r, int c) {
		boolean findedMine = false;

		for (int i = 0; i < 8; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];

			if (nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc])
				continue;

			if (charArr[nr][nc] == '*') {
				findedMine = true;
			}

		}

		if (!findedMine) {
			for (int i = 0; i < 8; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];

				if (nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc])
					continue;

				visited[nr][nc] = true;
				findMine(nr, nc);

			}
		}

		return findedMine;
	}

}