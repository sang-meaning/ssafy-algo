import java.io.*;
import java.util.*;

public class Solution_3421_정서우 {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;
  static StringBuilder sb = new StringBuilder();
  static int N, M;
  static boolean[] vis;
  static boolean[][] bad;
  static int cnt;

  public static void main(String[] args) throws IOException {
    int T = Integer.parseInt(br.readLine());
    for (int tc = 1; tc <= T; tc++) {
      st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      M = Integer.parseInt(st.nextToken());
      
      vis = new boolean[N + 1];
      bad = new boolean[N + 1][N + 1];
      cnt = 0;

      for (int i = 0; i < M; i++) {
        st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        
        bad[a][b] = true;
        bad[b][a] = true;
      }
      dfs(1);
      sb.append('#').append(tc).append(' ').append(cnt).append('\n');
    }
    System.out.println(sb);
    
  }
  

  static void dfs(int idx) {
    if (idx > N) {
      cnt++;
      return;
    }
    dfs(idx + 1);
    boolean canUse = true;
    for (int i = 1; i < idx; i++) {
      if (vis[i] && bad[idx][i]) {
        canUse = false;
        break;
      }
    }
    if (canUse) {
        vis[idx] = true;
        dfs(idx + 1);
        vis[idx] = false;
      }
  }
}
