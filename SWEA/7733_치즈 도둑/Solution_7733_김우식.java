import java.io.*;
import java.util.*;

public class Solution {

	static int N, max, ans;
	static int[][] map;
	static boolean[][] visited;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			
			map = new int[N][N];
			
			visited = new boolean[N][N];
			
			max = 0; ans = 0;
			
			for(int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					max = Math.max(map[i][j], max);
				}
			}
			
			
			for (int day = 0; day <= max; day++) {

			    visited = new boolean[N][N];
			    int cnt = 0;

			    for (int r = 0; r < N; r++) {
			        for (int c = 0; c < N; c++) {

			            if (map[r][c] <= day) continue;
			            if (visited[r][c]) continue;

			            bfs(r, c, day);
			            cnt++;
			        }
			    }

			    ans = Math.max(ans, cnt);
			}
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		System.out.println(sb);
	}
	
	static void bfs(int r, int c, int day) {
		
		ArrayDeque<int[]> q = new ArrayDeque<>();
		
		q.offer(new int[] {r, c});
		
		visited[r][c] = true;
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			
			int cr = cur[0];
			int cc = cur[1];
			
			for(int d = 0; d < 4; d++) {
				int nr = cr + dr[d];
				int nc = cc + dc[d];
				
				if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
				
				if(visited[nr][nc]) continue;
				
				if(map[nr][nc] <= day) continue;
				
				visited[nr][nc] = true;
				q.offer(new int[] {nr, nc});
			}
		}
	}
}
