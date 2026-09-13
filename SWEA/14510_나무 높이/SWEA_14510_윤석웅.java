import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {
  static int T, N;
  static int[] trees;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    T = Integer.parseInt(br.readLine());

    for (int tc = 1; tc <= T; tc++) {
      N = Integer.parseInt(br.readLine());

      trees = new int[N];

      StringTokenizer st = new StringTokenizer(br.readLine());

      int max = 0;

      for (int i = 0; i < N; i++) {
        trees[i] = Integer.parseInt(st.nextToken());

        if (max < trees[i]) {
          max = trees[i];
        }
      }

      int odd = 0;  // +1이 필요한 횟수
      int even = 0; // +2가 필요한 횟수

      for (int i = 0; i < N; i++) {
        int diff = max - trees[i];

        even += diff / 2;
        odd += diff % 2;
      }

      // +2 하나를 +1 두 개로 바꿔 날짜 수 균형 맞추기
      while (even > odd + 1) {
        even--;
        odd += 2;
      }

      int ans;

      if (odd > even) {
        // 마지막 홀수날에 종료
        ans = odd * 2 - 1;
      } else {
        // 마지막 짝수날에 종료
        ans = even * 2;
      }
      sb.append("#").append(tc).append(" ")
        .append(ans).append('\n');
    }

    System.out.print(sb);
  }
}