import java.io.*;
import java.util.*;

public class Solution_1949_정서우 {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;
  static StringBuilder sb = new StringBuilder();

  static int N, K;
  static int[][] map;
  static boolean[][] visited;

  static int maxN, longest;

  static int[] dx = {1, 0, -1, 0};
  static int[] dy = {0, 1, 0, -1};

  public static void main(String[] args) throws IOException {
    int T = Integer.parseInt(br.readLine());
    for (int tc = 1; tc <= T; tc++) {
      st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      K = Integer.parseInt(st.nextToken());

      map = new int[N][N];
      
      maxN = 0;
      longest = 0;

      for (int i = 0; i < N; i++) {
        st = new StringTokenizer(br.readLine());
        for (int j = 0; j < N; j++) {
          map[i][j] = Integer.parseInt(st.nextToken());
          maxN = Math.max(maxN, map[i][j]);
        }
      }

      for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
          if (map[i][j] == maxN) {
            visited = new boolean[N][N];
            visited[i][j] = true;
            dfs(i, j, 1, false);
          }
        }
      }


      sb.append("#").append(tc).append(" ").append(longest).append("\n");
    }

    System.out.println(sb);
  }

  static void dfs(int x, int y, int dist, boolean isCon) {
    longest = Math.max(longest, dist);

    for (int dir = 0; dir < 4; dir++) {
      int nx = x + dx[dir];
      int ny = y + dy[dir];

      if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
      if (visited[nx][ny]) continue;

      if (map[nx][ny] < map[x][y]) {
        visited[nx][ny] = true;
        dfs(nx, ny, dist + 1, isCon);
        visited[nx][ny] = false;
      } else if (!isCon && map[nx][ny] - K < map[x][y]) {
        int original = map[nx][ny];
        
        map[nx][ny] = map[x][y] - 1;
        visited[nx][ny] = true;
        dfs(nx, ny, dist + 1, true);
        visited[nx][ny] = false;
        map[nx][ny] = original;
      }
    }
  }
}
