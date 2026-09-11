import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;

class Solution {
  static int T, N, maxCore, minWire;
  static int[][] map;
  static ArrayList<int[]> cores;
  static int[] dx = {-1,1,0,0};
  static int[] dy = {0,0,-1,1};

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    StringTokenizer st = new StringTokenizer(br.readLine());

    T = Integer.parseInt(st.nextToken());

    for (int tc = 1; tc <= T; tc++) {
      st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());

      map = new int[N][N];
      cores = new ArrayList<>();

      for (int i = 0; i < N; i++) {
        st = new StringTokenizer(br.readLine());

        for (int j = 0; j < N; j++) {
          map[i][j] = Integer.parseInt(st.nextToken());

          if (map[i][j] == 1) {
            // 경계 프로세서는 이미 전원에 연결되어 있으므로 탐색 제외
            if (i != 0 && i != N-1 && j != 0 && j != N-1) {
              cores.add(new int[]{i,j});
            }
          }
        }
      }

      maxCore = -1;
      minWire = Integer.MAX_VALUE;

      dfs(0, 0, 0);

      sb.append("#").append(tc).append(" ")
        .append(minWire).append('\n');
    }

    System.out.print(sb);
  }

  static void dfs(int idx, int connected, int wire) {
    if (idx >= cores.size()) { // 모든 프로세서 탐색 완료
      if (connected > maxCore) {
        maxCore = connected;
        minWire = wire;
      }
      else if (connected == maxCore && wire < minWire) {
        minWire = wire;
      }

      return;
    }

    int r = cores.get(idx)[0];
    int c = cores.get(idx)[1];

    for (int d = 0; d < 4; d++) {
      if (!canConnect(r, c, d)) {
        continue;
      }

      int len = setWire(r, c, d, 2); // 전선 연결

      dfs(idx + 1, connected + 1, wire + len);

      setWire(r, c, d, 0); // 전선상태 복구
    }

    // 현재 프로세서를 연결하지 않는 경우
    dfs(idx + 1, connected, wire);
  }

  static boolean canConnect(int r, int c, int d) {
    int nr = r + dx[d];
    int nc = c + dy[d];

    while (nr >= 0 && nr < N && nc >= 0 && nc < N) {
      if (map[nr][nc] != 0) { // 프로세서 또는 전선이 경로에 있는 경우
        return false;
      }

      nr += dx[d];
      nc += dy[d];
    }

    return true;
  }

  static int setWire(int r, int c, int d, int value) {
    int nr = r + dx[d];
    int nc = c + dy[d];
    int len = 0;

    while (nr >= 0 && nr < N && nc >= 0 && nc < N) {
      map[nr][nc] = value;
      len++;

      nr += dx[d];
      nc += dy[d];
    }

    return len;
  }
}