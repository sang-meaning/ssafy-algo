import java.util.*;
import java.io.*;

/*
귀찮은 구현 문제
*/

public class Solution_1873_강상민 {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;
  static StringBuilder sb = new StringBuilder();
  static int T;
  static int r,c;
  static char[][] board;
  static int N; // 명령어 길이
  static String command; // 명령어 
  static int[] dx={-1,1,0,0};
  static int[] dy={0,0,-1,1};

  public static void main(String[] args) throws IOException {
    T = Integer.parseInt(br.readLine());

    for (int t=1; t<=T; t++) {
      st = new StringTokenizer(br.readLine());

      int x=-1; // 탱크 위치
      int y=-1;
      int dir=-1; // 상 하 좌 우 : 0 1 2 3

      r = Integer.parseInt(st.nextToken());
      c = Integer.parseInt(st.nextToken());

      board = new char[r][c];

      for (int i=0; i<r; i++) {
        String temp = br.readLine();

        for (int j=0; j<c; j++) {
          board[i][j] = temp.charAt(j);

          // 탱크 위치
          if (board[i][j] == '^') { // 상 하 좌 우 순서대로
            x=i; y=j;
            dir=0;
          } else if (board[i][j] == 'v') {
            x=i; y=j;
            dir=1;
          } else if (board[i][j] == '<') {
            x=i; y=j;
            dir=2;

          } else if (board[i][j] == '>') {
            x=i; y=j;
            dir=3;
          }


        }
      }

      N = Integer.parseInt(br.readLine());

      command = br.readLine();

      for (int i=0; i<N; i++) {
        char now = command.charAt(i);

        //System.out.println("com: "+now);

        if (now == 'U') {
          // dir 0으로, char[][] ^로, 그 방향으로 한칸 갈 수 있으면 가기
          dir = 0;
          board[x][y] = '^';

          int nx = x+dx[dir];
          int ny = y+dy[dir];

          if (nx>=0 && ny>=0 && nx<r && ny<c && board[nx][ny] == '.') {
            board[x][y] = '.';
            board[nx][ny] = '^';
            x=nx;
            y=ny;
          }

        } else if (now == 'D') {
          dir = 1;
          board[x][y] = 'v';

          int nx = x+dx[dir];
          int ny = y+dy[dir];

          if (nx>=0 && ny>=0 && nx<r && ny<c && board[nx][ny] == '.') {
            board[x][y] = '.';
            board[nx][ny] = 'v';
            x=nx;
            y=ny;
          }

        } else if (now == 'L') {
          dir = 2;
          board[x][y] = '<';

          int nx = x+dx[dir];
          int ny = y+dy[dir];

          if (nx>=0 && ny>=0 && nx<r && ny<c && board[nx][ny] == '.') {
            board[x][y] = '.';
            board[nx][ny] = '<';
            x=nx;
            y=ny;
          }

        } else if (now == 'R') {
          dir = 3;
          board[x][y] = '>';

          int nx = x+dx[dir];
          int ny = y+dy[dir];

          if (nx>=0 && ny>=0 && nx<r && ny<c && board[nx][ny] == '.') {
            board[x][y] = '.';
            board[nx][ny] = '>';
            x=nx;
            y=ny;
          }

        } else if (now =='S') {
          int curX = x+dx[dir];
          int curY = y+dy[dir];

          while(true) {
            if (curX>=0 && curY>=0 && curX<r && curY<c && board[curX][curY] == '*') {
              // 벽돌 부수기
              board[curX][curY] = '.';
              break;
            }

            // 강철이면 그냥 break;
            if (curX>=0 && curY>=0 && curX<r && curY<c && board[curX][curY] == '#') break;

            // 그 외는 한칸 전진
            curX += dx[dir];
            curY += dy[dir];

            if(curX<0|| curY<0 || curX>=r || curY>=c) break;
          }

        }
      }

      sb.append("#"+t+" ");
      for (int i=0; i<r; i++) {
        for (int j=0; j<c; j++) {
          sb.append(board[i][j]);
        }
        sb.append("\n");
      }

    


    } 
    // tc 종료
    System.out.println(sb);
  }
  
}
