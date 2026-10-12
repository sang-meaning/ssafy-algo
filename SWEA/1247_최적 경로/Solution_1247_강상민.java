import java.util.*;
import java.io.*;

public class Solution_1247_강상민 {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;
  static StringBuilder sb = new StringBuilder();
  static int T;
  static int N;
  static int[][][] dp; // x,y 위치에 존재하고, mask 의 위치를 방문했으며, 배열값은 현재 위치로부터 목적지까지 최소 비용
  static int[][] board; // board[0][0] : 0번째 노드의 x좌표, board[0][1] : 0번째 노드의 y좌표
  static int INF = 0x3f3f3f3f;
  static int x1,y1,x2,y2; //회사, 집
  public static void main(String[] args) throws IOException {
    T = Integer.parseInt(br.readLine());

    for (int t=1; t<=T; t++) {
      N = Integer.parseInt(br.readLine());

      dp = new int[101][101][1<<N]; 
      board = new int[N][2];

      st = new StringTokenizer(br.readLine());
      x1 = Integer.parseInt(st.nextToken());
      y1 = Integer.parseInt(st.nextToken());
      x2 = Integer.parseInt(st.nextToken());
      y2 = Integer.parseInt(st.nextToken());

      for (int i=0; i<N; i++) {
        // x,y 좌표 받기
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        board[i][0] = x;
        board[i][1] = y;
      }

      for (int i=0; i<101; i++) {
        for (int j=0; j<101; j++) {
          for (int k=0; k<(1<<N); k++) {
            dp[i][j][k] = -1;
          }
        }
      }

      // 세팅 끝

      // 탐색 시작 : x1,y1 부터
      int result = dfs(x1, y1, 0); // 아무곳도 방문 안해서 mask=0

      sb.append("#"+t+" "+result).append("\n");

    }


    // tc 종료
    System.out.println(sb);
    
  }

  // 반환값 : 현재 위치 ~ 집으로 돌아가는데 최소 비용
  static int dfs(int curX, int curY, int mask) {
    // 종료조건
    if (mask == (1<<N)-1) { // 모든 노드를 방문, 깊이 탐색 끝까지 봤고, 현재위치 ~ 집까지 비용만 리턴
      // 이제 집으로
      int toHome = Math.abs(curX-x2) + Math.abs(curY-y2);
      return toHome;
    }

    // memoization
    // 해당 위치 비용 갱신했으면 그대로 사용한다
    if (dp[curX][curY][mask] != -1) {
      return dp[curX][curY][mask];
    }

    // 이제 갱신하고자 함
    dp[curX][curY][mask] = INF;

    for (int nxt=0; nxt<N; nxt++) { // 모든 노드에 대해 본다

      // 아직 다음 위치를 방문하지 않았으면
      if ((mask & (1<<nxt)) == 0) {
        int nx = board[nxt][0]; // 다음 방문 후보 좌표
        int ny = board[nxt][1];

        int cost = Math.abs(nx - curX) + Math.abs(ny - curY); // 다음 위치로 가는 비용

        // 현재 위치, 현재 마스크의 dp를 더 작은 값으로 갱신
        dp[curX][curY][mask] = Math.min(dp[curX][curY][mask], dfs(nx,ny, (mask | (1<<nxt))) + cost);
        
      }

    }

    return dp[curX][curY][mask];

  }
  
}
