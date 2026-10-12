import java.util.*;
import java.io.*;

public class Solution_4130_강상민 {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;
  static StringBuilder sb = new StringBuilder();
  static int T;
  static int K; // 회전시킬 횟수
  static int[] which; // i번째 시행에서 어떤 톱니 회전시킬지
  static int[] dir; // i번째 시행에서 회전 방향 (1 : 시계, -1 : 반시계)
  static int[][] board; // 행 : 톱니 번호 (1~4), 열 : 앞에서 i번째(index 0부터), 값 : 자성 (N극 0)
  static int[] rotatable; // -1 : 초깃값, 0: 회전 불가, 1: 회전 가능
  static int[] rotateDir; // 초깃값 -2, 톱니의 회전 방향,


  public static void main(String[] args) throws IOException {
    T = Integer.parseInt(br.readLine());

    for (int t = 1; t <= T; t++) {
      K = Integer.parseInt(br.readLine());

      board = new int[5][8]; // 톱니만 1~4 번호로 따라가고 나머지는 index 0부터 시작
      which = new int[K];
      dir = new int[K];
      rotatable = new int[5];
      rotateDir = new int[5];
      for (int i = 1; i <= 4; i++) {
        rotatable[i] = -1; // 초깃값
        rotateDir[i] = -2; // 초깃값
      }

      for (int i = 1; i <= 4; i++) {
        st = new StringTokenizer(br.readLine());
        for (int j = 0; j < 8; j++) {
          board[i][j] = Integer.parseInt(st.nextToken());
        }
      }

      for (int i = 0; i < K; i++) {
        st = new StringTokenizer(br.readLine());
        which[i] = Integer.parseInt(st.nextToken());
        dir[i] = Integer.parseInt(st.nextToken());
      }

      // 세팅 완료

      int result = 0; // 점수

      for (int i = 0; i < K; i++) {
        // 회전 K번 시행

        int start = which[i]; // 시작 톱니 번호
        int d = dir[i]; // 시작 톱니 방향

        rotatable[start] = 1; 
        rotateDir[start] = d;

        for (int depth = 1; depth < 4; depth++) {
          // 왼쪽으로 depth 만큼 보기
          int left = start - depth;

          if (left < 1) continue;

          if (rotatable[left] != -1) continue;

          if (board[left][2] != board[left + 1][6]) {
            rotatable[left] = 1; // 회전가능
            rotateDir[left] = d * (-1);
            if (depth % 2 == 0)
              rotateDir[left] *= -1; // 짝수번째 떨어진 것에 다시 -1 곱해주기
          } else {
            // 회전 못함
            rotatable[left] = 0;
            break; // for문 더이상 볼 필요 없다
          }
        }

        for (int depth = 1; depth < 4; depth++) {
          // 이번엔 오른쪽 보기
          int right = start + depth;

          if (right > 4) continue;

          if (rotatable[right] != -1) continue;

          if (board[right][6] != board[right - 1][2]) {
            rotatable[right] = 1; 
            rotateDir[right] = d * (-1);
            if (depth % 2 == 0)
              rotateDir[right] *= -1;
          } else {
            rotatable[right] = 0;
            break; 
          }
        }

        // 각 톱니 회전 가능 여부와 방향 저장 완료
        for (int ii = 1; ii <= 4; ii++) {
          int ro = rotatable[ii];
          int di = rotateDir[ii];

          if (ro == 1) {
            rotate(ii, di);
          }
        }

        for (int ii = 1; ii <= 4; ii++) {
          rotatable[ii] = -1;
          rotateDir[ii] = -2;
        }

      }

      int score = 1;
      for (int i = 1; i <= 4; i++) {
        if (board[i][0] == 1) {
          result += score;
        }
        score *= 2;

      }

      sb.append("#" + t + " " + result).append("\n");
    }

    // tc 종료
    System.out.println(sb);

  }

  static void rotate(int node, int clock) {
    // clock 1 이면 시계
    // index 0이 1로
    // index 7 temp에, 6부터 0까지 밀어넣고, 0에 temp

    if (clock == 1) {
      int temp = board[node][7];
      for (int i = 6; i >= 0; i--) {
        board[node][i + 1] = board[node][i];
      }
      board[node][0] = temp;
    } else {

      // 반시계
      // index 0이 7로,
      // index 0 temp에, 1부터 7까지 당기고, 7에 temp

      int temp = board[node][0];
      for (int i = 1; i <= 7; i++) {
        board[node][i - 1] = board[node][i];
      }
      board[node][7] = temp;

    }

  }

}
