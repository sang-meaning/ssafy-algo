import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution_1226_김우식 {
  static int[] dr = { 1, -1, 0, 0 };
  static int[] dc = { 0, 0, -1, 1 };

  static boolean[][] visited;
  static int[][] map = new int[16][16];

  static int ans;

  public static void main(String[] args) throws Exception {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    for (int tc = 1; tc <= 10; tc++) {

      int T = Integer.parseInt(br.readLine());

      for (int i = 0; i < 16; i++) {
        String s = br.readLine();

        for (int j = 0; j < 16; j++) {
          map[i][j] = s.charAt(j) - '0';
        }
      }

      visited = new boolean[16][16];
      ans = 0;

      dfs(1, 1);

      sb.append("#")
          .append(T)
          .append(" ")
          .append(ans)
          .append("\n");
    }

    System.out.print(sb);
  }

  public static void dfs(int r, int c) {

    visited[r][c] = true;

    for (int d = 0; d < 4; d++) {

      int nr = r + dr[d];
      int nc = c + dc[d];

      if (nr < 0 || nr >= 16 || nc < 0 || nc >= 16) {
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
