import java.util.*;
import java.io.*;

public class Solution_1953_강상민 {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;
  static int T;
  static int N, M, R, C, L; // 행,열, 맨홀 행, 맨홀 열, 탈출 후 소요된 시간
  static int[][] board;
  static int[][] dist; // 이동하는데 걸린 시간
  static int result = 1;

  // 남 동 북 서
  static int[][] dx = { { 1, 0, -1, 0 }, { 1, 0, -1, 0 }, { 0, 0, 0, 0 }, { 0, 0, -1, 0 }, { 1, 0, 0, 0 }, { 1, 0, 0, 0 }, { 0, 0, -1, 0 } };
  static int[][] dy = { { 0, 1, 0, -1 }, { 0, 0, 0, 0 }, { 0, 1, 0, -1 }, { 0, 1, 0, 0 }, { 0, 1, 0, 0 }, { 0, 0, 0, -1 }, { 0, 0, 0, -1 } };
  // dx[board-1][dir], dy[board-1][dir] 로 4방향 탐색

  // 1번 터널인 경우 일반적인 dx dy 이동인데
  // 2번 터널은 dx={1,-1}, dy={0,0} 이다
  // 애초에 board가 1~7 값 아니면 못가고
  // 현재 board[][]값이 1이면 dx[board[][]-1][dir] 식으로?
  // 그럼 dx = { {1,-1}, {0, 0}, .... } 이런 식으로 세팅?

  // 무조건 상하좌우 접근하도록 설계 : 못가는 방향이면 0,0으로 -> dx 행 크기 고정하려고
  // 남, 동, 북, 서 순서대로

  public static void main(String[] args) throws IOException {
    T = Integer.parseInt(br.readLine());

    for (int t = 1; t <= T; t++) {
      // 변수 초기화
      st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      M = Integer.parseInt(st.nextToken());
      R = Integer.parseInt(st.nextToken());
      C = Integer.parseInt(st.nextToken());
      L = Integer.parseInt(st.nextToken());

      board = new int[N][M];
      dist = new int[N][M];

      result = 0;

      for (int i = 0; i < N; i++) {
        st = new StringTokenizer(br.readLine());
        for (int j = 0; j < M; j++) {
          // 0: 터널 없, 1~7 터널 타입
          board[i][j] = Integer.parseInt(st.nextToken());
          dist[i][j] = -1; // 초깃값 세팅
        }
      }

      Deque<int[]> q = new ArrayDeque<>();
      q.add(new int[] { R, C });
      dist[R][C] = 1;

      // BFS 시작
      while (!q.isEmpty()) {
        int[] cur = q.poll();
        int curX = cur[0];
        int curY = cur[1];

        int curV = board[curX][curY]; // 1~7값 중 하나

        for (int dir = 0; dir < 4; dir++) {
          int nx = curX + dx[curV - 1][dir]; // board-1 : dx 행의 index 이며 파이프 이동 방향 제어
          int ny = curY + dy[curV - 1][dir];

          if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
          if (board[nx][ny] == 0) continue; // 빈공간 이동 불가
          if (dist[nx][ny] != -1) continue; // 파이프로 못가는 위치는 dx dy가 0이고, 다음 노드와 현재 노드 동일, 여기서 걸러짐

          // 남쪽으로 가면 다음 위치에선 북쪽에서 받아야함 : 다음 위치 파이프가 연결되었는지 확인
          // 남 동 북 서 0 1 2 3
          // 북 서 남 동 2 3 0 1
          int nxtDir = dir + 2;
          if (nxtDir >= 4) nxtDir %= 4;

          int nxtV = board[nx][ny] - 1;
          if (dx[nxtV][nxtDir] == 0 && dy[nxtV][nxtDir] == 0) continue; // curX, curY 와 연결 안됨

          int temp = dist[curX][curY] + 1;
          if (temp > L) continue; // 최대 이동 제한

          dist[nx][ny] = temp;
          result++;
          q.add(new int[] { nx, ny });

        }

      }
      // 맨 처음 위치 더하기
      result +=1;
      System.out.println("#" + t+" "+result);
    }

  }
  // tc 종료

}
