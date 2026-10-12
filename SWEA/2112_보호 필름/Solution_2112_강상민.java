import java.util.*;
import java.io.*;


/*
D combination K * 2^K (K<=13, D<=K)
행 선택하고 백트래킹 위해 변경 전 행 임시 저장
행 선택을 조합으로 처리하되, A 혹은 B 로 나누는 것 분기

결과가 0이면 0출력 후 종료
전역 최솟값이 depth 이하이면 더 볼필요없다


*/

public class Solution_2112_강상민 {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;
  static StringBuilder sb = new StringBuilder();
  static int T;
  static int D,W,K; // 행 열 합격기준
  static int[][] board; // A:0, B:1
  static int result = Integer.MAX_VALUE;

  public static void main(String[] args) throws IOException {
    T = Integer.parseInt(br.readLine());

    for (int t=1; t<=T; t++) {
      st = new StringTokenizer(br.readLine());
      D = Integer.parseInt(st.nextToken());
      W = Integer.parseInt(st.nextToken());
      K = Integer.parseInt(st.nextToken());

      board = new int[D][W];
      result = Integer.MAX_VALUE;

      for (int i=0; i<D; i++) {
        st = new StringTokenizer(br.readLine());
        for (int j=0; j<W; j++) {
          board[i][j] = Integer.parseInt(st.nextToken());

        }
      }

      if (ok()) {
        sb.append("#"+t+" "+0).append("\n");
        continue;
      }

      dfs(0,0);

      sb.append("#"+t+" "+result).append("\n");


    }

    // tc 종료
    System.out.println(sb);
    
  }

  // depth : 행 선택한 횟수
  static void dfs(int depth, int idx) {
    if (depth == K) { // K까지만 선택해도 됨 : K개 전부 같은 약품으로 채워도 무조건 통과 가능
      return;
    }

    if (result <= depth) { // 더이상 약품 넣을 필요없음
      return;
    }

    // 행 선택
    for (int i=idx; i<D; i++) {
  
      int[] arr = new int[W];
      
      // 백트래킹을 위한 현재 상태 저장
      for (int a=0; a<W; a++) {
        arr[a] = board[i][a];
      }



      // 1) A로 약품 주입
      for (int a=0; a<W; a++) {
        board[i][a] = 0;
      }
      if (ok()) {
        result = Math.min(result, depth+1);
      } else {
        dfs(depth+1, i+1);
      }


      // 2) B로 
      for (int a=0; a<W; a++) {
        board[i][a] = 1;
      }

      if (ok()) {
        result = Math.min(result, depth+1);
      } else {
        dfs(depth+1, i+1);
      }

      for (int a=0; a<W; a++) { // 백트래킹
        board[i][a] = arr[a];
      }
    }

  }

  // 성능검사 통과 여부 함수
  static boolean ok() {

    for (int j=0; j<W; j++) {
      int con = 1; // 연속된 최대
      int conMax = 1;
      int pre = board[0][j];
      
      for (int i=1; i<D; i++) {
        if (board[i][j] == pre) {
          con++;
          conMax = Math.max(conMax, con);
        }else {
          con = 1;
          pre = board[i][j];
        }

      }

      if (conMax < K) return false;
    }

    return true;
  }
  
}
