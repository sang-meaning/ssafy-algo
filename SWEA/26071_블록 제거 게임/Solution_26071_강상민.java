import java.io.*;
import java.util.*;

public class Solution_26071_강상민 {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;
  static StringBuilder sb = new StringBuilder();
  static int T;
  static int N;
  static int[] board;
  static int result;

  public static void main(String[] args) throws IOException {
    T = Integer.parseInt(br.readLine());

    for (int t=1; t<=T; t++) {
      N = Integer.parseInt(br.readLine());

      board = new int[N];
      result = 0;

      st = new StringTokenizer(br.readLine());

      for (int i=0; i<N; i++) {
        board[i] = Integer.parseInt(st.nextToken());
      }


      // dfs 시작
      dfs(0, 0);

      sb.append("#"+t+" "+result).append("\n");

    }

    // tc 종료
    System.out.print(sb);
  }

  static void dfs(int depth, int score) {
    if (depth == N) {
      result = Math.max(result, score);
      return;
    }

    for (int i=0; i<N; i++) {
      if (board[i] == -1) continue;

      // board[i] 의 양 옆 구하기
      // left가 있거나 없는 경우 / right가 있거나 없는 경우 4가지 경우의 수

      int left = findLeft(i); // 없으면 -2
      int right = findRight(i);

      int add = 0;
      int me = board[i];

      if (left != -2 && right != -2) {
        // 양 옆 숫자가 있으면
        add += board[left] * board[right];
        
      } else if (left == -2 && right != -2) {
        // 왼쪽이 없음
        add+=board[right];
      
      } else if (left != -2 && right == -2) {
        // 오른쪽이 없음
        add+=board[left];

      } else if (left == -2 && right == -2) {
        add+=board[i];

      }

      board[i] = -1;

      dfs(depth+1, score+add);

      board[i] = me;



    }


  }

  static int findLeft(int x) {
    // x의 왼쪽으로 탐색하기

    int left = x-1;
    while(true) {
      if (left < 0) return -2;

      if (board[left] == -1) {
        // 이미 깬 곳이면
        left--;
      } else {
        // 숫자가 있으면 인덱스 반환
        return left;
      }


    }
  }

   static int findRight(int x) {
    // x의 왼쪽으로 탐색하기

    int right = x+1;
    while(true) {
      if (right >= N) return -2;

      if (board[right] == -1) {
        // 이미 깬 곳이면
        right++;
      } else {
        // 숫자가 있으면 인덱스 반환
        return right;
      }


    }
  }


  
}
