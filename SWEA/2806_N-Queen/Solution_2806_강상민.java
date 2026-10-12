import java.util.*;
import java.io.*;


public class Solution_2806_강상민 {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringBuilder sb = new StringBuilder();
  static int T, N;
  static int cnt;
  static boolean[] col,main,sub;

  public static void main(String[] args) throws IOException {
    T = Integer.parseInt(br.readLine());

    for (int t=1; t<=T; t++) {
      N = Integer.parseInt(br.readLine());
      col = new boolean[N]; // 세로 검사
      main = new boolean[2*N+1]; // 우하향 : 차 일정
      sub = new boolean[2*N+1]; // 우상향 : 합 일정

      cnt = 0;

      dfs(0);

      sb.append("#"+t+" "+cnt).append("\n");


    }

    System.out.println(sb);
    
  }
  static void dfs(int row) {
    if (row == N) {
      cnt++;
      return;
    }

    for (int i=0; i<N; i++) {
      if (col[i] | main[(row-i)+N] | sub[row+i]) continue;

      col[i] = true;
      main[(row-i)+N] = true;
      sub[row+i] = true;

      dfs(row+1);

      col[i] = false;
      main[(row-i)+N] = false;
      sub[row+i] = false;



    }
  }
  
}
