import java.util.*;

class Solution {
	
	public static int T, N, M, R, L, C;
	public static int[] dx = {-1, 0, 1, 0};
	public static int[] dy = {0, 1, 0, -1};
	public static int[] op = {2, 3, 0, 1};
	public static int[][] dir = {
			{}, {0, 1, 2, 3}, {0, 2}, {1, 3},
			{0, 1}, {1, 2}, {2, 3}, {0, 3}
	};
	public static int[][] visited;
	public static int[][][] arr;
	public static Queue<int[]> q;
	
	// 문제에는 생략되어 있는 듯 하지만, 도둑은 단일 파이프에 머물러 있는 행위도 할 수 있는 것으로 보임.
	// 이제 이 문제는 도둑이 주어진 시간 동안 얼마나 많은 땅을 밟을 수 있는지로 치환됨
	public static void main(String args[]) throws Exception {
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();

		for (int test_case = 1; test_case <= T; test_case++) {
			N = sc.nextInt();
			M = sc.nextInt();
			R = sc.nextInt();
			C = sc.nextInt();
			L = sc.nextInt();
			arr = new int[N][M][4];

			// 각 지점에서 상하좌우로 이동할 수 있는지 배열에 저장 (d = dir)
			for (int i=0; i<N; i++) {
				for (int j=0; j<M; j++) {
					int x = sc.nextInt();
					for (int d: dir[x]) {
						arr[i][j][d] = 1;
					}
				}
			}
			
			q = new LinkedList<>();
			visited = new int[N][M];
			
			q.add(new int[] {R, C, 1});
			visited[R][C] = 1;

			// bfs를 돌면서
			while (!q.isEmpty()) {
				int[] f = q.poll();
				int x = f[0], y = f[1], l = f[2];
				
				for (int d=0; d<4; d++) {
					int nx = x + dx[d];
					int ny = y + dy[d];
					if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
					if (visited[nx][ny] == 1) continue;
					
					// 이번 지점과 다음 지점에 모두 파이프가 뚫려 있어야 진행. l<L은 거리 조건
					if (arr[x][y][d] == 1 && arr[nx][ny][op[d]] == 1 && l<L) {
						q.add(new int[] {nx, ny, l+1});
						visited[nx][ny] = 1;
					}
				}
			}
			
			// 도둑이 밟은 모든 칸을 count
			int answer = 0;
			for (int i=0; i<N; i++) {
				for (int j=0; j<M; j++) {
					if (visited[i][j] == 1) {
						answer++;
					}
				}
			}
			
			System.out.println("#" + test_case + " " + answer);
		}
	}
}