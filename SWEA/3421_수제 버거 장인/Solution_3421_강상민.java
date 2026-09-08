import java.util.*;
import java.io.*;

public class Solution_3421_강상민 {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;
  static StringBuilder sb = new StringBuilder();
  static int T;
  static int N,M; // 재료 수, 순서쌍 수
  static int[][] in;
  static int result;
  public static void main(String[] args) throws IOException {
    T = Integer.parseInt(br.readLine());

    for (int t=1; t<=T; t++) {
      st = new StringTokenizer(br.readLine());

      N = Integer.parseInt(st.nextToken());
      M = Integer.parseInt(st.nextToken());
      in = new int[M][2];
      result = 0;

      for (int i=0; i<M; i++) {
        st = new StringTokenizer(br.readLine());

        in[i][0] = Integer.parseInt(st.nextToken());
        in[i][1] = Integer.parseInt(st.nextToken());


      }

      // 세팅 완료

      // 1~N 까지의 재료가 있는데, 부분 집합의 수를 구하되, in 로 검사해서 제외

      // in 과 모든 숫자는 index 1부터 시작하도록 받음

      dfs(0, 0, 0);

      sb.append("#"+t+" "+result).append("\n");



      
    }

    System.out.println(sb);
    
  }

  // cur : 조합 구현
  static void dfs(int depth, int mask, int cur) {
    // 매 시행마다 mask로 부분집합 만들어지니 검사
    boolean flag = true;

    for (int i=0; i<M; i++) {
      int l = in[i][0]-1;
      int r = in[i][1]-1;

      // l, r 모두 mask 에 포함되었으면 버거 못만듦
      if ( (mask & (1 << l)) != 0 && (mask & (1 << r)) != 0 ) {
        flag = false;
        break;
      }
    }

    // flag true 면 버거 만들기 가능
    if (flag) result++;

    if (depth == N) return;

    for (int i=cur; i<N; i++) {
      if ((mask & (1<<i)) != 0) continue;

      // i번째 재료 선택
      dfs(depth+1, (mask | (1<<i)), i+1);
    }



  }
  
}
