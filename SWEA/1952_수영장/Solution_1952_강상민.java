import java.util.*;
import java.io.*;

/*
dp[i] : i달에 내야할 최소 금액
dp[i] 갱신할 때 
1) 1일만 : dp[i-1] + 1일금액 * 일수
2) 1달만 : dp[i-1] + 1달금액
3) 3달결제 : dp[i-1] + 3달금액인데, dp[i], dp[i+1], dp[i+2] 까지 전부 갱신
4) 1년도 3달과 같이, 그럼 dp[12] 까지 있고, 편의상 dp[12+11] 까지 만들어두기
*/

public class Solution_1952_강상민 {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;
  static StringBuilder sb = new StringBuilder();
  static int T;
  static int a,b,c,d;
  static int[] dp;
  static int[] board;
  public static void main(String[] args) throws IOException {
    T = Integer.parseInt(br.readLine());

    for (int t=1; t<=T; t++) {
      st = new StringTokenizer(br.readLine());
      a = Integer.parseInt(st.nextToken());
      b = Integer.parseInt(st.nextToken());
      c = Integer.parseInt(st.nextToken());
      d = Integer.parseInt(st.nextToken());

      st = new StringTokenizer(br.readLine());
      dp = new int[24];
      board = new int[24];

      for (int i=1; i<=12; i++)
        board[i] = Integer.parseInt(st.nextToken());

      for (int i=1; i<=23; i++)
        dp[i] = Integer.MAX_VALUE;


      for (int i=1; i<=12; i++) {
        // 1월부터 dp 테이블 갱신

        // 먼저 일간
        dp[i] = Math.min(dp[i], dp[i-1] + board[i] * a);

        // 1달
        dp[i] = Math.min(dp[i], dp[i-1] + b);

        // 3달
        dp[i] = Math.min(dp[i], dp[i-1] + c);
        for (int j=i+1; j<=i+2; j++) { // 1월부터면, 1월은 이미 갱신 했고, 2월, 3월까지
          dp[j] = Math.min(dp[j], dp[i-1] + c);
        }

        // 1년
        dp[i] = Math.min(dp[i], dp[i-1] + d);
        for (int j=i+1; j<=i+11; j++) {
          dp[j] = Math.min(dp[j], dp[i-1] + d);
        }


      }

      sb.append("#"+t+" "+dp[12]).append("\n");
    }

    // tc 종료
    System.out.println(sb);
    
  }
  
}
