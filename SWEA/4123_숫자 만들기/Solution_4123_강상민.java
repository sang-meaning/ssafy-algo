import java.util.*;
import java.io.*;

public class Solution_4123_강상민 {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;
  static StringBuilder sb = new StringBuilder();
  static int T, N;
  static int[] c;
  static int[] num;
  static int max;
  static int min;
  static int[] g;
  static boolean[] vis;

  public static void main(String[] args) throws IOException {
    T = Integer.parseInt(br.readLine());

    for (int t=1; t<=T; t++) {
      N = Integer.parseInt(br.readLine());

      c = new int[4]; // 순서대로 + - * / 개수
      num = new int[N]; // 수식에 사용될 숫자들
      max = Integer.MIN_VALUE;
      min = Integer.MAX_VALUE;

      st = new StringTokenizer(br.readLine());

      g = new int[N-1]; // 차례대로 기호 나타냄 0은 +, 3은 /
      vis = new boolean[N-1];


      int iter = 0;
      // 기호 
      for (int i=0; i<4; i++) {
        c[i] = Integer.parseInt(st.nextToken());
        for (int j=0; j<c[i]; j++) {
          g[iter] = i;
          iter++;
        }
      }

    

      st = new StringTokenizer(br.readLine());

      for (int i=0; i<N; i++) {
        num[i] = Integer.parseInt(st.nextToken());
      }

      // 세팅완료

      dfs(0, num[0]);

      sb.append("#"+t+" "+(max-min)).append("\n");
      //System.out.println(max+" "+min);



    }

    // tc 종료
    System.out.println(sb);
    
  }

  static void dfs(int depth, int sum) {
    if (depth == N-1) {
      // 기호 다 썼고 계산도 다함
      max = Math.max(max, sum);
      min = Math.min(min, sum);
      return;
    }

    for (int i=0; i<N-1; i++) {
      if (vis[i]) continue;
      // g[i] 고르기 0 0 1 3 들어있음

      // g[i]가 0 이면 더하기

      vis[i] = true;

      if (g[i] == 0) {
        dfs(depth+1, sum+num[depth+1]);
      } else if (g[i] == 1) {
        dfs(depth+1, sum-num[depth+1]);
      } else if (g[i] == 2) {
        dfs(depth+1, sum * num[depth+1]);
      } else {
        dfs(depth+1, sum / num[depth+1]);
      }

      // 백트래킹
      vis[i] = false;

    }

  }
  
}
