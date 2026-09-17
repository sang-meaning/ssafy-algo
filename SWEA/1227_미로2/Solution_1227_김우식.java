package swea;

import java.io.*;
import java.util.*;


public class SWEA1227 {


  static int[] dr = { 1, -1, 0, 0 };
  static int[] dc = { 0, 0, -1, 1 };

  static boolean[][] visited;
  static int[][] map = new int[101][101];

  static int ans;


  public static void main(String[] args) throws Exception {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    for (int tc = 1; tc <= 10; tc++) {

      int T = Integer.parseInt(br.readLine());

      for (int i = 0; i < 100; i++) {
        String s = br.readLine();

        for (int j = 0; j < 100; j++) {
          map[i][j] = s.charAt(j) - '0';
        }
      }

      visited = new boolean[101][101];
      ans = 0;

      
//      dfs(1, 1);
      bfs(1, 1);
      

      sb.append("#")
          .append(T)
          .append(" ")
          .append(ans)
          .append("\n");
    }

    System.out.print(sb);
  }
  
  public static void bfs(int r, int c) {

	    Queue<int[]> q = new ArrayDeque<>();

	    q.offer(new int[]{r, c});
	    visited[r][c] = true;

	    while (!q.isEmpty()) {

	        int[] cur = q.poll();

	        int cr = cur[0];
	        int cc = cur[1];

	        for (int d = 0; d < 4; d++) {

	            int nr = cr + dr[d];
	            int nc = cc + dc[d];

	            if (nr < 0 || nr >= 100 || nc < 0 || nc >= 100) {
	                continue;
	            }

	            if (map[nr][nc] == 1 || visited[nr][nc]) {
	                continue;
	            }

	            if (map[nr][nc] == 3) {
	                ans = 1;
	                return;
	            }

	            visited[nr][nc] = true;
	            q.offer(new int[]{nr, nc});
	        }
	    }
	}

  public static void dfs(int r, int c) {

    visited[r][c] = true;

    for (int d = 0; d < 4; d++) {

      int nr = r + dr[d];
      int nc = c + dc[d];

      if (nr < 0 || nr >= 100 || nc < 0 || nc >= 100) {
        continue;
      }

      if (map[nr][nc] == 1 || visited[nr][nc]) {
        continue;
      }

      if (map[nr][nc] == 3) {
        ans = 1;
        return;
      }

      dfs(nr, nc);

      if (ans == 1) {
        return;
      }
    }
  }
}
