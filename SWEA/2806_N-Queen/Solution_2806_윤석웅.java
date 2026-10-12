import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {
  static int T, N, ans;
  static boolean[] col;
  static boolean[] diag1;
  static boolean[] diag2;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    StringTokenizer st = new StringTokenizer(br.readLine());

    T = Integer.parseInt(st.nextToken());

    for (int tc = 1; tc <= T; tc++) {
      st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());

      col = new boolean[N];
      diag1 = new boolean[N * 2 - 1];
      diag2 = new boolean[N * 2 - 1];

      ans = 0;
      dfs(0);

      sb.append("#").append(tc).append(" ")
        .append(ans).append('\n');
    }

    System.out.print(sb);
  }

  static void dfs(int row) {
    if (row >= N) { // N개의 퀸 배치 완료
      ans++;
      return;
    }

    for (int c = 0; c < N; c++) { // 대각선검사
      int d1 = row - c + N - 1;
      int d2 = row + c;

      if (col[c] || diag1[d1] || diag2[d2]) {
        continue; // 공격 가능한 위치면 pass
      }

      col[c] = true;
      diag1[d1] = true;
      diag2[d2] = true;

      dfs(row + 1); // 다음 행 퀸 배치

      col[c] = false;
      diag1[d1] = false;
      diag2[d2] = false; // 백트래킹용 상태 복구
    }
  }
}