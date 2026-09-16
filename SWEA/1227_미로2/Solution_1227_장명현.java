import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class Solution {
	public static int[] dx = {1, 0, -1, 0};
	public static int[] dy = {0, 1, 0, -1};
	public static void main(String args[]) throws Exception {
		// Fast Input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// Fast Output
		StringBuilder sb = new StringBuilder();
		
		// Execute TC
		// int T = Integer.parseInt(br.readLine());
		int T = 10;
		for (int test_case = 1; test_case <= T; test_case++) {
			// 변수 저장 및 선언
			int tc = Integer.parseInt(br.readLine());
			
			int N = 100, start = 0, end = 0;
			int[][] arr = new int[N][N];			
			for (int i=0; i<N; i++) {
				String newline = br.readLine();
				for (int j=0; j<N; j++) {
					arr[i][j] = newline.charAt(j) - '0';
					if (arr[i][j] == 2) start = i*100+j;
					if (arr[i][j] == 3) end = i*100+j;
				}
			}
			
			// bfs
			boolean answer = false;
			Queue<Integer> q = new ArrayDeque<>();
			boolean[][] visited = new boolean[N][N];
			
			q.add(start);
			visited[start/100][start%100] = true;
			
			while (!q.isEmpty()) {
				int f = q.poll();
				int x = f/100, y = f%100;
				
				for (int d=0; d<4; d++) {
					int nx = x + dx[d];
					int ny = y + dy[d];
					if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
					if (arr[nx][ny] == 1 || visited[nx][ny]) continue;
					
					if (nx*100 + ny == end) {
 						answer = true;
						break;
					}
					
					q.add(nx*100 + ny);
					visited[nx][ny] = true;
				}
				
				if (answer) break;
			}
			
			// Add Output
			sb.append('#').append(test_case).append(' ');
			
			if (answer) sb.append(1).append('\n');
			else sb.append(0).append('\n');
		}
		
		// Print Output
		System.out.println(sb);
	}
}
