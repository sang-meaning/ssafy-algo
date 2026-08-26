import java.util.*;
import java.io.*;

/*
한 칸에 한 사람만 올 수 있을 것이라고 오해할 수 있는데, 입구까지 걸리는 시간이 멘하탄 거리이다
충돌은 생각 안해도 된다
각 사람을 두 출구중 하나로 매핑 : 2^10 가지 완전탐색
1초마다 도착 여부 판단, 계단에 올라간 사람과 시간 판단
: 각 계단에 대한 큐 만들어 관리
*/

public class Solution_2383_강상민 {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;
  static StringBuilder sb = new StringBuilder();
  static int T;
  static int N;
  static int P; // 사람수
  static int mask; // 0은 0번째 계단, 1은 1번째 계단
  static int[][] board; // 초기 위치용 board
  static int[] p_x; // 사람의 초기 위치 좌표
  static int[] p_y;
  static int[] dist; // 초기 위치 dist (맨하탄)
  static int[] stair; // i번째 사람 어떤 계단에 할당
  static int[] x; // x[0] : 0번째 계단의 x 좌표
  static int[] y;
  static int result;

  public static void main(String[] args) throws IOException {
    T = Integer.parseInt(br.readLine());

    for (int t = 1; t <= T; t++) {
      N = Integer.parseInt(br.readLine());

      P = 0; // 사람 수
      mask = 0;
      result = Integer.MAX_VALUE;

      board = new int[N][N];

      int iter = 0;

      x = new int[2];
      y = new int[2];

      // board와 계단 좌표 입력받기
      for (int i = 0; i < N; i++) {
        st = new StringTokenizer(br.readLine());
        for (int j = 0; j < N; j++) {
          board[i][j] = Integer.parseInt(st.nextToken());

          if (board[i][j] >= 2 && board[i][j] <= 10) {
            x[iter] = i;
            y[iter] = j;
            iter++;
          }

          if (board[i][j] == 1)
            P++;
        }
      }

      p_x = new int[P];
      p_y = new int[P];

      // 사람의 초기 좌표 
      iter = 0;
      for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
          if (board[i][j] == 1) {
            p_x[iter] = i;
            p_y[iter] = j;
            iter++;
          }
        }
      }

      dist = new int[P];
      iter = 0;

      stair = new int[P]; // 1이면 1번째 계단

      for (int i = 0; i < (1 << P); i++) { // 각 사람을 계단에 할당하는 최대 2^10 가짓수 : mask로 판단
        // 각 시행에서 초기화 필요한 것은 stair 과 dist
        for (int j = 0; j < P; j++) {
          // j번째 비트 확인

          if (((1 << j) & mask) == 0) { 
            stair[j] = 0; // j번째 비트(사람)이 0이면 0번 계단에 할등
          } else {
            stair[j] = 1;
          }

          // System.out.println("stair: "+stair[j]);
        }

        // 사람과 계단의 멘하탄 거리 구하기
        for (int j = 0; j < P; j++) {
          int xx = Math.abs(x[stair[j]] - p_x[j]); 
          int yy = Math.abs(y[stair[j]] - p_y[j]);

          dist[j] = xx + yy;
        }


        int fin = 0; // fin == P 면 종료
        int time = 0; 
        Deque<Integer> q1 = new ArrayDeque<>();
        Deque<Integer> q2 = new ArrayDeque<>();

        // 먼저 두 계단의 큐 갱신, 뺄거 빼기
        // 빼면 fin++, fin == P 이면 모두 탈출

        while (true) {
          if (fin == P) { // 종료조건
            result = Math.min(result, time);
            break;
          }

          int qqq = q1.size();
          for (int q = 0; q < qqq; q++) { // 계단 1 이동
            int cur = q1.poll();
            if (cur == board[x[0]][y[0]]) {
              fin++;
            } else {
              q1.add(cur + 1);
            }
          }

          qqq = q2.size();
          for (int q = 0; q < qqq; q++) { // 계단 2 이동
            int cur = q2.poll();
            if (cur == board[x[1]][y[1]]) {
              fin++;
            } else {
              q2.add(cur + 1);
            }
          }

          // 현재 상태에서 계단과 거리가 0인지 확인, 0이면, 그 계단의 큐의 size가 3보다 작을때 큐에 넣기
          // 큐에 넣으면 dist 1빼고, 안넣으면 그대로

          for (int k = 0; k < P; k++) {
            if (dist[k] == 0) {
              // 큐에 넣을 수 있는지 판단
              int whichStair = stair[k];

              if (whichStair == 0) {
                // q1 확인
                if (q1.size() < 3) {
                  // 넣을 수 있음
                  dist[k]--;
                  q1.add(1); // 나중에 시작을 0, 1 중 뭐로 해야하는지 다시 확인해보기
                }
              } else {
                // q2 확인
                if (q2.size() < 3) {
                  dist[k]--;
                  q2.add(1);
                }

              }

            } else if (dist[k] > 0) {
              dist[k]--;
            }

          }
          // 넣을 놈들 다 넣었음
          time++;
        }

        // while 끝

        mask++;
      }

      // 2^P 가지 모든 경우 다 봤음

      sb.append("#"+t+" "+result).append("\n");

    }

    System.out.println(sb);

  }

}
