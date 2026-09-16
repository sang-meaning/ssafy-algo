import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static int T;
	static int N;
	static char[][] map;
	static int[] dx = { 1, -1, 0, 0, 1, 1, -1, -1 };
	static int[] dy = { 0, 0, 1, -1, 1, -1, 1, -1 };
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());
		String st;
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new char[N][N];
			visited = new boolean[N][N];
			for (int y = 0; y < N; y++) {
				st= br.readLine();
				for (int x = 0; x < N; x++) {
					map[y][x] = st.charAt(x);
				}
			}

			int answer = 0;

			for (int y = 0; y < N; y++) {
				for (int x = 0; x < N; x++) {
					// 방문기록 없고, 지뢰가 없고, 주변에 지뢰가 없는경우에만
					if (map[y][x] == '.' && !visited[y][x] && checkbomb(x, y) == 0) {
						bfs(x, y);
						answer++;
					}
				}
			}

			// 방문 못하는 지뢰없는 장소.
			for (int y = 0; y < N; y++) {
				for (int x = 0; x < N; x++) {
					if (map[y][x] == '.' && !visited[y][x]) {
						answer++;
					}
				}
			}
		
		System.out.println("#"+tc+" "+answer);
		}

	}

	// bfs는 queue 이용..
	private static void bfs(int x, int y) {
		// TODO Auto-generated method stub
		Queue<int[]> que = new ArrayDeque<>();

		que.offer(new int[] { x, y });
		visited[y][x] = true;

		while (!que.isEmpty()) {
			int[] now = que.poll();

			int nowX = now[0];
			int nowY = now[1];

			if (checkbomb(nowX, nowY) != 0) {
				continue;
			}

			for (int d = 0; d < 8; d++) {
				int nx = nowX + dx[d];
				int ny = nowY + dy[d];

				if (ny < 0 || ny >= N || nx < 0 || nx >= N)
					continue;
				if (map[ny][nx] == '*')
					continue;

				if (visited[ny][nx])
					continue;

				visited[ny][nx] = true;
				if (checkbomb(nx, ny) == 0) {
					que.offer(new int[] { nx, ny });
				}
			}

		}

	}

	public static int checkbomb(int x, int y) {

		int count = 0;

		for (int d = 0; d < 8; d++) {
			int ny = y + dy[d];
			int nx = x + dx[d];

			if (ny < 0 || ny >= N || nx < 0 || nx >= N)
				continue;

			if (map[ny][nx] == '*')
				count++;

		}

		return count;
	}

}
