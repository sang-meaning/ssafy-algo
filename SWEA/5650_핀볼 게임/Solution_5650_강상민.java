import java.util.*;
import java.io.*;


/*
북 동 남 서 : 0 1 2 3
블록이 1이면, 그 위치의 공이 0->2, 1->3, 2->1, 3->0
블록이 2이면, 그 위치의 공이 0->1, 1->3, 2->0, 3->2
블록이 3이면, 그 위치의 공이 0->3, 1->2, 2->0, 3->1
블록이 4이면, 그 위치의 공이 0->2, 1->0, 2->3, 3->1
블록이 5이면, 그 위치의 공이 0->2, 1->3, 2->0, 3->1
*/

public class Solution_5650_강상민 {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;
  static StringBuilder sb = new StringBuilder();
  static int T,N;
  static int[][] board;
  static int result;
  static HashMap<Integer, int[]> map;
  static int[] dx={-1,0,1,0};
  static int[] dy={-0,1,0,-1};

  public static void main(String[] args) throws IOException {
    T = Integer.parseInt(br.readLine().trim());

    for (int t=1; t<=T; t++) {
      N = Integer.parseInt(br.readLine().trim());

      board = new int[N][N];
      result = 0;

      // x, y, board값
      // board값으로 정렬, 니증에 pq.size() / 2 개의 map 갱신하도록
      // key는 board값, value는 x1,y1,x2,y2
      PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> Integer.compare(a[2],b[2]));
      map = new HashMap<>();

      for (int i=0; i<N; i++) {
        st = new StringTokenizer(br.readLine().trim());
        for (int j=0; j<N; j++) {
          // 0~5 : 블록, 6~10 : 웜홀, -1 : 블랙홀
          board[i][j] = Integer.parseInt(st.nextToken());
          if (board[i][j] >=6 && board[i][j] <= 10) {
            pq.add(new int[] {i,j,board[i][j]});
          }
        }

      }

      int iter = pq.size() / 2;
      for (int i=0; i<iter; i++) {
        int[] cur1 = pq.poll();
        int[] cur2 = pq.poll();

        map.put(cur1[2], new int[] {cur1[0], cur1[1], cur2[0], cur2[1]});
      }

      for (int i=0; i<N; i++) {
        for (int j=0; j<N; j++) {
          if (board[i][j] != 0) continue;

          // 모든 위치에 대해 4방향 검사 진행
          result = Math.max(result, find(i,j,0));
          result = Math.max(result, find(i,j,1));
          result = Math.max(result, find(i,j,2));
          result = Math.max(result, find(i,j,3));
          
        }
      }

      sb.append("#"+t+" "+result).append("\n");


    }

    // tc 종료
    System.out.print(sb);


  }

  static int find(int x, int y, int dir) {
    // x,y 위치에서 dir 방향으로 시작

    int curD = dir;

    int point = 0;

    int nx = x+dx[curD];
    int ny = y+dy[curD];

    while(true) {

      // 바깥으로 나가면
      if (nx<0||ny<0||nx>=N||ny>=N) {
        // dir을 180도 바꾸고 그 위치로 1간 이동
        curD += 2;
        if (curD >= 4) curD %= 4;

        nx += dx[curD];
        ny += dy[curD];

        point++;
      }

      // 처음 위치로 돌아오면 끝
      if (nx == x && ny == y) break;

      // 블랙홀을 만나면 끝
      if (board[nx][ny] == -1) break;

      

      // 블록을 만나면
      if (board[nx][ny] >= 1 && board[nx][ny] <= 5) {

        // 다음 위치의 dir 반환
        int nxtDir = block(nx,ny,curD);

        curD = nxtDir;

        point++;

      }

      // 웜홀을 만나면
      if (board[nx][ny] >= 6 && board[nx][ny] <= 10) {
        int[] otherWorm = findWorm(nx, ny, board[nx][ny]);

        nx = otherWorm[0];
        ny = otherWorm[1];
      }

      // 1칸 이동
      nx += dx[curD];
      ny += dy[curD];


    }

    return point;
  }


  /*
  북 동 남 서 : 0 1 2 3
  블록이 1이면, 그 위치의 공이 0->2, 1->3, 2->1, 3->0
  블록이 2이면, 그 위치의 공이 0->1, 1->3, 2->0, 3->2
  블록이 3이면, 그 위치의 공이 0->3, 1->2, 2->0, 3->1
  블록이 4이면, 그 위치의 공이 0->2, 1->0, 2->3, 3->1
  블록이 5이면, 그 위치의 공이 0->2, 1->3, 2->0, 3->1
  */

  static int block(int x, int y, int dir) {
    int nxtDir = -1;

    int blockType = board[x][y];

    if (blockType == 1) {
      if (dir == 0) nxtDir = 2;
      else if (dir == 1) nxtDir = 3;
      else if (dir == 2) nxtDir = 1;
      else if (dir == 3) nxtDir = 0;
    } 
    else if (blockType == 2) {
      if (dir == 0) nxtDir = 1;
      else if (dir == 1) nxtDir = 3;
      else if (dir == 2) nxtDir = 0;
      else if (dir == 3) nxtDir = 2;
    }
    else if (blockType == 3) {
      if (dir == 0) nxtDir = 3;
      else if (dir == 1) nxtDir = 2;
      else if (dir == 2) nxtDir = 0;
      else if (dir == 3) nxtDir = 1;
    }
    else if (blockType == 4) {
      if (dir == 0) nxtDir = 2;
      else if (dir == 1) nxtDir = 0;
      else if (dir == 2) nxtDir = 3;
      else if (dir == 3) nxtDir = 1;
    }
    else if (blockType == 5) {
      if (dir == 0) nxtDir = 2;
      else if (dir == 1) nxtDir = 3;
      else if (dir == 2) nxtDir = 0;
      else if (dir == 3) nxtDir = 1;
    }



    return nxtDir;
  }



  // x, y, 값을 주면 다른 웜홀 좌표 반환
  static int[] findWorm(int x, int y, int value) {
    int[] cur = map.get(value);

    int x1=cur[0];
    int y1=cur[1];
    int x2=cur[2];
    int y2=cur[3];

    if (x==x1 && y==y1) {
      return new int[] {x2, y2};
    } else {
      return new int[] {x1, y1};
    }
  }


  
}
