import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {
  static int T, N, K, ans;
  static int[][] map;
  static boolean[][] isVisited;
  static int[] dx = {1,-1,0,0};
  static int[] dy = {0,0,1,-1};

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    StringTokenizer st = new StringTokenizer(br.readLine());

    T = Integer.parseInt(st.nextToken());

    for (int tc = 1; tc <= T; tc++) {
      st = new StringTokenizer(br.readLine());

      N = Integer.parseInt(st.nextToken());
      K = Integer.parseInt(st.nextToken());

      map = new int[N][N];
      isVisited = new boolean[N][N];

      int max = 0;

      for (int i = 0; i < N; i++) {
        st = new StringTokenizer(br.readLine());

        for (int j = 0; j < N; j++) {
          map[i][j] = Integer.parseInt(st.nextToken());

          if (max < map[i][j]) {
            max = map[i][j];
          }
        }
      }

      ans = 0;

      for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
          if (map[i][j] == max) { // 가장 높은 봉우리에서 탐색
            isVisited[i][j] = true;
            dfs(i, j, 1, false);
            isVisited[i][j] = false;
          }
        }
      }

      sb.append("#").append(tc).append(" ")
        .append(ans).append('\n');
    }

    System.out.print(sb);
  }

  static void dfs(int r, int c, int len, boolean cutUsed) {
    if (ans < len) {
      ans = len;
    }

    for (int d = 0; d < 4; d++) {
      int nr = r + dx[d];
      int nc = c + dy[d];

      if (nr < 0 || nr >= N || nc < 0 || nc >= N) {
        continue;
      }

      if (isVisited[nr][nc]) {
        continue;
      }

      if (map[nr][nc] < map[r][c]) { // 그냥 내려갈 수 있는 경우
        isVisited[nr][nc] = true;

        dfs(nr, nc, len + 1, cutUsed);

        isVisited[nr][nc] = false;
      }
      else if (!cutUsed && map[nr][nc] - K < map[r][c]) { // 공사 후 이동 가능
        int temp = map[nr][nc];

        map[nr][nc] = map[r][c] - 1;
        isVisited[nr][nc] = true;

        dfs(nr, nc, len + 1, true);

        isVisited[nr][nc] = false;
        map[nr][nc] = temp; // 공사 상태 복구
      }
    }
  }
}