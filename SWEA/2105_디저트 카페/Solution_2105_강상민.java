import java.util.*;
import java.io.*;

// 너무 어렵다..

public class Solution_2105_강상민 {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringBuilder sb = new StringBuilder();
  static StringTokenizer st;
  static int T;
  static int N;
  static int[][] board;
  static boolean[] visited;
  static int result = -1;

  // 탐색 순서 (1,1), (1,-1), (-1,-1), (-1,1)
  static int[] dx = {1,1,-1,-1};
  static int[] dy = {1,-1,-1,1};

  // 돌아와야하니 꼭짓점 4개 제외해도 됨
  // dfs
  
  // dfs 안에서 4방향 탐색 + 가지치기로 풀었다가 시간초과 남

  // ai 피드백
  // N=20으로 매우 크고 4방향 탐색은 "꺾은 횟수" 가지치기가 있더라도 시간 초과난다
  // dir 초깃값을 curDir로 초기화하여 기본적으로 같은 방향으로 자동 탐색
  // 그게 안되면 방향 꺾어주기
  // dir는 무조건 0부터 시작하도록 약속했다. dir==3 에서만 정답이 나온다

  
  public static void main(String[] args) throws IOException {
    T = Integer.parseInt(br.readLine());

    for (int t=1; t<=T; t++) {
      N = Integer.parseInt(br.readLine());
      board = new int[N][N];

      // Integer 디저트 방문 여부
      visited = new boolean[101]; // board 값 index로 사용
      result = -1;

      for (int i=0; i<N; i++) {
        st = new StringTokenizer(br.readLine());
        for (int j=0; j<N; j++) {
          board[i][j] = Integer.parseInt(st.nextToken());
          visited[board[i][j]] = false; // 안해도 false긴 함
        }
      }


      // 각 위치를 시작점으로 dfs
      for (int i=0; i<N; i++) {
        for (int j=0; j<N; j++) {
          // 굳이 모서리 안봄
          if (corner(i,j)) continue;

          visited[board[i][j]] = true;
          dfs(1, 0, i,j, i,j);
          visited[board[i][j]] = false;

        }
      }

     
      sb.append("#"+t+" "+result).append("\n");
    }

    // tc 종료
    System.out.print(sb);
  
  }


  // curDir 을 4방향 탐색 for 문의 dir 시작점으로 삼음
  static void dfs(int depth, int curDir, int curX, int curY, int startX, int startY) {

    // 방향 순서 (1,1), (1,-1), (-1,-1), (-1,1)
    // dir 초깃값을 curDir로 초기화하여 기본적으로 같은 방향으로 자동 탐색
    // 그게 안되면 방향 꺾어주기
    // 시작을 dx[0] 사용했기에 dir == 3 이고, 시작위치 도착하는 순간 dfs 종료
    for (int dir=curDir; dir<4; dir++) {
      int nx = curX+dx[dir];
      int ny = curY+dy[dir];
      
      if (nx<0||ny<0||nx>=N||ny>=N) continue;

      // 종료조건
       if (depth >= 4 && dir == 3 && nx == startX && ny == startY) {
          // 위 조건 만족하는 것은 한바퀴 돌았을 때
          // 최소 4개가 이어져야한다
          result = Math.max(result, depth);
          return;
        }


      if (visited[board[nx][ny]] == true) continue; // 방문한 디저트

      int nxtV = board[nx][ny];

      visited[nxtV] = true;
      dfs(depth+1, dir, nx, ny, startX, startY);

      // 백트래킹
      visited[nxtV] = false;
    }

    

  }

  static boolean corner(int i, int j) {
    // 0,0  0,N-1,  N-1,0   N-1,N-1

    if (i==0 && j==0) return true;
    if (i==0 && j==N-1) return true;
    if (i==N-1 && j==0) return true;
    if (i==N-1 && j==N-1) return true;

    return false;
  }
  
}
