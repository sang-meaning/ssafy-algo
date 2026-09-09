import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {
  static int T, N, M, ans;
  static boolean[][] map;
  static boolean[] isSelected;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    StringTokenizer st = new StringTokenizer(br.readLine());

    T = Integer.parseInt(st.nextToken());

    for (int tc = 1; tc <= T; tc++) {
      st = new StringTokenizer(br.readLine());

      N = Integer.parseInt(st.nextToken());
      M = Integer.parseInt(st.nextToken());

      map = new boolean[N + 1][N + 1];
      isSelected = new boolean[N + 1];

      for (int i = 0; i < M; i++) {
        st = new StringTokenizer(br.readLine());

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        map[a][b] = true; // 같이 들어갈 수 없는 재료 체크
        map[b][a] = true;
      }

      ans = 0;
      dfs(1);

      sb.append("#").append(tc).append(" ")
        .append(ans).append('\n');
    }

    System.out.print(sb);
  }

  static void dfs(int idx) {
    if (idx > N) { // 모든 재료가 선택이 됐으면
      ans++;
      return;
    }

    // 현재 재료를 선택하기
    isSelected[idx] = false;
    dfs(idx + 1);

    // 현재 재료 가능 검사
    boolean check = true;

    for (int i = 1; i < idx; i++) {
      if (isSelected[i] && map[idx][i]) {
        check = false;
        break;
      }
    }

    if (check) {
      isSelected[idx] = true; // 현재 재료 선택
      dfs(idx + 1);
      isSelected[idx] = false; // 백트래킹
    }
  }
}