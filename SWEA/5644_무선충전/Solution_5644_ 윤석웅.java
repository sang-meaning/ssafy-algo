import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {
  static int T, M, A;
  static int[] moveA, moveB;
  static int[][] bc;
  static int[] dx = {0, 0, 1, 0, -1};
  static int[] dy = {0, -1, 0, 1, 0};

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    StringTokenizer st = new StringTokenizer(br.readLine());

    T = Integer.parseInt(st.nextToken());

    for (int tc = 1; tc <= T; tc++) {
      st = new StringTokenizer(br.readLine());

      M = Integer.parseInt(st.nextToken());
      A = Integer.parseInt(st.nextToken());

      moveA = new int[M];
      moveB = new int[M];

      st = new StringTokenizer(br.readLine());
      for (int i = 0; i < M; i++) {
        moveA[i] = Integer.parseInt(st.nextToken());
      }

      st = new StringTokenizer(br.readLine());
      for (int i = 0; i < M; i++) {
        moveB[i] = Integer.parseInt(st.nextToken());
      }

      // x, y, coverage, power
      bc = new int[A][4];

      for (int i = 0; i < A; i++) {
        st = new StringTokenizer(br.readLine());

        bc[i][0] = Integer.parseInt(st.nextToken());
        bc[i][1] = Integer.parseInt(st.nextToken());
        bc[i][2] = Integer.parseInt(st.nextToken());
        bc[i][3] = Integer.parseInt(st.nextToken());
      }

      int ax = 1, ay = 1;
      int bx = 10, by = 10;

      int ans = getMaxCharge(ax, ay, bx, by); // 시작 위치

      for (int i = 0; i < M; i++) {
        ax += dx[moveA[i]];
        ay += dy[moveA[i]];

        bx += dx[moveB[i]];
        by += dy[moveB[i]];

        ans += getMaxCharge(ax, ay, bx, by);
      }

      sb.append("#").append(tc).append(" ")
        .append(ans).append('\n');
    }

    System.out.print(sb);
  }

  static int getMaxCharge(int ax, int ay, int bx, int by) {
    int max = 0;

    // A,B 완탐
    for (int i = 0; i < A; i++) {
      boolean chargeA = canCharge(ax, ay, i);

      for (int j = 0; j < A; j++) {
        boolean chargeB = canCharge(bx, by, j);

        int temp = 0;

        if (i == j) { // 같은 무선 선택
          if (chargeA || chargeB) {
            temp = bc[i][3];
          }
        } else {
          if (chargeA) {
            temp += bc[i][3];
          }

          if (chargeB) {
            temp += bc[j][3];
          }
        }

        if (max < temp) {
          max = temp;
        }
      }
    }

    return max;
  }

  static boolean canCharge(int x, int y, int idx) {
    int distance =
        Math.abs(x - bc[idx][0])
      + Math.abs(y - bc[idx][1]);

    return distance <= bc[idx][2];
  }
}