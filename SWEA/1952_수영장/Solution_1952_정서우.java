import java.io.*;
import java.util.*;

public class Solution_1952_정서우 {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;
  static StringBuilder sb = new StringBuilder();

  public static void main(String[] args) throws IOException {
    int T = Integer.parseInt(br.readLine());
    for (int tc = 1; tc <= T; tc++) {
      int[] price = new int[4];
      int[] use = new int[13];

      st = new StringTokenizer(br.readLine());
      for (int i = 0; i < 4; i++) {
        price[i] = Integer.parseInt(st.nextToken());
      }

      st = new StringTokenizer(br.readLine());
      for (int i = 1; i <= 12; i++) {
        use[i] = Integer.parseInt(st.nextToken());
      }

      int[] dp = new int[13];
      
      for (int i = 1; i <=12; i++) {
        // 1일권과 1달권 사용 중 비교
        dp[i] = Math.min(dp[i - 1] + use[i] * price[0], dp[i - 1] + price[1]);

        // 3달권 사용과 비교
        if (i >= 3) {
          dp[i] = Math.min(dp[i], dp[i - 3] + price[2]);
        }
      }
      // 1년권 사용과 비교
      dp[12] = Math.min(dp[12], price[3]);

      sb.append("#" + tc + " "+ dp[12]).append("\n");
    }
    System.out.println(sb);
  }
}
