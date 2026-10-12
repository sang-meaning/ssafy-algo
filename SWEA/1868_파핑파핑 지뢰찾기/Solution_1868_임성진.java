import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_1868_임성진 {

	static int N;
	static char[][] map;
	static int[][] mineCount;
	static boolean[][] visited;

	static int[] dr = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static int[] dc = { -1, 0, 1, -1, 1, -1, 0, 1 };

	static int[] queueR;
	static int[] queueC;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine().trim());

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine().trim());

			map = new char[N][N];
			for (int r = 0; r < N; r++) {
				String line = br.readLine().trim();
				for (int c = 0; c < N; c++) {
					map[r][c] = line.charAt(c);
				}
			}

			mineCount = new int[N][N];
			visited = new boolean[N][N];
			queueR = new int[N * N];
			queueC = new int[N * N];

			countMine();

			int answer = 0;

			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (map[r][c] == '*')
						continue;
					if (visited[r][c])
						continue;
					if (mineCount[r][c] != 0)
						continue;

					open(r, c);
					answer++;
				}
			}

			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (map[r][c] == '.' && !visited[r][c]) {
						answer++;
					}
				}
			}

			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}

		System.out.print(sb);
	}

	static void countMine() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (map[r][c] == '*')
					continue;

				int count = 0;
				for (int d = 0; d < 8; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];

					if (!isIn(nr, nc))
						continue;
					if (map[nr][nc] == '*')
						count++;
				}

				mineCount[r][c] = count;
			}
		}
	}

	static void open(int startR, int startC) {
		int head = 0;
		int tail = 0;

		queueR[tail] = startR;
		queueC[tail] = startC;
		tail++;
		visited[startR][startC] = true;

		while (head < tail) {
			int r = queueR[head];
			int c = queueC[head];
			head++;

			for (int d = 0; d < 8; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];

				if (!isIn(nr, nc))
					continue;
				if (visited[nr][nc])
					continue;

				visited[nr][nc] = true; 

				if (mineCount[nr][nc] == 0) {
					queueR[tail] = nr;
					queueC[tail] = nc;
					tail++;
				}
			}
		}
	}

	static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}
}