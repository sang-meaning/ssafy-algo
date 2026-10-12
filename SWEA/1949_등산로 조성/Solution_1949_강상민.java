import java.util.*;
import java.io.*;

/*
AI x
시작점들 고르고 깊이 탐색
탐색하다가, 다음 위치로 못가는 시행에서 공사
공사는 한번만 - static 변수 broken 으로 관리
각 위치 방문 여부, 공사 여부, 공사한 높이 백트래킹
*/

class Solution_1949_강상민 {
   static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
   static StringTokenizer st;
   static int[] dx={1,0,-1,0};
   static int[] dy={0,1,0,-1};
   static int T; 
   static int N,K;
   static int longest = 0;
   static boolean broken = false;

     public static void main(String[] args) throws IOException{
      T = Integer.parseInt(br.readLine());
      for (int t=1; t<=T; t++) {
         longest = 0; // 등산로 최대 길이 초기화
         broken = false; // 공사 여부 초기화
         st = new StringTokenizer(br.readLine());
         N = Integer.parseInt(st.nextToken());
         K = Integer.parseInt(st.nextToken());

         int[][] board = new int[N][N];
         boolean[][] visited = new boolean[N][N];
         int peak = 0; // 가장 높은 봉우리 높이

         // 지도
         for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<N; j++) {
               board[i][j] = Integer.parseInt(st.nextToken());
               if(peak < board[i][j]) peak = board[i][j];
            }
         }

         // 가장 높은 봉우리들에서 DFS
         Deque<int[]> q = new ArrayDeque<>(); // 봉우리들 좌표

         for (int i=0; i<N; i++) 
            for (int j=0; j<N; j++) 
               if (board[i][j] == peak) 
                  q.add(new int[] {i,j});


         int result = 0; // 최종 출력

         while(!q.isEmpty()) {
            int[] start = q.poll();

            longest = 0;
            broken = false;

            visited[start[0]][start[1]] = true;
            dfs(1,start[0],start[1],board, visited);
            visited[start[0]][start[1]] = false;

            // 한 봉우리에 대해 탐색 완료
            result = Math.max(result, longest);

         }
         System.out.println("#"+t+" "+result);




      }
      // 모든 tc 종료

     }

     // 어차피 depth는 N*N까지 나올 수 있으므로 사전 종료 없음 -> 메서드 void 타입
     static void dfs(int depth, int curX, int curY, int[][] board, boolean[][] visited) {
         longest = Math.max(longest, depth); // 최대 길이 갱신

         // 상하좌우
         for (int dir=0; dir<4; dir++) {
            int nx = curX+dx[dir];
            int ny = curY+dy[dir];

            if (nx<0||ny<0||nx>=N||ny>=N) continue; // 지도 바깥
            if (visited[nx][ny] == true) continue; // 방문

            // 1) board[nx][ny] < 현재 위치값이면 그위치 선택하고 dfs
            // 2) board[nx][ny] >= 현재 위치값 이고, 한번도 공사한 적 없으며, board[nx][ny] 를 (현재값-1)로 만드는 K 존재하면 이동
            // K로 공사하되, (현재 위치값-1) 으로 깎는것만 보면됨 : 그보다 더 깎을 필요 없다

            if (board[nx][ny] < board[curX][curY]) {
               visited[nx][ny] = true;
               dfs(depth+1, nx,ny, board, visited);
               visited[nx][ny] = false;
            }
            else if (board[nx][ny] >= board[curX][curY] && broken == false && board[nx][ny] - K < board[curX][curY]) {
               int dif = board[nx][ny] - (board[curX][curY]-1); // board[nx][ny] 에서 깎을 높이

               visited[nx][ny] = true; // 방문함
               board[nx][ny] -= dif; // 백트래킹 위해 dif 필요
               broken = true; // 공사 진행
               dfs(depth+1, nx,ny, board, visited);
               broken = false;
               board[nx][ny] += dif;
               visited[nx][ny] = false;
            }
               



         }


     }
}