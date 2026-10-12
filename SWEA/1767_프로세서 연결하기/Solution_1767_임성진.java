import java.util.*;
import java.io.*;

public class Solution_1767_임성진 {
	static int N, maxCore, minLen;
	static int[][] map;
	static List<int[]> cores;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine().trim());

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine().trim());
			map = new int[N][N];
			cores = new ArrayList<>();

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine().trim());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());

					if (map[i][j] != 1) continue;
					if (i == 0 || i == N - 1 || j == 0 || j == N - 1) continue;   // 가장자리는 이미 연결
					cores.add(new int[] { i, j });
				}
			}

			maxCore = -1;
			minLen = Integer.MAX_VALUE;
			dfs(0, 0, 0);

			sb.append('#').append(tc).append(' ').append(minLen).append('\n');
		}
		System.out.print(sb);
	}

	static void dfs(int idx, int connected, int length) {
		if (connected + (cores.size() - idx) < maxCore) return;

		if (idx == cores.size()) {
			if (connected > maxCore) {                // 더 많이 연결했으면 길이도 같이 갱신
				maxCore = connected;
				minLen = length;
			} else if (connected == maxCore && length < minLen) {
				minLen = length;
			}
			return;
		}

		int r = cores.get(idx)[0];
		int c = cores.get(idx)[1];

		for (int d = 0; d < 4; d++) {
			int len = canPlace(r, c, d);
			if (len == -1) continue;

			place(r, c, d, 2);
			dfs(idx + 1, connected + 1, length + len);
			place(r, c, d, 0);                        // 되돌린다
		}
		dfs(idx + 1, connected, length);              // 이 코어는 포기
	}

	static int canPlace(int r, int c, int d) {
		int len = 0;
		int nr = r + dr[d];
		int nc = c + dc[d];

		while (nr >= 0 && nr < N && nc >= 0 && nc < N) {
			if (map[nr][nc] != 0) return -1;          // 코어나 다른 전선
			len++;
			nr += dr[d];
			nc += dc[d];
		}
		return len;                                   // 가장자리 밖
	}

	static void place(int r, int c, int d, int value) {
		int nr = r + dr[d];
		int nc = c + dc[d];

		while (nr >= 0 && nr < N && nc >= 0 && nc < N) {
			map[nr][nc] = value;
			nr += dr[d];
			nc += dc[d];
		}
	}
}