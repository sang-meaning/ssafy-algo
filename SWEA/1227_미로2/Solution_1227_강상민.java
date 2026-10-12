import java.util.*;
import java.io.*;

public class Solution_1227_강상민 {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;
  static StringBuilder sb = new StringBuilder();
  static int T = 10;
  static char[][] board;
  static int[][] dist;
  static int[] dx={1,0,-1,0};
  static int[] dy={0,1,0,-1};

  public static void main(String[] args) throws IOException {
    
    for (int t=1; t<=T; t++) {
      br.readLine();
      board = new char[100][100];
      dist = new int[100][100];
      for (int i=0; i<100; i++)
        for (int j=0; j<100; j++)
          dist[i][j] = -1; // 초깃값

      Deque<int[]> q = new ArrayDeque<>();

      int ex = 0, ey = 0;

      for (int i=0; i<100; i++) {
        String s = br.readLine();
        for (int j=0; j<100; j++) {
          board[i][j] = s.charAt(j); // 1벽 2시작 3도착

          if (board[i][j] == '2') {
            q.add(new int[] {i,j});
            dist[i][j] = 0;
          }

          if (board[i][j] == '3') {
            ex = i;
            ey = j;
          }
        }
      }


      // 세팅 완료

      while(!q.isEmpty()) {
        int[] cur = q.poll();

        for (int dir=0; dir<4; dir++) {
          int nx = cur[0]+dx[dir];
          int ny = cur[1]+dy[dir];

          if (nx<0||ny<0||nx>=100||ny>=100) continue;
          if (board[nx][ny] == '1') continue;
          if (dist[nx][ny] != -1) continue;

          dist[nx][ny] = dist[cur[0]][cur[1]] + 1;
          q.add(new int[] {nx,ny});

          if (nx == ex && ny == ey) {
            break;
          }


        }
      }

      // bfs 끝

      sb.append("#"+t+" ");

      if (dist[ex][ey] == -1) {
        sb.append(0);
      } else {
        sb.append(1);
      }

      sb.append("\n");

      
      
    }

    System.out.println(sb);
  }
  
}
