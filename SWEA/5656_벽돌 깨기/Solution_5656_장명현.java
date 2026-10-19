import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

	public static int N, W, H, answer;
	public static int[] dx = {-1, 0, 1, 0};
	public static int[] dy = {0, 1, 0, -1};

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());

			int count = 0;
			int[][] arr = new int[H][W];
			for (int i=0; i<H; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j=0; j<W; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
					if (arr[i][j] != 0) count++;
				}
			}

			answer = count;
			dfs(0, count, arr);

			sb.append('#').append(test_case).append(' ').append(answer).append('\n');
		}

		System.out.print(sb);
	}

	public static void dfs(int cnt, int count, int[][] arr) {
		if (answer == 0) return;

		// 구슬을 다 쐈거나, 벽돌을 전부 제거했으면 답 갱신
		if (cnt == N || count == 0) {
			answer = Math.min(answer, count);
			return;
		}

		for (int j=0; j<W; j++) {
			// 해당 열에서 가장 위에 있는 벽돌 찾기
			int x = 0;
			while (x < H && arr[x][j] == 0) x++;
			if (x == H) continue;

			// 이번 선택에서만 사용할 배열 복사
			int[][] next = new int[H][W];
			for (int i=0; i<H; i++) {
				for (int k=0; k<W; k++) {
					next[i][k] = arr[i][k];
				}
			}

			int remove = bfs(x, j, next);
			down(next);
			dfs(cnt+1, count-remove, next);
			if (answer == 0) return;
		}
	}

	public static int bfs(int sx, int sy, int[][] arr) {
		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] {sx, sy, arr[sx][sy]});
		arr[sx][sy] = 0;
		int count = 1;

		while (!q.isEmpty()) {
			int[] f = q.poll();
			int x = f[0], y = f[1], range = f[2];

			for (int d=0; d<4; d++) {
				for (int k=1; k<range; k++) {
					int nx = x + dx[d]*k;
					int ny = y + dy[d]*k;
					if (nx < 0 || nx >= H || ny < 0 || ny >= W) break;
					if (arr[nx][ny] == 0) continue;

					// 폭발 범위를 저장하고 바로 지워서 중복 제거 방지
					q.add(new int[] {nx, ny, arr[nx][ny]});
					arr[nx][ny] = 0;
					count++;
				}
			}
		}

		return count;
	}

	public static void down(int[][] arr) {
		for (int j=0; j<W; j++) {
			// 아래쪽부터 살아남은 벽돌을 차례대로 채우기
			int idx = H-1;
			for (int i=H-1; i>=0; i--) {
				if (arr[i][j] == 0) continue;
				arr[idx--][j] = arr[i][j];
			}

			// 벽돌을 채우고 남은 윗부분은 빈 공간
			while (idx >= 0) arr[idx--][j] = 0;
		}
	}
}
