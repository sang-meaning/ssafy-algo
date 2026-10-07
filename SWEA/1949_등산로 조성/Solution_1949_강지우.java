import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution
{
	static int N, K, answer;
	static int[][] map;
	static boolean[][] visited;	
	
	// 상 하 좌 우
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			int maxHeight = 0;
			map = new int[N][N];
			visited = new boolean[N][N];
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					
					maxHeight = Math.max(map[i][j], maxHeight);
					
				}
			}
			
			answer = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == maxHeight) {
						
						visited[i][j] = true;
						// 최고 봉우리도 길이에 포함
						dfs(i, j, 1, false);
						visited[i][j] = false;
					}
					
				}
			}
			
			sb.append("#").append(test_case).append(" ")
			  .append(answer).append("\n");

		}
		
		System.out.print(sb);
	}
	
	static void dfs(int x, int y, int length, boolean usedCut) {
		answer = Math.max(answer, length);
		
		for (int d = 0; d < 4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			
			if (nx<0 || ny<0 || nx>=N || ny>=N) continue;
			if (visited[nx][ny]) continue;
			
			// 1. 그냥 이동할 수 있는 경우
			if (map[nx][ny] < map[x][y]) {
				
				visited[nx][ny] = true;
				dfs(nx, ny, length + 1, usedCut);
				visited[nx][ny] = false;
			}
			
			// 2. 그냥 이동할 수 없지만 아직 공사를 안 한 경우
			else if (!usedCut) {
				if (map[nx][ny] - K < map[x][y]) {
					
					int original = map[nx][ny];
					
					// 현재 지형보다 정확히 1 낮게 만듦
					map[nx][ny] = map[x][y] - 1;
					
					visited[nx][ny] = true;
					dfs(nx, ny, length + 1, true);
					visited[nx][ny] = false;
					map[nx][ny] = original;
				}
			}			
		}
	}
}