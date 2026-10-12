import java.util.*;
import java.io.*;


public class Solution_1868_강상민 {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;
  static StringBuilder sb = new StringBuilder();
  static int T,N;
  static char[][] board;
  static boolean[][] vis;
  static int[][] num;
  static int[] dx={1,0,-1,0,1,1,-1,-1};
  static int[] dy={0,1,0,-1,1,-1,1,-1};

  public static void main(String[] args) throws IOException {
    T = Integer.parseInt(br.readLine());
    for (int t=1; t<=T; t++) {
      N = Integer.parseInt(br.readLine());

      board = new char[N][N];
      vis = new boolean[N][N];
      num = new int[N][N];

      for (int i=0; i<N; i++) {
        String s = br.readLine();
        for (int j=0; j<N; j++) {
          board[i][j] = s.charAt(j);
        }
      }

      // 지뢰 아닌 곳의 숫자 먼저 구하기
      for (int i=0; i<N; i++) {
        for (int j=0; j<N; j++) {
          if (board[i][j] == '*') continue;
          for (int d=0; d<8; d++) {
            int nx = i+dx[d];
            int ny = j+dy[d];

            if (nx<0||ny<0||nx>=N||ny>=N) continue;
            if (board[nx][ny] == '*') num[i][j]++;
          }
        }
      }

      int cnt = 0;

      // 지뢰x, 방문x, 주변에 지뢰 적어도 1개이상 있는 곳 선택
      for (int i=0; i<N; i++) {
        for (int j=0; j<N; j++) {
          if (board[i][j] == '*' || vis[i][j] || num[i][j] != 0) continue;
          cnt++;

          Deque<int[]> q = new ArrayDeque<>();

          q.add(new int[] {i,j});
          vis[i][j] = true;

          while(!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];

            if (num[x][y] != 0) continue; 
            
            for (int dir=0; dir<8; dir++) {
              int nx = x+dx[dir];
              int ny = y+dy[dir];

              if (nx<0||ny<0||nx>=N||ny>=N) continue;
              if (board[nx][ny] == '*') continue;
              if (vis[nx][ny]) continue;

              vis[nx][ny] = true;
              q.add(new int[] {nx,ny});
            }

          }

        }
  
      }

      for (int i=0; i<N; i++) {
          for (int j=0; j<N; j++) {
            if (board[i][j] == '.' && !vis[i][j]) cnt++;
          }
      }

      sb.append("#"+t+" "+cnt).append("\n");


    }

    System.out.println(sb);
  }
  
}
