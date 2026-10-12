import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int[] dx = {0, 1, 0, -1};
		int[] dy = {1, 0, -1, 0};
		
		int T = Integer.parseInt(br.readLine());
		for (int test=1; test<=T; test++) {
			int N = Integer.parseInt(br.readLine());
			
			int[][] arr = new int[N][N];
			for (int i=0; i<N; i++) {
				String s = br.readLine();
				for (int j=0; j<N; j++) {
					arr[i][j] = s.charAt(j) - '0';
				}
			}
			
			int[][] dist = new int[N][N];
			for (int i=0; i<N; i++) {
				Arrays.fill(dist[i], Integer.MAX_VALUE);
			}
			
			PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> { 
				return Integer.compare(a[2], b[2]);
			});
			
			pq.add(new int[] {0, 0, 0});
			dist[0][0] = 0;
			
			int answer = -1;
			while (!pq.isEmpty()) {
				int[] f = pq.poll();
				int x = f[0];
				int y = f[1];
				int now = f[2];
				
				if (now > dist[x][y]) continue;
				
				for (int d=0; d<4; d++) {
					int nx = x + dx[d];
					int ny = y + dy[d];
					
					if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
					
					if (nx == N-1 && ny == N-1) {
						answer = now + arr[nx][ny];
						break;
					}
					
					if (dist[nx][ny] > now + arr[nx][ny]) {
						dist[nx][ny] = now + arr[nx][ny];
						pq.add(new int[] {nx, ny, dist[nx][ny]});
					}
				}
				
				if (answer != -1) break;
			}
			
			sb.append('#').append(test).append(' ').append(answer).append('\n');
		}
		
		System.out.print(sb);
	}
}