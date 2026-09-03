
import java.io.*;
import java.util.*;

public class Solution  {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = 10;
		int n = 16;
		for(int testcase = 1; testcase <= T; testcase++) {
			br.readLine();
			int[][] arr = new int[n][n];
			int[][] visited = new int[n][n];
			int answer = 0;
			int[] target;
			// 좌표
			for(int i = 0; i<n; i++) {
				String li = br.readLine();
				for(int j = 0; j<n; j++) {
					arr[i][j] = li.charAt(j) - '0';
					if(arr[i][j] == 3) target = new int[]{i, j};
				}
			}
			
			// queue
			Queue<int[]> q = new ArrayDeque<>();
			q.offer(new int[]{1, 1});
			visited[1][1] = 1;
			// 상하좌우 위한 
			int[] dr = {1, -1, 0, 0};
			int[] dc = {0, 0, 1, -1};
			while(!q.isEmpty()) {
				int[] cur = q.poll();
				
				for(int i = 0; i<4; i++) {
					int nr = cur[0] + dr[i];
					int nc = cur[1] + dc[i];
					if(nr < 0 || nc < 0 || nr >= n || nc >= n) continue;
					if(visited[nr][nc] == 1 || arr[nr][nc] == 1) continue;
					if(arr[nr][nc] == 3) {
						answer = 1;
						break;
					}
					q.offer(new int[] {nr, nc});
					visited[nr][nc] = 1;
				}
				if(answer == 1) break;
			}
			System.out.println("#"+ testcase + " " + answer);
		}
	}	
}