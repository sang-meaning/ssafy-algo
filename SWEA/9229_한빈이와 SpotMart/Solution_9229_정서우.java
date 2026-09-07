import java.util.*;
import java.io.*;

public class Solution_9229_정서우 {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;
  static StringBuilder sb = new StringBuilder();


  public static void main(String[] args) throws IOException {
    int TC = Integer.parseInt(br.readLine());

    for (int test_case = 1; test_case <= TC; test_case++) {
      st = new StringTokenizer(br.readLine());
      int N = Integer.parseInt(st.nextToken());
      int M = Integer.parseInt(st.nextToken());

      int[] a = new int[N];

      st = new StringTokenizer(br.readLine());
      for (int i = 0 ; i < N; i++) {
        a[i] = Integer.parseInt(st.nextToken());
      }
      Arrays.sort(a);

      int left = 0;
      int right = N - 1;
      int ans = -1;

      while (left < right) {
        int sum = a[left] + a[right];
        if (sum > M) {
          right--;
        } else {
          left++;
          ans = Math.max(ans, sum);
        }
      }

      sb.append('#')
      .append(test_case)
      .append(' ')
      .append(ans)
      .append('\n');
    }

    System.out.println(sb);
  }
}
