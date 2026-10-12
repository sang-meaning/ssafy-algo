import java.util.Scanner;

class Solution {
	static int answer, maxi, N, K;
	static int dx[] = {1, 0, -1, 0};
	static int dy[] = {0, -1, 0, 1};
	static int arr[][];
	static boolean visited[][];
	
	public static void main(String args[]) throws Exception {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int test_case = 1; test_case <= T; test_case++) {
			N = sc.nextInt();
			K = sc.nextInt();
			
			maxi = 0;
			arr = new int[N][N];
			for (int i=0; i<N; i++) {
				for (int j=0; j<N; j++) {
					arr[i][j] = sc.nextInt();
					maxi = Math.max(maxi, arr[i][j]);
				}
			}
			
			answer = 0;
			for (int i=0; i<N; i++) {
				for (int j=0; j<N; j++) {
					if (arr[i][j] == maxi) {
						visited = new boolean[N][N];
						visited[i][j] = true;
						dfs(i, j, false, 1);
					}
				}
			}
			
			System.out.println("#" + test_case + " " +answer);
		}
	}
	
	public static void dfs(int x, int y, boolean isConstruct, int dist) {
		answer = Math.max(answer, dist);
		for (int t=0; t<4; t++) {
			int nx = x + dx[t];
			int ny = y + dy[t];
			if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
			if (visited[nx][ny] == true) continue;
			
			if (arr[nx][ny] < arr[x][y]) {
				visited[nx][ny] = true;
				dfs(nx, ny, isConstruct, dist+1);
				visited[nx][ny] = false;
			}
			else if (!isConstruct && arr[nx][ny]-K < arr[x][y]) {
				int original = arr[nx][ny];
				
				arr[nx][ny] = arr[x][y]-1;
				visited[nx][ny] = true;
				dfs(nx, ny, true, dist+1);
				visited[nx][ny] = false;
				arr[nx][ny] = original;
			}
		}
	}
}