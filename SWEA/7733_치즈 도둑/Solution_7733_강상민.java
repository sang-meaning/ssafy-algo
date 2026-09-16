import java.util.*;
import java.io.*;

public class Solution_7733_강상민 {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;
  static StringBuilder sb = new StringBuilder();
  static int T, N;
  static int[][] board;
  static int[][] dist; // 초깃값 -1, 먹어서 못가는 곳 -2
  static int[] dx={1,0,-1,0};
  static int[] dy={0,1,0,-1};

  public static void main(String[] args) throws IOException {
    T = Integer.parseInt(br.readLine());

    for (int t=1; t<=T; t++) {
      N = Integer.parseInt(br.readLine());

      board = new int[N][N];
      dist = new int[N][N];

      for (int i=0; i<N; i++) {
        st = new StringTokenizer(br.readLine());
        for (int j=0; j<N; j++) {
          board[i][j] = Integer.parseInt(st.nextToken());
        }
      }

    
      int chunk = 0; // 영역 개수 최댓값

      for (int i=0; i<=100; i++) {
        clearD();

        // board에서 i 이하인 곳의 dist를 -2로 만들기
        for (int a=0; a<N; a++) {
          for (int b=0; b<N; b++) {
            if (board[a][b] <= i) dist[a][b] = -2;
          }
        }

        Deque<int[]> q = new ArrayDeque<>();
        int area = 0; // 영역의 개수

        for (int a=0; a<N; a++) {
          for (int b=0; b<N; b++) {
            
            if (dist[a][b] == -2) continue; // 먹힌 치즈
            if (dist[a][b] >= 0) continue; // 방문함

            dist[a][b] = 1;
            q.add(new int[] {a,b}); // bfs 시작할 위치
            area++;

            while(!q.isEmpty()) {
              int[] cur = q.poll();

              for (int dir=0; dir<4; dir++) {
                int nx = cur[0]+dx[dir];
                int ny = cur[1]+dy[dir];

                if (nx<0||ny<0||nx>=N||ny>=N) continue;
                if (dist[nx][ny] >= 0) continue;
                if (dist[nx][ny] == -2) continue;
                
                dist[nx][ny] = dist[cur[0]][cur[1]] + 1;
                q.add(new int[] {nx,ny});
              }
            }
            
          }
        }

        // dist, area 갱신 완료
        chunk = Math.max(chunk, area);
        
      }

      sb.append("#"+t+" "+chunk).append("\n");




    }
    System.out.println(sb);

  }

  static void clearD() {
    for (int i=0; i<N; i++)
      for (int j=0; j<N; j++)
        dist[i][j] = -1;
  }
}
