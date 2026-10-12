import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};
		int[] dy = {1, 1, 0, -1, -1, -1, 0, 1};
		
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(br.readLine());
			int[][] map = new int[N][N];
			
			for (int i=0; i<N; i++) {
				String s = br.readLine();
				for (int j=0; j<N; j++) {
					char c = s.charAt(j);
					map[i][j] = c == '*' ? -1 : 0;
				}
			}
			
			
			for (int i=0; i<N; i++) {
				for (int j=0; j<N; j++) {
					if (map[i][j] == -1) continue;
					
					for (int d=0; d<8; d++) {
						int nx = i + dx[d];
						int ny = j + dy[d];
						if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
						if (map[nx][ny] == -1) map[i][j]++;
					}
				}
			}
			

			Queue<Integer> q = new ArrayDeque<>();
			
			int answer = 0;
			boolean[][] visited = new boolean[N][N];
			for (int i=0; i<N; i++) {
				for (int j=0; j<N; j++) {
					if (visited[i][j]) continue;
					if (map[i][j] != 0) continue;
					answer++;
					
					q.add(i*1000+j);
					visited[i][j] = true;
					
					while (!q.isEmpty()) {
						int f = q.poll();
						int x = f/1000;
						int y = f%1000;
						
						for (int d=0; d<8; d++) {
							int nx = x + dx[d];
							int ny = y + dy[d];
							if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
							if (visited[nx][ny]) continue;
							
							visited[nx][ny] = true;
							if (map[nx][ny] == 0) {
								q.add(nx*1000+ny);
							}
						}
					}
				}
			}
			
			for (int i=0; i<N; i++) {
				for (int j=0; j<N; j++) {
					if (visited[i][j]) continue;
					if (map[i][j] == -1) continue;
					
					answer++;
				}
			}
			
			sb.append("#").append(test_case).append(' ');
			sb.append(answer).append("\n");
		}
		
		System.out.println(sb);
	}
}