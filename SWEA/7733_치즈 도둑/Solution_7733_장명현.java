import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int[] dx = {1, 0, -1, 0};
		int[] dy = {0, 1, 0, -1};
		
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(br.readLine());
			
			int[][] arr = new int[N][N];
			for (int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j=0; j<N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			ArrayDeque<Integer> q = new ArrayDeque<>();
			
			int answer = 1;
			for (int t=1; t<=100; t++) {
				boolean[][] visited = new boolean[N][N];
				int cnt = 0;
				
				for (int i=0; i<N; i++) {
					for (int j=0; j<N; j++) {
						if (arr[i][j] == t) {
							arr[i][j] = 0;
						}
					}
				}
				
				
				for (int i=0; i<N; i++) {
					for (int j=0; j<N; j++) {
						if (visited[i][j] || arr[i][j] == 0) continue;
						
						q.add(i*100+j);
						visited[i][j] = true;
						
						while (!q.isEmpty()) {
							int f = q.poll();
							int x = f/100;
							int y = f%100;
							
							for (int d=0; d<4; d++) {
								int nx = x + dx[d];
								int ny = y + dy[d];
								if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
								if (visited[nx][ny] || arr[nx][ny] == 0) continue;
								
								q.add(nx*100+ny);
								visited[nx][ny] = true;
							}
						}
						
						cnt++;
					}
				}
				
				answer = Math.max(answer, cnt);
			}
			
			sb.append("#").append(test_case).append(' ');
			sb.append(answer).append("\n");
		}
		
		System.out.println(sb);
	}
}