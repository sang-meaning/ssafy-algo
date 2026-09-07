import java.io.*;
import java.util.*;


public class Solution_5215_강상민 {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;
  static StringBuilder sb = new StringBuilder();
  static int T;
  static int N,L;
  static int[][] board;
  static boolean[] vis;
  static int result;

  public static void main(String[] args) throws IOException {
    T = Integer.parseInt(br.readLine());

    for (int t=1; t<=T; t++) {
      st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      L = Integer.parseInt(st.nextToken());

      board = new int[N][2];
      vis = new boolean[N];
      result = 0;

      for (int i=0; i<N; i++) {
        st = new StringTokenizer(br.readLine());

        board[i][0] = Integer.parseInt(st.nextToken());
        board[i][1] = Integer.parseInt(st.nextToken());

      }


      // 세팅 완료

      dfs(0,0,0,0);

      sb.append("#"+t+" "+result).append("\n");

    }

    System.out.println(sb);
    
  }

  static void dfs(int depth, int value, int cal, int idx) {
    if (cal <= L) {
      result = Math.max(result, value);
    }

    if (depth == N || cal > L) return;

    for (int i=idx; i<N; i++) {
      if (vis[i]) continue;

      vis[i] = true;
      int value2 = value + board[i][0];
      int cal2 = cal + board[i][1];

      dfs(depth+1, value2, cal2, i);

      vis[i] = false;
    }

  }
  
}
