package d4;
import java.io.*;
import java.util.*;

public class Solution_1249_김민우 {
  static BufferedReader br;
  static StringTokenizer st;

  static int T, N;
  static int[][] map;
  static int[][] min;
  static Queue<int[]> que;
  static int[][] delta = {{0,-1}, {-1,0}, {0,1}, {1,0}};

  public static void main(String[] args) throws IOException{
    br = new BufferedReader(new InputStreamReader(System.in));
    st = new StringTokenizer(br.readLine());

    T = Integer.parseInt(st.nextToken());
    for(int test_case = 1; test_case <= T; test_case++){
      st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());

      map = new int[N][N];
      min = new int[N][N];
      que = new LinkedList<>();

      for(int i= 0 ; i < N; i++){
        st = new StringTokenizer(br.readLine());
        String s = st.nextToken();
        for (int j = 0; j < N; j++){
          map[i][j] = s.charAt(j) - '0';
          min[i][j] = Integer.MAX_VALUE;
        }
      }
      min[0][0] = 0;
      que.offer(new int[]{0,0});

      while(!que.isEmpty()){
        int[] cur = que.poll();
        for(int[] dir : delta){
          int r = cur[0];
          int c = cur[1];
          int nr = r + dir[0];
          int nc = c + dir[1];

          if(isIn(nr, nc)){
            int cur_cost = min[r][c];
            int next_cost = cur_cost + map[nr][nc];
            if(next_cost < min[nr][nc]){
              min[nr][nc] = next_cost;
              que.offer(new int[]{nr, nc});
            }
            else
              continue;
          }
        }
      }

      int goal = min[N-1][N-1];

      System.out.printf("#%d %d\n", test_case, goal);
    }
  }

  public static boolean isIn(int r, int c){
    return r >= 0 && r < N && c >= 0 && c < N;
  }
}
