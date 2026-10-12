package samsung1;

import java.io.*;
import java.util.*;

public class SWEA1868 {
	
	static int N;
	static int[][] intmap;
	static boolean[][] visited;
	
	static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			
			char[][] map = new char[N][N];
			intmap = new int[N][N];
			
			for(int i = 0; i < N; i++) {
				String s = br.readLine();
				for(int j = 0; j < N; j++) {

					map[i][j] = s.charAt(j);
					if(map[i][j] == '*') {
						intmap[i][j] = -1000;
					}
				}
			}
			
			for(int r = 0; r < N; r++) {
				for(int c = 0; c < N; c++) {
					if(map[r][c] == '*') continue;
					
					int cnt = 0;
					
					for(int d = 0; d < 8; d++) {
						int nr = r + dr[d];
						int nc = c + dc[d];
						
						if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
						
						if(map[nr][nc] == '*') cnt++;
						
						
					}
					intmap[r][c] = cnt;
				}
			}
			visited = new boolean[N][N];
			
			int ans = 0;
			
			for(int r = 0; r < N; r++) {
				for(int c = 0; c < N; c++) {
					if(intmap[r][c] == 0 && !visited[r][c]) {
						ans++;
						bfs(r, c);
					}
				}
			}
			
			for(int r = 0; r < N; r++) {
			    for(int c = 0; c < N; c++) {
			        if(intmap[r][c] >= 0 && !visited[r][c]) {
			            ans++;
			        }
			    }
			}
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		System.out.println(sb);
	}
	static void bfs(int r, int c) {
		ArrayDeque<int[]> q = new ArrayDeque<>();
		
		q.offer(new int[] {r, c});
		visited[r][c] = true;
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			
			int ar = cur[0];
			int ac = cur[1];
			
			for(int d = 0; d < 8; d++) {
				int nr = ar + dr[d];
				int nc = ac + dc[d];
				
				if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
				
				if(intmap[nr][nc] < 0) continue;
				if(visited[nr][nc]) continue;
				
				visited[nr][nc] = true;
				
				if(intmap[nr][nc]==0) {
					q.offer(new int[] {nr, nc});
				}
			}
			
		}
	}

}
