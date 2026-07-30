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
			
			while (!q.isEmpty()) {
				int[] f = q.poll();
				int x = f[0], y = f[1], l = f[2];
				
				for (int d=0; d<4; d++) {
					int nx = x + dx[d];
					int ny = y + dy[d];
					if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
					if (arr[x][y][d] == 1 && arr[nx][ny][op[d]] == 1 && visited[nx][ny] == 0 && l<L) {
						q.add(new int[] {nx, ny, l+1});
						visited[nx][ny] = 1;
					}
				}
			}
			
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