import java.util.*;
import java.io.*;

class Solution
{
 static BufferedReader br;
  static StringTokenizer st;
  static int[][] maze;
  static int[][] delta = new int[][]{{0,-1}, {-1,0}, {0, 1}, {1,0}};
  static boolean[][] visited;
  public static void main(String[] args) throws Exception{
    
    br = new BufferedReader(new InputStreamReader(System.in));
    
    for(int t = 0; t < 10; t++){
      st = new StringTokenizer(br.readLine());
      int test_case = Integer.parseInt(st.nextToken());

      maze = new int[100][100];
      visited = new boolean[100][100];
      for(int i = 0; i < 100; i++){
        st = new StringTokenizer(br.readLine());
        String s = st.nextToken();
        for(int j = 0; j < 100; j++){
          maze[i][j] = s.charAt(j) - '0';
        }
      }

      Queue<int[]> route = new LinkedList<>();

      route.offer(new int[]{1,1});
      int answer = 0;

      while(!route.isEmpty()){
        int[] idx = route.poll();
        if(maze[idx[0]][idx[1]] == 3){
          answer = 1;
          break;
        }

        for(int[] d : delta){
          int nr = idx[0] + d[0];
          int nc = idx[1] + d[1];
          if(isIn(nr,nc) && maze[nr][nc] != 1 && !visited[nr][nc]){
            route.offer(new int[]{nr, nc});
            visited[nr][nc] = true;
          }
        }
      }//while문 끝
      
      System.out.printf("#%d %d\n", test_case, answer);
    }//test_case 끝
  }//main 끝

  public static boolean isIn(int r, int c){
    return r >= 0 && r < 100 && c >= 0 && c < 100;
  }

}