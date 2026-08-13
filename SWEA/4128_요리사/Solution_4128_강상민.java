import java.util.*;
import java.io.*;

/*

N combination N/2

*/

public class Solution_4128_강상민 {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;
  static StringBuilder sb = new StringBuilder();
  static int T,N;
  static int[][] board;
  static int result;
  static boolean[] vis;
  static int[] arr; // 선택할 4가지

  public static void main(String[] args) throws IOException {
    T = Integer.parseInt(br.readLine());


    for (int t=1; t<=T; t++) {
      N = Integer.parseInt(br.readLine());

      result = Integer.MAX_VALUE;

      board = new int[N][N];
      vis = new boolean[N];
      arr = new int[N/2];

      for (int i=0; i<N; i++) {
        st = new StringTokenizer(br.readLine());
        for (int j=0; j<N; j++) {
          board[i][j] = Integer.parseInt(st.nextToken());
        }
      }

      dfs(0, 0);

      sb.append("#"+t+" "+result).append("\n");
    }

    // tc 종료
    System.out.println(sb);
  }


  static void dfs(int depth, int cur) {
    if (depth == N/2) {
      // arr[] 전부 갱신 완료

      int sumB = 0;
      int sumA = 0;

      // arr 에 포함된 재료 index
      HashSet<Integer> set = new HashSet<>();

      for (int i=0; i<N/2; i++) {
        set.add(arr[i]);
      }

      // arr 에 포함되지 않은 재료를 포함한 배열 만들기
      int[] temp = new int[N/2];
      int iter = 0;
      for (int i=0; i<N; i++) {
        if (set.contains(i)) continue;
        temp[iter] = i;
        iter++;
      }

      // B 음식
      for (int a=0; a<N/2; a++) {
        for (int b=0; b<N/2; b++) {
          sumB += board[temp[a]][temp[b]];

        }
      }
      
      // A 음식
      for (int a=0; a<N/2; a++) {
        for (int b=0; b<N/2; b++) {
          sumA += board[arr[a]][arr[b]];
        }
      }

      // 차이
      int diff =  Math.abs(sumA - sumB);

      result = Math.min(result, diff);
      return;
    }

    for (int i=cur; i<N; i++) {
      if (vis[i] == true) continue;
    

      arr[depth] = i;
      vis[i] = true;

      dfs(depth+1, i);

      vis[i] = false; // 백트래킹
    }
  }
  
}
