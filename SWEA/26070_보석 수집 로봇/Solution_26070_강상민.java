import java.util.*;
import java.io.*;

/*

보석 개수만큼 bfs (시작점 -> 보석) 에 대해 진행
3차원 dist 배열로 {x좌표, y좌표, 바라보고 있는 방향} 일 때의 "꺾은 횟수" 를 갱신한다
이전 좌표의 dir를 그대로 가져오거나 +1 하거나 : 2가지만 한다

탐색하며 다음 위치를 큐에 넣기 전, "보석의 dist 보다 탐색중인 dist가 크면" 큐에 넣지 않도록 하였다
그렇지 않으면 비효율적이므로 넣을 가치가 있는 좌표만 큐에 넣어주었다

한 보석에 대해 시행이 끝나면, nxtStart 큐에 보석의 4방향 dist 값을 전부 넣고 (도착한 것만)
다음 보석 시행에서 dist 배열 전부 초기화하고, nxtStart 에서 뽑아 초기 세팅을 한다
    이 때 dist는 초기화하지만, 직전 보석의 dist 값들을 기억해야하므로 nxtStart 마지막 차원에 저장하였다

*/


public class Solution_26070_강상민 {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;
  static StringBuilder sb = new StringBuilder();
  static int T;
  static int N;
  static int[][] board;
  static int[][][] dist; // 마지막은 방향
  static int[] dx={0,1,0,-1};
  static int[] dy={1,0,-1,0};
  public static void main(String[] args) throws IOException {
    T = Integer.parseInt(br.readLine());

    for (int t=1; t<=T; t++) {
      N = Integer.parseInt(br.readLine());

      board = new int[N][N];
      dist = new int[N][N][4];
      clearD();

      PriorityQueue<int[]> jewel = new PriorityQueue<>((a,b) -> Integer.compare(a[2],b[2]));

      for (int i=0; i<N; i++) {
        st = new StringTokenizer(br.readLine());
        for (int j=0; j<N; j++) {
          board[i][j] = Integer.parseInt(st.nextToken());
          if (board[i][j] != 0) jewel.add(new int[] {i,j,board[i][j]});
        }
      }

      int jewelSize = jewel.size();

      // bfs 를 위한 큐
      Deque<int[]> q = new ArrayDeque<>(); 
      q.add(new int[] {0,0,0}); // 좌표와 방향
      dist[0][0][0] = 0;

      // i>=1 부터 초기 보석 위치 가져오고, dist 배열 초기화를 위한 시작점 정보를 담은 큐
      // x,y,dir,값
      Deque<int[]> nxtStart = new ArrayDeque<>();

      int t_x = -1; // 맨 마지막 보석 위치
      int t_y = -1; 


      // 보석 개수만큼 bfs 시작
      for (int i=0; i<jewelSize; i++) {
        int[] curJ = jewel.poll(); // 목적지 보석

        // 최종 도착 보석 위치
        if (i == jewelSize-1) {
          t_x = curJ[0];
          t_y = curJ[1];
        }

        // 첫 시행이 아닐 때, dist 초기화하고, 직전 보석의 dist 들로 시작점 q에 넣고 시작 dist 값 세팅
        if (i != 0) {
          clearD();
          while(!nxtStart.isEmpty()) {
            int[] curr = nxtStart.poll();
            dist[curr[0]][curr[1]][curr[2]] = curr[3];
            q.add(new int[] {curr[0], curr[1], curr[2]});
          }
        }


        boolean arrived = false; // 한번이라도 보석에 도착했는지

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            
            // 직진하거나 한번 꺾거나
            for (int dir=0; dir<2; dir++) { // dir == 0 직진, dir == 1 꺾기
              int nxtD = cur[2]+dir;
              if (nxtD == 4) nxtD = 0;

              int nx = cur[0]+dx[nxtD];
              int ny = cur[1]+dy[nxtD];

              if (nx<0||ny<0||nx>=N||ny>=N) continue;
              if (dist[nx][ny][nxtD] != -1) continue;

              // 목적지 도착하면 더이상 큐에 안넣기
              if (nx == curJ[0] && ny == curJ[1]) {
                int add = 0;
                if (dir==1) add++;
                dist[nx][ny][nxtD] = dist[cur[0]][cur[1]][cur[2]]+add;
                arrived = true;
                continue;
              }

              // 목적지 보석이 아닌, 갈 수 있는 위치
              int add = 0;
              if (dir==1) add++;
              dist[nx][ny][nxtD] = dist[cur[0]][cur[1]][cur[2]]+add; // 꺾었을 때만 dist 1증가

              boolean doNotAdd = false;

              // 한번이라도 보석의 dist가 갱신된 적이 있고
              if (arrived == true) {
                // -1이 아닌 보석의 dist 최솟값보다 현재 dist가 크면 큐에 넣을 필요없다
                for (int temp = 0; temp<4; temp++) {
                  int target = dist[curJ[0]][curJ[1]][temp];
                  if (target == -1) continue;
                  if (target < dist[nx][ny][nxtD]) doNotAdd = true;
                }
              }

              if (doNotAdd == true) continue;
              
              // 큐에 넣을 가치가 있는 위치만 큐에 넣기
              q.add(new int[] {nx,ny,nxtD});

            }

        }

        // 한 보석에 대해 bfs 종료
        // 다음 보석 시행을 위해 q에 현재 보석의 dist값 넣기 (-1 아닌)
        // dist 초기화, 시작점 초기화는 다음 i 시행에서 poll 한 후 진행하기
        for (int dir = 0; dir<4; dir++) {
          int target = dist[curJ[0]][curJ[1]][dir];
          if (target == -1) continue;

          nxtStart.add(new int[] {curJ[0], curJ[1], dir, target});
        }




      }

      int result = Integer.MAX_VALUE;

      for (int dir = 0; dir<4; dir++) {
        int target = dist[t_x][t_y][dir];
        if (target == -1) continue;

        if (result > target) result = target;

      }

      sb.append("#"+t+" "+result).append("\n");



    }

    // tc 종료
    System.out.print(sb);
  }




  static void clearD() {
    for (int i=0; i<N; i++) {
      for (int j=0; j<N; j++) {
        for (int k=0; k<4; k++) {
          dist[i][j][k] = -1;
        }
      }
    }
  }
  
}
